package benchmark

import java.io._

import benchmark.KryoTest.obj

import scala.reflect.ClassTag

object JavaSerializationTest {

  def roundTrip: Data.Type =
    roundTripJava[Data.Type](obj)

  def jserialize[T <: Serializable](t: T): Array[Byte] = {
    val bos = new ByteArrayOutputStream
    val out = new ObjectOutputStream(bos)
    try {
      out.writeObject(t)
      bos.toByteArray
    } finally {
      out.close
      bos.close
    }
  }

  def jdeserialize[T](bytes: Array[Byte])(implicit cmf: ClassTag[T]): T = {
    val cls = cmf.runtimeClass.asInstanceOf[Class[T]]
    val bis = new ByteArrayInputStream(bytes)
    val in  = new ObjectInputStream(bis)
    try {
      cls.cast(in.readObject)
    } finally {
      bis.close
      in.close
    }
  }

  private def roundTripJava[T <: Serializable](t: T)(implicit cmf: ClassTag[T]): T =
    jdeserialize(jserialize(t))
}
