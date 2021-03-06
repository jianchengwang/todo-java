package todo.java.bigdata.flinkdemo.scala.datastream.example

import org.apache.flink.streaming.api.functions.source.SourceFunction

/**
 * 自定义source非并行
 */
class CustomNonParallelSourceFunction extends SourceFunction[Long] {
  var count = 1L
  var isRunning = true

  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    while (isRunning) {
      ctx.collect(count)
      count += 1
      Thread.sleep(1000)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
  }
}
