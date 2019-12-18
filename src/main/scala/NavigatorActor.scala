import Message.{Down, Left, Request, Right, Up}
import Result._
import akka.actor.{Actor, ActorRef, ActorSystem}

import scala.util.Random


class NavigatorActor(speleologistActor: ActorRef, system: ActorSystem) extends Actor {
  override def receive: Receive = {
    case Start => getNext
    case Request => speleologistActor ! Request match {
      case Ok => getNext
      case Finish => println("Game over")
      case Win => println("Victory")
      case _ =>  println("Game over")
        system.shutdown()

    }
    case Left => speleologistActor ! Left
    case Right => speleologistActor ! Right
    case Up => speleologistActor ! Up
    case Down => speleologistActor ! Down
  }

  def getNext: Unit = {
    Random.shuffle(List(Down,Left,Request,Right,Up)).head
  }
}
