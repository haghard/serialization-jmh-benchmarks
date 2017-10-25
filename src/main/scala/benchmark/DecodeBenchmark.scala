package benchmark

import org.openjdk.jmh.annotations._

class DecodeBenchmark {
  /*
  @Benchmark
  def circeJackson(): Unit = {
   CirceJacksonTest.decode()
  }

  @Benchmark
  def circeJacksonAuto(): Unit = {
    CirceJacksonTest.decodeAuto()
  }

  @Benchmark
  def jacksonDecode(): Unit = {
   JacksonTest.decode()
  }

  @Benchmark
  def sprayDecode(): Unit = {
   SprayJsonTest.decodeEncode()
  }

  @Benchmark
  def circeJacksonDecodeEncode(): Unit = {
    CirceJacksonTest.decodeEncode
  }
  @Benchmark
  def circeJackson(): Unit = {
    CirceJacksonTest.decode()
  }
  */

  @Benchmark
  def jacksonDecodeEncode(): Unit = {
    //obj -> json
    //json -> bytes
    //bytes -> obj
    JacksonTest.roundTrip
  }

  @Benchmark
  def circe1(): Unit = {
    //obj -> json
    //json -> bytes
    //bytes -> obj
    CirceJacksonSupport.roundTrip
  }

  @Benchmark
  def kryoRoundTrip(): Unit = {
    //obj -> bytes
    //bytes -> obj
    KryoSupport.roundTrip
  }
}
