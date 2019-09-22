/*
package benchmark

import com.twitter.chill.{KryoInstantiator, KryoPool, /*KryoSerializer,*/ ScalaKryoInstantiator}

import scala.reflect.ClassTag

object ChillTest {
  val obj = benchmark.JacksonTest.decode()
  //KryoSerializer.registered.newKryo

  def serialize[T](t: T): Array[Byte] =
    ScalaKryoInstantiator.defaultPool.toBytesWithClass(t)

  def deserialize[T](bytes: Array[Byte]): T =
    ScalaKryoInstantiator.defaultPool.fromBytes(bytes).asInstanceOf[T]

  def roundTrip2[T: ClassTag](k: KryoInstantiator, t: T): T = {
    val pool = KryoPool.withByteArrayOutputStream(1, k)
    pool.fromBytes(pool.toBytesWithClass(t)).asInstanceOf[T]
  }

  def roundTrip: Data.Type =
    deserialize[Data.Type](serialize(obj))
}
 */
