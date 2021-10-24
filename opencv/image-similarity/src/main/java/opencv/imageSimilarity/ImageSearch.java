package opencv.imageSimilarity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import opencv.imageSimilarity.utils.SiftUtils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import opencv.imageSimilarity.ssim.SsimCalculator;

public class ImageSearch {
	// 图片库的路径
	private String imageRepo;

	// 路径要以分隔符结尾
	private String tmpPath;

	public ImageSearch(String imageRepo) {
		this.imageRepo = imageRepo;
		this.tmpPath = System.getProperty("java.io.tmpdir");
		if(!this.tmpPath.endsWith(File.separator)) {
			this.tmpPath = this.tmpPath + File.separator;
		}
	}

	/**
	 * 搜索图片，返回相似度大于 0.5 的图片，并排序
	 * @param imagePath 图片路径
	 * @return
	 * @throws Exception
	 */
	public List<MatchImage> search(String inputImage) throws Exception{
		byte[] data = pretreament(inputImage);
		return searchAfterPretreament(data);
	}

	/**
	 * 搜索图片，返回相似度大于 0.5 的图片，并排序
	 * @param inputData 图片数据
	 * @return
	 * @throws Exception
	 */
	public List<MatchImage> search(byte[] inputData) throws Exception{
		byte[] data = pretreament(inputData);
		return searchAfterPretreament(data);
	}

	/**
	 * 搜索图片，返回相似度大于 0.5 的图片，并排序
	 * @param inputImg 预处理后的图片数据
	 * @return
	 * @throws Exception
	 */
	public List<MatchImage> searchAfterPretreament(String inputPath) throws Exception {
		return searchAfterPretreament(Utils.readStream(inputPath));
	}

	/**
	 * 搜索图片，返回相似度大于 0.5 的图片，并排序
	 * @param inputImg 预处理后的图片数据
	 * @return
	 * @throws Exception
	 */
	public List<MatchImage> searchAfterPretreament(byte[] inputImg) throws Exception {
		File[] fs = new File(imageRepo).listFiles();
		SsimCalculator sc = new SsimCalculator(inputImg);

		List<MatchImage> ret = new ArrayList<>();
		for(File f : fs) {
			double rate = sc.compareTo(f);
//			double rate = SiftUtils.siftCompare(inputImg, Utils.readStream(f.getAbsolutePath()));
			if(rate > 0.5) {
				MatchImage mi = new MatchImage();
				mi.setImageName(f.getName());
				mi.setImagePath(f.getAbsolutePath());
				mi.setMatchRate(rate);
				ret.add(mi);
			}
		}

		Collections.sort(ret);
		return ret;
	}

	/**
	 * 图像预处理
	 * @param inputData
	 * @return
	 * @throws Exception
	 */
	private byte[] pretreament(byte[] inputData) throws Exception {
		String id = UUID.randomUUID().toString();
		String fname = tmpPath + id + ".jpg";
		Utils.writeFile(fname, inputData);
		byte[] ret = pretreament(fname);

		new File(fname).delete();

		return ret;
	}

	/**
	 * 对图像进行预处理
	 * @param inputPath
	 * @return
	 * @throws Exception
	 */
	private byte[] pretreament(String inputPath) throws Exception {
		ImagePretreatment ip = new ImagePretreatment(inputPath);
		Mat img = ip.proc();

		String id = UUID.randomUUID().toString();
		String fname = tmpPath + id + ".jpg";
		Imgcodecs.imwrite(fname, img);
		byte[] ret = Utils.readStream(fname);

		new File(fname).delete();

		return ret;
	}
}
