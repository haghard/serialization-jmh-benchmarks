package benchmark

class MyBenchmark {
  /*
  @Benchmark
  def circeJackson(): Unit = {
   CirceJacksonTest.decode()
  }

  @Benchmark
  def circeJacksonAuto(): Unit = {
    CirceJacksonTest.decodeAuto()
  }
   */

  /*
  @Benchmark
  def sprayDecode(): Unit = {
   SprayJsonTest.decodeEncode()
  }
   */

  /*
  @Benchmark
  def circeJackson(): Unit = {
    CirceJacksonTest.decode()
  }
   */
  /*
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
  }*/

  /*******************************************************/
  @org.openjdk.jmh.annotations.Benchmark
  def jacksonDecode(): Unit =
    JacksonTest.roundTrip

  @org.openjdk.jmh.annotations.Benchmark
  def circeJacksonDecodeEncode(): Unit =
    CirceJacksonSupport.roundTrip
  @org.openjdk.jmh.annotations.Benchmark
  def javaSerialization(): Unit =
    JavaSerializationTest.roundTrip

  @org.openjdk.jmh.annotations.Benchmark
  def chill(): Unit =
    //obj -> bytes
    //bytes -> obj
    ChillTest.roundTrip

  /*******************************************************/
  @org.openjdk.jmh.annotations.Benchmark
  def kryo(): Unit =
    //obj -> bytes
    //bytes -> obj
    //KryoTest.roundTrip
    KryoTest.roundTripWithSerializer

}
