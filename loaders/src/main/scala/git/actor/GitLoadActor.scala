package git.actor

import java.io.{InputStreamReader, ByteArrayInputStream, File}
import java.security.MessageDigest
import java.util.UUID
import com.fasterxml.jackson.databind.ObjectMapper
import gherkin.formatter.JSONFormatter
import gherkin.parser.Parser
import gherkin.util.FixJava
import org.sai.featureeye.domain.Feature
import org.sai.featureeye.gherkin.FeatureFileFragment
import org.springframework.data.mongodb.core.query.{Update, Query, Criteria}

import scala.collection.JavaConversions._

import akka.actor.{Actor, ActorLogging, Props}
import com.jcraft.jsch.Session
import git.model.{Config, GitLoadTrigger}
import org.apache.commons.io.FileUtils
import org.eclipse.jgit.api.{TransportConfigCallback, Git}
import org.eclipse.jgit.transport.OpenSshConfig.Host
import org.eclipse.jgit.transport.{JschConfigSessionFactory, SshTransport, Transport}

/**
  * @author Sai Kris
  */
class GitLoadActor extends Actor with ActorLogging {


  def receive = {
    case _ => {
      // Delete the existing workspace
      dirsSetup
      gitClone
      allFeatures
    }

      def gitClone = {
        val sshSessionFactory = new JschConfigSessionFactory() {
          override def configure(host: Host, session: Session) {
          }
        }
        Git.cloneRepository()
          .setURI(Config.gitRepo.repoUrl)
          .setBranch(Config.gitRepo.repoBranch)
          .setDirectory(Config.localWorkspace)
          .setTransportConfigCallback(new TransportConfigCallback() {
            override def configure(transport: Transport) {
              val sshTransport = transport.asInstanceOf[SshTransport];
              sshTransport.setSshSessionFactory(sshSessionFactory);
            }
          })
          .call()
          .close()
      }
  }

  def toFeature(file: File) = {
    val gherkinContents = FileUtils.readFileToString(file.asInstanceOf[File])
    val gherkinFilePath = file.getPath
    val fileName = file.getName
    val f = new Feature()
    f.setFileName(fileName)
    f.setLocation(gherkinFilePath)
    f.setRawContents(gherkinContents)
    f
  }

  def dirsSetup = {
    FileUtils.deleteQuietly(Config.localWorkspace)
    FileUtils.forceMkdir(Config.localWorkspace)
  }

  def allFeatures = {
    FileUtils.listFiles(Config.localWorkspace, Array("feature"), true)
      .map(f => toFeature(f.asInstanceOf[File]))
      .map(gherkinToJson)
      .map(jsonToGherkinDomainModel)
      .foreach(upsert)
  }

  def gherkinToJson(feature: Feature) = {
    val gherkin = FixJava.readReader(new InputStreamReader(new ByteArrayInputStream(feature.getRawContents.getBytes)))
    val json = new java.lang.StringBuilder
    val formatter = new JSONFormatter(json)
    val parser = new Parser(formatter)
    parser.parse(new String(gherkin), "", 0)
    formatter.done()
    formatter.close()
    (feature, json.toString)
  }

  def jsonToGherkinDomainModel(featureAndJson: (Feature, String)) = {
    val mapper: ObjectMapper = new ObjectMapper
    val frag = mapper.readValue(featureAndJson._2, classOf[Array[FeatureFileFragment]])
    (featureAndJson._1, frag(0))
  }

  def upsert(domainTuple: (Feature, FeatureFileFragment)) = {

    // Update feature
    val query = new Query()
    query.addCriteria(Criteria.where("fileName").is(domainTuple._1.getFileName))

    val update = new Update()
    update.set("location", domainTuple._1.getLocation)
    update.set("rawContents", domainTuple._1.getRawContents)
    Config.mongoTemplate.upsert(query, update, classOf[Feature])

    val fresh = Config.mongoTemplate.findOne(query, classOf[Feature])

    // Replace feature gherkin model.
    val query1 = new Query()
    query1.addCriteria(Criteria.where("featureId").is(fresh.getId))
    val update1 = new Update()
    update1.set("line", domainTuple._2.getLine)
    update1.set("elements", domainTuple._2.getElements)
    update1.set("additionalProperties", domainTuple._2.getAdditionalProperties)
    update1.set("tags", domainTuple._2.getTags)
    Config.mongoTemplate.upsert(query1, update1, classOf[FeatureFileFragment])
  }
}
