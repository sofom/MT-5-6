import Message._
import Result._
import akka.actor._

class SpeleologistActor(world: ActorRef) extends Actor {

  override def receive: Receive = {
    case Request => parse(world ! Request)
    case Left => world ! Left
    case Right => world ! Right
    case Up => world ! Up
    case Down => world ! Down
  }

  def parse(worldResp: Response) = {
    worldResp match {
      case Stop => Stop
      case _ => Finish
    }
  }
}


