package todo.java.bigdata.flinkdemo.scala.dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.core.fs.FileSystem.WriteMode

object DataSetSinkApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val data = 1 to 10
    val text = env.fromElements(data)

    val filePath = this.getClass.getClassLoader.getResource("sink/scala/sink_test.txt").getPath
    text.writeAsText(filePath, WriteMode.OVERWRITE)

    env.execute("SinkApp")
  }
}
