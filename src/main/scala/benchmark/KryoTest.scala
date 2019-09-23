package benchmark

import java.io._

import benchmark.Data.Friend
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.Serializer
import com.esotericsoftware.kryo.io.Output

import scala.reflect.ClassTag
import scala.util.Using

object KryoTest {
  val kryo = new Kryo()
  kryo.register(classOf[Data.Type], new MyDataSerializer, 1)

  val obj = benchmark.JacksonTest.decode

  class MyDataSerializer extends Serializer[Data.Type] {
    override def write(kryo: Kryo, out: Output, obj: Data.Type): Unit = {
      out.writeString(obj.`_id`)
      out.writeInt(obj.`index`)
      out.writeString(obj.`guid`)
      out.writeBoolean(obj.`isActive`)
      out.writeString(obj.`balance`)
      out.writeString(obj.`picture`)
      out.writeInt(obj.`age`)
      out.writeString(obj.`eyeColor`)
      out.writeString(obj.`name`)
      out.writeString(obj.`gender`)
      out.writeString(obj.`company`)
      out.writeString(obj.`email`)
      out.writeString(obj.`phone`)
      out.writeString(obj.`address`)
      out.writeString(obj.`about`)
      out.writeString(obj.`registered`)
      out.writeDouble(obj.`latitude`)
      out.writeDouble(obj.`longitude`)
      out.writeString(obj.`tags`.mkString(","))
      //*******friends*************************
      out.writeInt(obj.`friends`.size)
      obj.`friends`.foreach { f ⇒
        out.writeInt(f.id)
        out.writeString(f.name)
      }
      //***************************************
      out.writeString(obj.`greeting`)
      out.writeString(obj.`favoriteFruit`)
    }

    override def read(kryo: Kryo, in: Input, clazz: Class[Data.Type]): Data.Type =
      Data.Type(
        in.readString,
        in.readInt,
        in.readString,
        in.readBoolean,
        in.readString,
        in.readString,
        in.readInt,
        in.readString,
        in.readString,
        in.readString,
        in.readString,
        in.readString,
        in.readString,
        in.readString,
        in.readString,
        in.readString,
        in.readDouble,
        in.readDouble,
        in.readString.split(",").toList,
        List.range(0, in.readInt).map(_ ⇒ Friend(in.readInt, in.readString)),
        in.readString,
        in.readString
      )
  }

  def roundTrip: Data.Type = {
    val bts = serialize(obj)
    deserialize[Data.Type](bts)
  }

  def serialize[T](out: T): Array[Byte] =
    Using.resource(new ByteArrayOutputStream()) { outBts ⇒
      Using.resource(new Output(outBts))(kryo.writeObject(_, obj))
      outBts.toByteArray
    }

  def deserialize[T: ClassTag](bytes: Array[Byte]): T = {
    val in: Input = new Input(bytes)
    try kryo.readObject(in, implicitly[ClassTag[T]].runtimeClass.asInstanceOf[Class[T]])
    finally in.close
  }
}
