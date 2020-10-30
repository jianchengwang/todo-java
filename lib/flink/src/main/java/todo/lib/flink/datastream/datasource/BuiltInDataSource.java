package todo.lib.flink.datastream.datasource;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.io.CsvInputFormat;
import org.apache.flink.api.java.io.RowCsvInputFormat;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.apache.flink.types.Row;

/**
 * @author wjc
 * @date 2020/10/30
 */
public class BuiltInDataSource {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStream<String> stream = getDataStreamSocket(env);
        stream.map(i -> i).print();
        env.execute("Flink BuiltInDataSource Test");
    }

    public static DataStream<String> getDataStreamBatch(StreamExecutionEnvironment env) {
        String[] elementInput = new String[]{"hello Flink", "Second Line"};
        DataStream<String> stream = env.fromElements(elementInput);
        return stream;
    }

    public static DataStream<String> getDataStreamLog(StreamExecutionEnvironment env) {
        String logPath = BuiltInDataSource.class.getClassLoader().getResource(".").getPath().replace("classes/java/main/", "") + "resources/main/test.txt";
        DataStream<String> stream = env.readTextFile(logPath);
        return stream;
    }

    public static DataStream<Row> getDataStreamCsv(StreamExecutionEnvironment env) {
        String csvPath = BuiltInDataSource.class.getClassLoader().getResource(".").getPath().replace("classes/java/main/", "") + "resources/main/test.csv";
        // 使用 RowCsvInputFormat 把每一行记录解析为一个 Row
        CsvInputFormat csvInput = new RowCsvInputFormat(
                new Path(csvPath),                                        // 文件路径
                new TypeInformation[]{Types.STRING, Types.STRING, Types.STRING},// 字段类型
                "\n",                                             // 行分隔符
                ",");                                            // 字段分隔符
        csvInput.setSkipFirstLineAsHeader(true);
        // 指定 CsvInputFormat, 监控csv文件(两种模式), 时间间隔是10ms
        DataStream<Row> stream = env.readFile(csvInput, csvPath, FileProcessingMode.PROCESS_CONTINUOUSLY, 10);
        return stream;
    }

    public static DataStream<String> getDataStreamSocket(StreamExecutionEnvironment env) {
        DataStream<String> stream = env.socketTextStream("localhost", 9999, "\n", 4);
        return stream;
    }
}