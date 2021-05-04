package todo.java.bigdata.flinkdemo.scala.datastream.example

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import todo.java.bigdata.flinkdemo.scala.common.Person

import java.sql.{Connection, DriverManager, PreparedStatement}

class CustomSinkToMysql extends RichSinkFunction[Person] {
  private[datastream] var connection: Connection = _
  private[datastream] var ps: PreparedStatement = _

  /**
   * 获取数据库连接
   */
  def getConnection() = {
    DriverManager.getConnection("jdbc:mysql://localhost122.51.87.176:13306/test?user=root&password=Wjc123456")
  }

  /**
   * 在open方法中建立connection
   *
   * @param parameters
   * @throws Exception
   */
  @throws[Exception]
  override def open(parameters: Configuration): Unit = {
    super.open(parameters)
    connection = getConnection
    val sql = "insert into t_person(id,name,age) values(?,?,?)";
    ps = connection.prepareStatement(sql)
    println("open")
  }
  /**
   * 在close方法中要释放资源
   *
   * @throws Exception
   */
  @throws[Exception]
  override def close(): Unit = {
    super.close()
    if(ps != null) ps.close()
    if(connection != null) connection.close()
  }

  /**
   * 每条记录插入时调用一次
   *
   * @param value
   * @param context
   * @throws Exception
   */
  override def invoke(value: Person, context: SinkFunction.Context): Unit = {
    println("invoke-----")
    ps.setInt(1, value.id)
    ps.setString(2, value.name)
    ps.setInt(3, value.age)
    ps.executeUpdate
  }
}
