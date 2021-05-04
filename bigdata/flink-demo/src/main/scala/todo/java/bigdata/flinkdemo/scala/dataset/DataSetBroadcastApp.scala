package todo.java.bigdata.flinkdemo.scala.dataset

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.{ExecutionEnvironment, createTypeInformation}
import org.apache.flink.configuration.Configuration

import scala.collection.mutable

/**
 * Flink支持广播变量，就是将数据广播到具体的taskmanager上，数据存储在内存中，这样可以减缓大量的shuffle操作
 * 1、准备需要广播的数据集
 * 2、广播数据
 * 3、获取广播数据
 */
object DataSetBroadcastApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val toBroadcast = env.fromElements(1, 2, 3)
    val data = env.fromElements("a", "b")
    val result = data.map(new RichMapFunction[String, String]() {
      var mList: mutable.Buffer[String] = _
      override def open(config: Configuration): Unit = {
        // 3. Access the broadcast DataSet as a Collection
        import scala.collection.JavaConverters._
        mList = getRuntimeContext().getBroadcastVariable[String]("broadcastSetName").asScala
      }
      def map(in: String): String = {
        in + "--->广播数据" + mList.toString()
      }
    }).withBroadcastSet(toBroadcast, "broadcastSetName") // 2. Broadcast the DataSet
    result.print()
  }
}
