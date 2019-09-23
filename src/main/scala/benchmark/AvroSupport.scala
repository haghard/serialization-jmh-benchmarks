package benchmark

import java.io.{ByteArrayOutputStream, File}

import org.apache.avro.Schema
import com.domain.users.AvroType
import org.apache.avro.io.{BinaryEncoder, DecoderFactory, EncoderFactory}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}

import scala.util.Using
import scala.util.Using.Releasable

//avro:generate

object AvroSupport {

  //private val obj    = benchmark.JacksonTest.decode
  private val schema = new Schema.Parser().parse(new File("./src/main/avro/schema.avsc"))

  def roundTrip(obj: AvroType): AvroType = {
    val bts = serialize(obj)
    deserialize(bts)
  }

  //https://medium.com/wix-engineering/my-favorite-new-features-of-scala-2-13-standard-library-909a89b0da4
  /*Using.resources(new FileReader("input.txt"), new FileWriter("output.txt")) { (reader, writer) ⇒
    reader.read()
  }*/

  implicit object BinaryEncoderIsReleasable extends Releasable[BinaryEncoder] {
    def release(resource: BinaryEncoder): Unit =
      resource.flush
  }

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
