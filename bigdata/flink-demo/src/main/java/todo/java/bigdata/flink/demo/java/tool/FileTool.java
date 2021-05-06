package todo.java.bigdata.flink.demo.java.tool;

import org.apache.flink.core.fs.Path;
import todo.java.bigdata.flink.demo.java.datastream.datasource.InputFormatDataSet;

import java.io.File;
import java.net.URL;

/**
 * @author wjc
 * @date 2020/11/1
 */
public class FileTool {
    public static String getResourceFileUrl(String path) {
        return getResourceFilPath(path).getPath();
    }

    public static Path getResourceFilPath(String path) {
        URL fileUrl = InputFormatDataSet.class.getClassLoader().getResource(path);
        Path localFilePath = Path.fromLocalFile(new File(fileUrl.getPath()));
        return localFilePath;
    }
}
