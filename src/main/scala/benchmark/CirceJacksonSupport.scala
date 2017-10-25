package benchmark

import benchmark.Data.Type
import io.circe._
import io.circe.generic.auto._

object CirceJacksonSupport {
  private val decoder = implicitly[Decoder[Data.Type]]
  private val encoder = implicitly[Encoder[Data.Type]]

  def decode(): Either[Error, Type] = {
    jackson.decode[Data.Type](Data.jsonBody)(decoder)
  }

  //val js = io.circe.parser.parse(Data.jsonBody).right.get
  //val	obj = decoder.decodeJson(js).right.get
  val js = io.circe.parser.parse(Data.jsonBody).right.get
  val	obj = decoder.decodeJson(js).right.get

  def roundTrip: Data.Type = {
  	val bytes = encoder(obj).noSpaces.getBytes
    jackson.decode[Data.Type](new String(bytes)).right.get
  }

  /*def roundTrip2: String = {
    val obj = jackson.decode[Data.Type](Data.jsonBody)(decoder).right.get
    jackson.jacksonPrint(encoder(obj))
  }*/

  def decodeAuto(): Either[Error, Type] = {
    jackson.decode[Data.Type](Data.jsonBody)
  }
}
