package benchmark

import java.io.{File, FileInputStream}
import java.util.{TimeZone, UUID}

import com.domain.users.{Joined, UserEnv}
import org.apache.avro.Schema
import org.apache.avro.file.{DataFileStream, DataFileWriter}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}

import org.apache.commons.codec.digest.DigestUtils

import scala.util.Using

//runMain benchmark.AvroExamples
object AvroExamples {

  val fileName = "./events"

  def main(args: Array[String]): Unit = {

    val prevSchemaPath = new File("./src/main/avro-history/UsersEnvelopeV1.avsc")
    val schemaPath     = new File("./src/main/avro/UsersEnvelopeV2.avsc")

    val prev = new Schema.Parser().parse(prevSchemaPath)
    val cur  = new Schema.Parser().parse(schemaPath)

    val ev = new UserEnv(
      UUID.randomUUID.toString,
      System.currentTimeMillis,
      TimeZone.getDefault.getID,
      Joined
        .newBuilder()
        .setLogin("harry")
        .setEmail("harry@email.com")
        .setToken("harry-sec-token-xxxxxxxxxx")
        .setPromoCode(1234)
        .build()
    )

    writeToFile(ev, cur)
    readFromFile(new FileInputStream(fileName), cur, cur)

    //readFromFile(new FileInputStream(fileName), cur, prev)
  }

  def writeToFile(ev: UserEnv, writerSchema: Schema): Unit =
    Using.resource(new DataFileWriter[UserEnv](new SpecificDatumWriter[UserEnv](classOf[UserEnv]))) { writer ⇒
      writer.create(writerSchema, new File(fileName))
      writer.append(ev)
      println(s"*** writeToFile: $ev")
      writer.flush
    }

  def readFromFile(in: FileInputStream, writerSchema: Schema, readerSchema: Schema): Unit =
    Using.resource(in) { input ⇒
      Using.resource(new DataFileStream(input, new SpecificDatumReader[UserEnv](writerSchema, readerSchema))) {
        datumReader ⇒
          println(
            s"*** readFromFile: ${DigestUtils.md5Hex(writerSchema.toString)} / ${DigestUtils.md5Hex(readerSchema.toString)}"
          )
          if (datumReader.hasNext) {
            val ev = datumReader.next
            println(s"read it back: $ev")
          }
      }
    }
}
