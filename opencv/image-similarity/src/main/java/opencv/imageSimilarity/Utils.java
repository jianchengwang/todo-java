package opencv.imageSimilarity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	public static void writeFile(String fileName, byte[] data) throws IOException {
		if(null == data || data.length == 0) {
			return;
		}
		FileOutputStream fout = new FileOutputStream(fileName);
		fout.write(data);
		fout.flush();
		fout.close();
	}

	/**
	 * 读取数据
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static byte[] readStream(String filePath) throws Exception {
		InputStream in = new FileInputStream(filePath);
		byte[] ret = null;
		int BUF_L = 4096;
		byte[] buf = new byte[BUF_L];
		int l = 0;
		while((l = in.read(buf)) > 0) {
			if(ret == null) {
				ret = new byte[l];
				System.arraycopy(buf, 0, ret, 0, l);
			}else {
				int length = ret.length + l;
				byte[] tmp = new byte[length];
				System.arraycopy(ret, 0, tmp, 0, ret.length);
				System.arraycopy(buf, 0, tmp, ret.length, l);
				ret = tmp;
			}
		}
		in.close();
		return ret;
	}

	/**
	 * 计算 md5
	 * @param plainText
	 * @return
	 */
	public static String md5(byte[] data) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
