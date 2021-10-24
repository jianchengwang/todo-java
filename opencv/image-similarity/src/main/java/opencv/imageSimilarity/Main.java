package opencv.imageSimilarity;

import java.io.File;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {

	public static void main(String[] args) throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String repo = "D:\\tmp\\Desk\\shou_pre\\";
		String input = "D:\\tmp\\Desk\\shou\\";
		buildRepo(input, repo);

		String searchInput = "D:\\tmp\\Desk\\test2_pre.jpg";
		ImageSearch is = new ImageSearch(repo);
		List<MatchImage> match = is.search(searchInput);
		for(MatchImage mi : match) {
			System.out.println(mi);
		}
	}

	public static void buildRepo(String inputPath, String outputPath) throws Exception {
		File[] fs = new File(inputPath).listFiles();
		for(File f : fs) {
			try {
//				imageResize(f.getAbsolutePath());
				ImagePretreatment ip = new ImagePretreatment(f.getAbsolutePath());
				ip.setCutPath("D:\\tmp\\Desk\\shou_cut\\");
				Mat img = ip.proc();
				String fname = f.getName();
				Imgcodecs.imwrite(outputPath + fname, img);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void imageResize(String path) {
		Mat mat = Imgcodecs.imread(path);
		int width = mat.width();
		int height = mat.height();
		Mat dst = new Mat();
		Imgproc.resize(mat, dst, new Size(1000, 1000 * ((double)height / (double)width)));

		Imgcodecs.imwrite(path, dst);
	}
}
