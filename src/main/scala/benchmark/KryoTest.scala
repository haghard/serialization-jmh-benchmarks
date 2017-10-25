package benchmark
import java.io._

import benchmark.Data.Friend
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.Serializer
import com.esotericsoftware.kryo.io.Output

object KryoTest {
  val kryo = new Kryo()
  val obj = benchmark.JacksonTest.decode()
  kryo.register(classOf[Data.Type], new MySerializer, 1)

  class MySerializer extends Serializer[Data.Type] {
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
      obj.`friends`.foreach { f =>
        out.writeInt(f.id)
        out.writeString(f.name)
      }
      //***************************************
      out.writeString(obj.`greeting`)
      out.writeString(obj.`favoriteFruit`)
    }

    override def read(kryo: Kryo, in: Input, clazz: Class[Data.Type]) = {
      new Data.Type(
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
        List.range(0, in.readInt()).map(_ => Friend(in.readInt, in.readString)),
        in.readString,
        in.readString
      )
    }
  }

  //DataOutputStream, BufferedOutputStream,  ByteArrayOutputStream
  def roundTrip: Data.Type = {
    val bytes = new ByteArrayOutputStream
    val out: Output = new Output(bytes)
    try { kryo.writeObject(out, obj) } finally out.close
    val in = new ByteArrayInputStream(bytes.toByteArray)
    try kryo.readObject(new Input(in), classOf[Data.Type]) finally in.close
  }
}
