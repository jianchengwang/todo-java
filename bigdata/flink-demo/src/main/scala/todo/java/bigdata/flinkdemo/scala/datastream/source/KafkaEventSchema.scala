package todo.java.bigdata.flinkdemo.scala.datastream.source

import net.sf.json.JSONObject
import org.apache.flink.api.common.serialization.{DeserializationSchema, SerializationSchema}
import org.apache.flink.api.common.typeinfo.TypeInformation

class KafkaEventSchema extends DeserializationSchema[JSONObject] with SerializationSchema[JSONObject]{

  override def deserialize(message: Array[Byte]): JSONObject = {
    JSONObject.fromObject(new String(message))
  }

  override def isEndOfStream(nextElement: JSONObject): Boolean = false

  override def serialize(element: JSONObject): Array[Byte] = {
    element.toString().getBytes()
  }

  override def getProducedType: TypeInformation[JSONObject] = {
    TypeInformation.of(classOf[JSONObject])
  }
}
