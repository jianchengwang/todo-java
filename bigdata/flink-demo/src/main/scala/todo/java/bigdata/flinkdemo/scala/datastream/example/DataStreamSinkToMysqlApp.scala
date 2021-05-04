package todo.java.bigdata.flinkdemo.scala.datastream.example

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import todo.java.bigdata.flinkdemo.scala.common.Person

object DataStreamSinkToMysqlApp {
  def main(args: Array[String]):Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9999)

    val personStream = text.map(new MapFunction[String, Person] {
      override def map(value: String): Person = {
        val split = value.split(",")
        Person(Integer.parseInt(split(0)), split(1), Integer.parseInt(split(2)))
      }
    })
    personStream.addSink(new CustomSinkToMysql)

    env.execute("DataStreamSinkToMysqlApp")
  }
}
