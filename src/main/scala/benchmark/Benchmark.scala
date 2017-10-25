package benchmark

class Benchmark {
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

  @org.openjdk.jmh.annotations.Benchmark
  def jackson(): Unit = {
    //obj -> json
    //json -> bytes
    //bytes -> obj
    JacksonTest.roundTrip
  }

  @org.openjdk.jmh.annotations.Benchmark
  def circe(): Unit = {
    //obj -> json
    //json -> bytes
    //bytes -> obj
    CirceJacksonSupport.roundTrip
  }

  @org.openjdk.jmh.annotations.Benchmark
  def kryo(): Unit = {
    //obj -> bytes
    //bytes -> obj
    KryoTest.roundTrip
  }
}
