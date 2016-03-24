package git.actor

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Props
import akka.routing.RoundRobinRouter
import git.model.GitLoadTrigger
import scala.collection.JavaConversions._
import java.util.UUID

/**
  * @author Sai Kris
  */
class GitLoadTriggerActor extends Actor with ActorLogging {

  def receive = {
    case triggerMsg: GitLoadTrigger => {
      val mavenArtifactActor = context.actorOf(Props.create(classOf[GitLoadActor]), "GitLoadActor" + UUID.randomUUID())
      log.info("Triggered GitLoadActor "+triggerMsg)
      mavenArtifactActor ! triggerMsg
    }
  }
}
