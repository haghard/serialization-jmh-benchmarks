package benchmark

import spray.json._

object SprayJsonTest {

  def parse(): Unit =
    Data.jsonBody.parseJson

  object MyJsonProtocol extends DefaultJsonProtocol {
    implicit val DataTypeFriend = jsonFormat2(Data.Friend)
    implicit val DataType       = jsonFormat22(Data.Type)
  }

  import MyJsonProtocol._

  def decode(): Data.Type =
    Data.jsonBody.parseJson.convertTo[Data.Type]

  def roundTrip: String = {
    val obj = Data.jsonBody.parseJson.convertTo[Data.Type]
    obj.toJson.compactPrint
  }

  def invalidDecode(): Unit =
    Data.invalidJson.parseJson.convertTo[Data.Type]
}
