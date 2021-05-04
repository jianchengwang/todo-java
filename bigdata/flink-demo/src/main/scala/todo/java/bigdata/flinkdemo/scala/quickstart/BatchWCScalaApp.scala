package todo.java.bigdata.flinkdemo.scala.quickstart

import org.apache.flink.api.scala.ExecutionEnvironment

import scala.sys.env

object BatchWCScalaApp {
  def main(args: Array[String]): Unit = {

    val inputTxtFilePath = this.getClass.getClassLoader.getResource("data/input.txt").getPath
    val env = ExecutionEnvironment.getExecutionEnvironment
    val text = env.readTextFile(inputTxtFilePath)

    // 引入隐式转换
    import org.apache.flink.api.scala._

    text.flatMap(_.toLowerCase.split("\t"))
      .filter(_.nonEmpty)
      .map((_, 1))
      .groupBy(0)
      .sum(1).print()
  }
}
