import Message.{Down,Left,Request,Right,Up}
import Result._
import Room._
import akka.actor.Actor

class WumpusActor extends Actor {

  val xDim = 4;
  val yDim = 4;
  val worldMap: Array[Array[_ >: Room.Empty.type <: Product]] = Array(
    Array(Empty,Empty,Empty,WumpusRoom),
    Array(Empty,Pit,Empty,Empty),
    Array(Empty,Empty,Empty,Empty),
    Array(GoldRoom,Empty,Empty,Pit),
  )
  val state = (stench,glitter,breeze,scream,bump)
  var currentPosition: (Int,Int) = (0,0)
  var stench: Boolean = false
  var glitter: Boolean = false
  var breeze: Boolean = false
  var scream: Boolean = false
  var bump: Boolean = false

  override def receive: Receive = {
    case Request => getState()
    case Left => moveToLeft()
    case Right => moveToRight()
    case Up => moveToUp()
    case Down => moveToDown()
  }

  def getState() {
    checkRoom()
    state
  }

  def checkRoom(): Boolean = {
    this.worldMap(this.currentPosition._1)(this.currentPosition._2) match {
      case WumpusRoom => false
      case Pit => false
      case GoldRoom => true
      case _ => false
    }
  }

  def moveToLeft() = {
    if (currentPosition._1 - 1 > 0) {
      currentPosition = (currentPosition._1 - 1,currentPosition._2)
      if (checkRoom()) {
        println("Move to LEFT (" + currentPosition.toString() + ")")
        Ok
      }
      else {
        Finish
      }
    }
    else Stop
  }

  def moveToRight(): Product = {
    if (currentPosition._1 + 1 < xDim) {
      currentPosition = (currentPosition._1 + 1,currentPosition._2)
      if (checkRoom()) {
        println("Move to RIGHT (" + currentPosition.toString() + ")")
        Ok
      }
      else {
        Finish
      }
    }
    else Stop
  }

  def moveToUp(): Product = {
    if (currentPosition._1 - 1 < 0) {
      currentPosition = (currentPosition._1,currentPosition._2 - 1)
      if (checkRoom()) {
        println("Move UP (" + currentPosition.toString() + ")")
        Ok
      }
      else {
        Finish
      }
    }
    else Stop
  }

  def moveToDown(): Product = {
    if (currentPosition._1 + 1 < yDim) {
      currentPosition = (currentPosition._1,currentPosition._2 + 1)
      if (checkRoom()) {
        println("Move DOWN (" + currentPosition.toString() + ")")
        Ok
      }
      else {
        Finish
      }
    }
    else Stop
  }

}



