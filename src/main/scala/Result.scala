object Result {

  sealed trait Response

  case object Start extends Response

  case object Ok extends Response

  case object Win extends Response

  case object Finish extends Response

  case object Stop extends Response

}
