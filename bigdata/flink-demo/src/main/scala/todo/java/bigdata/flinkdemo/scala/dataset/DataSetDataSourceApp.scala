package todo.java.bigdata.flinkdemo.scala.dataset

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.configuration.Configuration
import org.apache.flink.util.Collector

object DataSetDataSourceApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    fromCollection(env)
    textFile(env)

    val filePath = this.getClass.getClassLoader.getResource("data/input.txt").getPath
    val line: DataSet[String] = env.readTextFile(filePath)
    import org.apache.flink.api.scala._
    val value: DataSet[String] = line.flatMap(x => {
      x.split(" ")
    })
    println("----")
    line.flatMap(new MyFun).print()
  }

  class MyFun extends FlatMapFunction[String, String] {
    override def flatMap(value: String, out: Collector[String]): Unit = {
      val s = value.split(" ")
      for (e <- s) {
        out.collect(e)
      }
    }
  }

  /**
   * 集合datasource
   * @param env
   */
  def fromCollection(env: ExecutionEnvironment):Unit = {
    import org.apache.flink.api.scala._
    val data = 1 to 10
    env.fromElements(data).print()
  }

  /**
   * 文件/文件夹datasource
   * @param env
   */
  def textFile(env: ExecutionEnvironment):Unit = {
    //可以直接指定文件夹
    env.readTextFile(this.getClass.getClassLoader.getResource("data/input.txt").getPath).print()
  }

  /**
   * csv  datasource
   * @param env
   */
  def csvFile(env: ExecutionEnvironment): Unit = {
    import org.apache.flink.api.scala._
    val filePath = ""
    //[T]可以指定为tuple或者pojo case class,可以指定需要的列或在参数重指定 includedFields = Array(0,1)列
    //文件路径     是否忽略第一行
    env.readCsvFile[(String, Int, String)](filePath, ignoreFirstLine = true).print()

  }

  /**
   * 递归，嵌套文件 datasource
   * @param env
   */
  def readRecurseFiles(env: ExecutionEnvironment): Unit = {

    val filePath = ""
    val parameters = new Configuration

    parameters.setBoolean("recursive.file.enumeration", true)

    env.readTextFile(filePath).withParameters(parameters).print()

  }

  /**
   * 读压缩文件
   *
   * @param env
   */
  def readCompressionFiles(env: ExecutionEnvironment): Unit = {

    val filePath = ""
    env.readTextFile(filePath).print()
  }
}
