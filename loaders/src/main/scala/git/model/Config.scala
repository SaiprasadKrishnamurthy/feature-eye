package git.model

import java.nio.file.Paths

import com.mongodb.MongoClient
import org.springframework.data.mongodb.core.{SimpleMongoDbFactory, MongoTemplate}

/**
  * Created by saipkri on 24/03/16.
  */
object Config {

  case class FeaturesGitRepo(repoUrl: String, repoBranch: String)

  val gitRepo = FeaturesGitRepo("ssh://git@stash-eng.cisco.com:7999/cvgpi/bdd_tests.git", "feature/BDD_POC")
  val localWorkspace = Paths.get(System.getProperty("user.home"), "fr").toFile

  private val mongo = new SimpleMongoDbFactory(new MongoClient(), "fe")
  val mongoTemplate = new MongoTemplate(mongo)
}
