package git

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinGroup
import git.actor.GitLoadTriggerActor
import git.model.GitLoadTrigger
import org.joda.time.DateTime

import scala.concurrent.duration._

object BaseTriggerActor {

  def main(args: Array[String]): Unit = {
    println(" ------------------- STARTING FE ACTOR SYSTEM ------------------- ")
    val actorSystemName = "feature-eye-akka"
    val system = ActorSystem(actorSystemName)

    import system.dispatcher

    def getRouter = {
      val allActors = List(
        system.actorOf(Props[GitLoadTriggerActor]))

      val roundRobinGroup = RoundRobinGroup(allActors.map(_.path.toStringWithoutAddress))
      system.actorOf(roundRobinGroup.props)
    }

    val roundRobinRouter = getRouter
    system.scheduler.schedule(0 seconds, 30 seconds)(roundRobinRouter ! GitLoadTrigger(actorSystemName, new DateTime()))

    system.awaitTermination()
  }
}
