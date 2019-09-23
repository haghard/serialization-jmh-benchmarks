package benchmark

import com.domain.users.pb.protocol.TypePB

object ProtoSupport {

  def serialize(ev: TypePB): Array[Byte] =
    ev.toByteArray

  def deserialize(bts: Array[Byte]): TypePB =
    TypePB.parseFrom(bts)

  def roundTrip(protoObj: TypePB): TypePB = {
    val bts = serialize(protoObj)
    deserialize(bts)
  }
}
