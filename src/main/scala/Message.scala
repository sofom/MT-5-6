object Message {

  sealed trait Action

  case object Left extends Action

  case object Right extends Action

  case object Up extends Action

  case object Down extends Action

  case object Request

  case object Accept

}