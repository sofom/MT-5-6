import Result.Start
import akka.actor.ActorSystem
import akka.actor.Props


object Main extends App {
  val system = ActorSystem("WumpusWorld")

  val wumpusActor = system.actorOf(Props[WumpusActor], name = "WumpusActor")

  val speleologistActor = system.actorOf(Props[SpeleologistActor], name = "speleologist")
  val navigatorActor = system.actorOf(Props(new NavigatorActor(speleologistActor, system)), "navigator")
  wumpusActor ! Start

}