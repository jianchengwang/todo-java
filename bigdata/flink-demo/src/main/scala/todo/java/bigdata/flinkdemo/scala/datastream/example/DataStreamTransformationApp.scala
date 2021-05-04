package todo.java.bigdata.flinkdemo.scala.datastream.example

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object DataStreamTransformationApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

        filterFunction(env)
    //    unionFunction(env)

    env.execute("DataStreamTransformationApp")
  }

  /**
   * dataStream 流处理 union算子
   *
   * @param env
   */
  def unionFunction(env: StreamExecutionEnvironment) = {
    import org.apache.flink.api.scala._
    val data1 = env.addSource(new CustomNonParallelSourceFunction)
    val data2 = env.addSource(new CustomNonParallelSourceFunction)

    data1.union(data2).print().setParallelism(1)


  }

  /**
   * dataStream 流处理 filter算子
   *
   * @param env
   */
  def filterFunction(env: StreamExecutionEnvironment): Unit = {

    import org.apache.flink.api.scala._
    val data = env.addSource(new CustomNonParallelSourceFunction)

    data.map(x => x).filter(_ % 2 == 0).print().setParallelism(1)

  }
}
