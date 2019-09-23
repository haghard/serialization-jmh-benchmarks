package benchmark

import java.io.{ByteArrayOutputStream, File}

import com.domain.users.AvroType
import com.domain.users.AvroFriend
import org.apache.avro.Schema
import org.apache.avro.io.{BinaryEncoder, DecoderFactory, EncoderFactory}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}

import scala.util.Using.Releasable
import scala.util.Using
import java.io.{FileReader, FileWriter}

object AvroSupport {

  private val obj    = benchmark.JacksonTest.decode
  private val schema = new Schema.Parser().parse(new File("./src/main/avro/schema.avsc"))

  val avroObj = new AvroType(
    obj.`_id`,
    obj.`index`,
    obj.`guid`,
    obj.`isActive`,
    obj.`balance`,
    obj.`picture`,
    obj.`age`,
    obj.`eyeColor`,
    obj.`name`,
    obj.`gender`,
    obj.`company`,
    obj.`email`,
    obj.`phone`,
    obj.`address`,
    obj.`about`,
    obj.`registered`,
    obj.`latitude`,
    obj.`longitude`,
    java.util.List.of(obj.`tags`: _*),
    java.util.List.of(obj.`friends`.map(e ⇒ new AvroFriend(e.id, e.name)): _*),
    obj.`greeting`,
    obj.`favoriteFruit`
  )

  def roundTrip: AvroType = {
    val bts = serialize(avroObj)
    deserialize(bts)
  }

  /*Using.resources(new FileReader("input.txt"), new FileWriter("output.txt")) { (reader, writer) ⇒
    reader.read()
  }*/

  implicit object BinaryEncoderIsReleasable extends Releasable[BinaryEncoder] {
    def release(resource: BinaryEncoder): Unit =
      resource.flush
  }

  //https://medium.com/wix-engineering/my-favorite-new-features-of-scala-2-13-standard-library-909a89b0da4
  def serialize(ev: AvroType): Array[Byte] =
    Using.resource(new ByteArrayOutputStream()) { out ⇒
      Using.resource(EncoderFactory.get.binaryEncoder(out, null)) { enc ⇒
        new SpecificDatumWriter[AvroType](schema).write(ev, enc)
      }
      out.toByteArray
    }

  def deserialize(bts: Array[Byte]): AvroType = {
    val reader  = new SpecificDatumReader[AvroType](schema)
    val decoder = DecoderFactory.get.binaryDecoder(bts, null)
    reader.read(null, decoder)
  }
}
