package benchmark
import org.openjdk.jmh.annotations.Benchmark

class MyBenchmark {

  /*******************************************************/
  /*
  @Benchmark
  def sprayDecode(): Unit =
    SprayJsonTest.roundTrip

  @Benchmark
  def jackson(): Unit =
    JacksonTest.roundTrip

  @Benchmark
  def circe(): Unit =
    CirceJacksonSupport.roundTrip

  @Benchmark
  def circeJacksonDecodeEncode(): Unit =
    CirceJacksonSupport.roundTrip

  @Benchmark
  def javaSerialization(): Unit =
    JavaSerializationTest.roundTrip
   */
  @Benchmark
  def avro(): Unit =
    AvroSupport.roundTrip

  @Benchmark
  def kryo(): Unit =
    KryoTest.roundTrip

}
