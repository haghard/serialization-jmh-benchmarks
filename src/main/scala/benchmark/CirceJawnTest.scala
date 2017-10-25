package benchmark

import benchmark.Data.Type
import io.circe._
import io.circe.generic.auto._
import io.circe.jawn
import io.circe.syntax._

object CirceJawnTest {

  def parse(): Unit = {
    val doc = jawn.parse(Data.jsonBody)
  }

  private val decoder = implicitly[Decoder[Data.Type]]
  private val encoder = implicitly[Encoder[Data.Type]]

  def roundTrip2: String = {
    val obj = jawn.decode[Data.Type](Data.jsonBody)(decoder).right.get
    //encoder(obj).noSpaces
    jackson.jacksonPrint(encoder(obj))
  }
}
