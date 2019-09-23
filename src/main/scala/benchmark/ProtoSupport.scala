package benchmark

import com.domain.users.pb.protocol.{FriendPB, TypePB}

object ProtoSupport {

  private val obj = benchmark.JacksonTest.decode

  val protoObj = new TypePB(
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
    obj.`tags`,
    obj.`friends`.map(e ⇒ new FriendPB(e.id, e.name)),
    obj.`greeting`,
    obj.`favoriteFruit`
  )

  def serialize(ev: TypePB): Array[Byte] =
    ev.toByteArray

  def deserialize(bts: Array[Byte]): TypePB =
    TypePB.parseFrom(bts)

  def roundTrip: TypePB = {
    val bts = serialize(protoObj)
    deserialize(bts)
  }
}
