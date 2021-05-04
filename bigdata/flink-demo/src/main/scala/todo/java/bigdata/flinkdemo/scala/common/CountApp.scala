package todo.java.bigdata.flinkdemo.scala.common

import org.apache.flink.api.common.accumulators.LongCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration
import org.apache.flink.core.fs.FileSystem.WriteMode
import org.apache.flink.streaming.api.scala.createTypeInformation

/**
 * Scala实现通过一个add操作累加最终的结果，在job执行后可以获取最终结果
 */
object CountApp {
  def main(args: Array[String]) {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val data = env.fromElements("hadoop", "spark", "flink", "pyspark", "storm")

    val info = data.map(new RichMapFunction[String, String]() {
      // step1：定义计数器
      val counter = new LongCounter()
      override def open(parameters: Configuration): Unit = {
        // step2: 注册计数器
        getRuntimeContext.addAccumulator("ele-counts-scala", counter)
      }
      override def map(in: String): String = {
        counter.add(1)
        in
      }
    })


    val filePath = this.getClass.getClassLoader.getResource("sink/scala/").getPath
    info.writeAsText(filePath, WriteMode.OVERWRITE).setParallelism(2)
    val jobResult = env.execute("CounterApp")
    // step3: 获取计数器
    val num = jobResult.getAccumulatorResult[Long]("ele-counts-scala")
    println("num: " + num)

  }
}
