package benchmark

import java.util.concurrent.TimeUnit

import com.domain.users.pb.protocol.{FriendPB, TypePB}
import com.domain.users.{AvroFriend, AvroType}
import org.openjdk.jmh.annotations._
import MyBenchmark._

object MyBenchmark {

  @State(Scope.Thread)
  class BState(var zeroObj: Data.Type, var avroObj: AvroType, var protoObj: TypePB) {

    def this() =
      this(null, null, null)

    @Setup(Level.Trial)
    def init() {
      zeroObj = benchmark.JacksonTest.decode
      avroObj = new AvroType(
        zeroObj.`_id`,
        zeroObj.`index`,
        zeroObj.`guid`,
        zeroObj.`isActive`,
        zeroObj.`balance`,
        zeroObj.`picture`,
        zeroObj.`age`,
        zeroObj.`eyeColor`,
        zeroObj.`name`,
        zeroObj.`gender`,
        zeroObj.`company`,
        zeroObj.`email`,
        zeroObj.`phone`,
        zeroObj.`address`,
        zeroObj.`about`,
        zeroObj.`registered`,
        zeroObj.`latitude`,
        zeroObj.`longitude`,
        java.util.List.of(zeroObj.`tags`: _*),
        java.util.List.of(zeroObj.`friends`.map(e ⇒ new AvroFriend(e.id, e.name)): _*),
        zeroObj.`greeting`,
        zeroObj.`favoriteFruit`
      )

      protoObj = new TypePB(
        zeroObj.`_id`,
        zeroObj.`index`,
        zeroObj.`guid`,
        zeroObj.`isActive`,
        zeroObj.`balance`,
        zeroObj.`picture`,
        zeroObj.`age`,
        zeroObj.`eyeColor`,
        zeroObj.`name`,
        zeroObj.`gender`,
        Some(zeroObj.`company`),
        Some(zeroObj.`email`),
        Some(zeroObj.`phone`),
        zeroObj.`address`,
        zeroObj.`about`,
        zeroObj.`registered`,
        zeroObj.`latitude`,
        zeroObj.`longitude`,
        zeroObj.`tags`,
        zeroObj.`friends`.map(e ⇒ new FriendPB(e.id, e.name)),
        zeroObj.`greeting`,
        zeroObj.`favoriteFruit`
      )

    }
  }

}

/* Default settings for benchmarks in this class */
//@OutputTimeUnit(TimeUnit.MICROSECONDS)
//@BenchmarkMode(Array(Mode.AverageTime)) //Mode.Throughput,
class MyBenchmark {

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
  def kryo(s: BState): Unit =
    KryoTest.roundTrip(s.zeroObj)

  @Benchmark
  def avro(s: BState): Unit =
    AvroSupport.roundTrip(s.avroObj)

  @Benchmark
  def proto(s: BState): Unit =
    ProtoSupport.roundTrip(s.protoObj)
}
