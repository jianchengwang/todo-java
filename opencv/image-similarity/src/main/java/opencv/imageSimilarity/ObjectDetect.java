package opencv.imageSimilarity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.baidu.aip.imageclassify.AipImageClassify;

/**
 * TODO：添加 init 方法，读取配置
 * @author xiezhengbiao
 *
 */
public class ObjectDetect {
	// TODO：以下几项需要可以配置
	public static final String APP_ID = "24993773";
    public static final String API_KEY = "5Ydo4QcnOc3dqOoGnUleZpbc";
    public static final String SECRET_KEY = "jqY1Zjj7PEVMm4nOfM42ENx8nhGYRrA5";

    // 缓存文件 TODO：可配置
    private static final String CACHE_FILE = "D:\\detectcache";

	private static Map<String, String> caches = new HashMap<>();

	static {
		// 缓存调用，降低百度接口的调用频率
		if(new File(CACHE_FILE).exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(CACHE_FILE));
				String line = null;
				while((line = br.readLine()) != null) {
					if(line.length() > 0 && line.indexOf("@@") > 0) {
						String[] lineS = line.split("@@");
						caches.put(lineS[0], lineS[1]);
					}
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 主体检测
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static JSONObject detect(String path) throws Exception {
		String cache = caches.get(path);
		if(null == cache || cache.length() == 0) {
			 JSONObject result = objectDetect(Utils.readStream(path));
			 if(null != result && null != result.get("result")) {
				 write(path, result.toString());
				 return result;
			 }
		}
		return new JSONObject(cache);
	}

	/**
	 * 图像主体检测，返回坐标，格式如下
	 * {"result":{"top":153,"left":186,"width":1438,"height":1056},"log_id":1448912147717433966}
	 * @param imageData
	 * @return
	 */
	private static JSONObject objectDetect(byte[] imageData) {
		AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        HashMap<String, String> options = new HashMap<>();
        options.put("with_face", "1");
        // 调用百度的主体检测接口，返回主体范围
        return client.objectDetect(imageData, options);
	}

	/**
	 * 缓存写入：写入格式：“path@@result”
	 * @param path
	 * @param result
	 */
	private static void write(String path, String result) {
		caches.put(path, result);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(CACHE_FILE, true));
			bw.newLine();
			bw.write(path + "@@" + result);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
