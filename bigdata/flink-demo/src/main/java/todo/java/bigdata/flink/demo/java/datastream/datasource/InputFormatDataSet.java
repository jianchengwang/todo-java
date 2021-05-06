package todo.java.bigdata.flink.demo.java.datastream.datasource;

import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.PojoCsvInputFormat;
import org.apache.flink.api.java.operators.JoinOperator;
import org.apache.flink.api.java.typeutils.PojoTypeInfo;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.core.fs.Path;
import todo.java.bigdata.flink.demo.java.common.model.Score;
import todo.java.bigdata.flink.demo.java.common.model.User;
import todo.java.bigdata.flink.demo.java.tool.FileTool;

/**
 * 使用 CsvInputFormat 创建模拟数据源  使用join合并两个dataSet
 * @author wjc
 * @date 2020/11/1
 */
public class InputFormatDataSet {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        // item dataSet
        String scoreCsvPath = "data/test_user_score.csv";
        String[] scoreField = new String[]{"userId", "score"};
        DataSet<Score> scores = getSource(env, scoreCsvPath, scoreField, Score.class);

        // info dataSet
        String userCsvPath = "data/test_user_info.csv";
        String[] userField = new String[]{"id", "name"};
        DataSet<User> users = getSource(env, userCsvPath, userField, User.class);
        // 关联两个dataset
        JoinOperator.DefaultJoin<Score, User> dataSet = scores.join(users).where("userId").equalTo("id");
        // 使用 joinFunction 处理合并后的两个dataSet
        dataSet.with((JoinFunction<Score, User, String>) (score, user) -> "user ID:" + user.getId() + " score:" + score.getScore()).print();
    }

    private static <T> DataSet<T> getSource(ExecutionEnvironment env, String path, String[] fieldOrder, Class<T> type) {
        // 本地文件路径
        Path filePath = FileTool.getResourceFilPath(path);
        // 抽取  TypeInformation，是一个 PojoTypeInfo
        PojoTypeInfo<T> pojoType = (PojoTypeInfo<T>) TypeExtractor.createTypeInfo(type);
        // 由于 Java 反射抽取出的字段顺序是不确定的，需要显式指定下文件中字段的顺序
        // 创建 PojoCsvInputFormat
        PojoCsvInputFormat<T> csvInput = new PojoCsvInputFormat<>(filePath, pojoType, fieldOrder);
        csvInput.setSkipFirstLineAsHeader(true);
        return env.createInput(csvInput, pojoType);
    }
}
