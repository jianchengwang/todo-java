package todo.java.bigdata.flinkdemo.scala.utils

import scala.util.Random

object DBUtils {
  def getConection(): String = {
    new Random().nextInt(10) + ""
  }

  def returnConnection(connection:String): Unit ={

  }
}
