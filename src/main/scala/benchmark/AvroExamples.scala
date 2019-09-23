package benchmark

/**
  *
  * runMain benchmark.AvroExamples
  *
  *
  * rm -rf ./src/main/java/recipes/chat/domain && java -jar ./avro/avro-tools-1.9.1.jar compile schema ./src/main/resources/UsersEnvelopeV1.avsc ./src/main/java
  * rm -rf ./src/main/java/recipes/chat/domain && java -jar ./avro/avro-tools-1.9.1.jar compile schema ./src/main/resources/UsersEnvelopeV2.avsc ./src/main/java
  *
  * ## Avro
  *
  * Avro will provide you full compatibility support.
  * Backward compatibility is necessary for reading the old version of events.
  * Forward compatibility is required for rolling updates — at the same time old and new version of events can be exchanged
  * between micro service instances.
  *
  * 1.  A backward compatible change - write with V1 and read with V2 (a  new schema can be used to read old data)
  * 2.  A forward compatible change -  write with V2 and read with V1
  * 3.  A full compatible  - your change is Backward and Forward compatible
  * 4.  A breaking change - none of those
  *
  *
  * Advice when writing Avro schema
  * 1) Add field with defaults
  * 2) Removing only fields with have defaults
  *
  *
  * If you target full compatibility follows these rules:
  * Removing fields with defaults is fully compatible change
  * Adding fields with defaults is fully compatible change
  *
  * Enum can't evolve over time.
  *
  * When evolving schema, ALWAYS give defaults.
  *
  * When evolving schema, NEVER
  * Rename fields
  * Remove required fields
  *
  *
  * Links
  *
  * https://www.baeldung.com/java-apache-avro
  * http://bigdatums.net/2016/01/20/simple-apache-avro-example-using-java/
  * https://github.com/apache/avro/tree/master/doc/examples/java-example/src/main/java/example
  * https://liyanxu.blog/2018/02/07/apache-avro-examples/
  *
  *
  * https://avro.apache.org/docs/current/gettingstartedjava.html
  *
  * https://stackoverflow.com/questions/28808479/how-to-generate-schema-less-avro-files-using-apache-avro
  *
  *
  * https://github.com/Banno/salat-avro
  *
  */
/*

import java.io.{File, FileInputStream}
import java.util.{TimeZone, UUID}

import com.domain.users.{Joined, UserEnv}
import org.apache.avro.Schema
import org.apache.avro.file.{DataFileStream, DataFileWriter}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}

import org.apache.commons.codec.digest.DigestUtils

import scala.util.Using

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
        //.setPromoCode(1234)
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
 */
