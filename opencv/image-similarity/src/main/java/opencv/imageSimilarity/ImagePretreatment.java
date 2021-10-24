package opencv.imageSimilarity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author xiezhengbiao
 *
 */
public class ImagePretreatment {
	// 边缘检测的点数在 3000 和 4000 之间，太多边缘太复杂，太少边缘太简单
	// 可以根据实际严格测试进行调节
	private static final int EDGE_MAX_POINT_NUM = 4000;
	private static final int EDGE_MIN_POINT_NUM = 3000;

	// 对比度调整参数
	private static final float CONTRAST_ALPHA = 0.3f;
	private static final float CONTRAST_BATE = 0f;

	// 标准化图片大
	private static final int IMG_WIDTH = 200;
	private static final int IMG_HEIGHT = 200;

	// 背景裁剪的圆形主体窗口的半径
	private static final int OBJECT_CIRCLE_RADIO = 90;

	// 灰度图像的均值一致化的大小
	private static final int IMG_GRAY_AVG_CONSISTENCY = 100;

	private String imagePath;

	// 图像预处理的信息
	private Map<String, Object> pretreamentInfo = new HashMap<>();

	private String cutPath = "";

	public ImagePretreatment(String imagePath) {
		this.imagePath = imagePath;

		// 保存基本处理信息
		pretreamentInfo.put("image_path", imagePath);
		pretreamentInfo.put("image_md5", imagePath);
		pretreamentInfo.put("edge_max_point_num", EDGE_MAX_POINT_NUM);
		pretreamentInfo.put("edge_min_point_num", EDGE_MIN_POINT_NUM);
		pretreamentInfo.put("contrast_alpha", CONTRAST_ALPHA);
		pretreamentInfo.put("contrast_bate", CONTRAST_BATE);
		pretreamentInfo.put("out_img_width", IMG_WIDTH);
		pretreamentInfo.put("out_img_height", IMG_HEIGHT);
		pretreamentInfo.put("object_circle_radio", OBJECT_CIRCLE_RADIO);
		pretreamentInfo.put("gray_consistency", IMG_GRAY_AVG_CONSISTENCY);
	}

	/**
	 * 图像预处理
	 * @param imagePath
	 * @return
	 * @throws Exception
	 */
	public Mat proc() throws Exception {
		byte[] data = Utils.readStream(imagePath);
		pretreamentInfo.put("image_md5", Utils.md5(data));

		// 提取图片主体，并裁剪成 200px*200px 大小
		Mat cutImg = imageCutAndResize(IMG_WIDTH, IMG_HEIGHT);

		Mat tmpImg = new Mat();
		cutImg.convertTo(tmpImg, -1, CONTRAST_ALPHA, CONTRAST_BATE);
		// 使用 RTCP 算法保留对比度去色，将图像转换成灰度图
		// 移除图像背景，保留半径为 90 的圆形区域，并将图像进行灰度的均值一致化
		Mat gray = removeBackground(rgb2GrayRTCP(tmpImg));

		// 提升图像的对比度
		//gray.convertTo(tmpImg, -1, CONTRAST_ALPHA, CONTRAST_BATE);

		if(null != cutPath && cutPath.length() > 0) {
			String name = new File(imagePath).getName();
			Imgcodecs.imwrite(cutPath + name, gray);
		}

		// 提取图像边缘
		return edgeDetection(tmpImg);
	}

	/**
	 * 图像边缘检测
	 * @param img
	 * @return
	 */
	private Mat edgeDetection(Mat img) {
		Mat b = new Mat();
		boolean find = false;
		int arg1 = 0;
		int arg2 = 0;
		int arg3 = 3;
		int edgeSum = 0;
		// 调节边缘检测算法的参数，直到得到的结果符合要求
		for(int i = 200; i > 10; i-=20) {
			if(find) {
				break;
			}
			for(int j = 200; j > 10; j-=20) {
				Imgproc.Canny(img, b, i, j, 3, false);
				int sum3 = sumEdge(b);
				arg1 = i;
				arg2 = j;
				if(sum3 > EDGE_MIN_POINT_NUM && sum3 < EDGE_MAX_POINT_NUM) {
					find = true;
					arg3 = 3;
					edgeSum = sum3;
					break;
				}
				Imgproc.Canny(img, b, i, j, 7, false);
				int sum7 = sumEdge(b);
				if(sum7 > EDGE_MIN_POINT_NUM && sum7 < EDGE_MAX_POINT_NUM) {
					find = true;
					arg3 = 7;
					edgeSum = sum7;
					break;
				}
			}
		}
		// canny 边缘检测算法的参数
		pretreamentInfo.put("canny_arg", new int[] {arg1, arg2, arg3});
		// 边缘线的点数
		pretreamentInfo.put("edge_point_sum", edgeSum);
		return b;
	}

	/**
	 * 计算图像边缘点数
	 * @param b
	 * @return
	 */
	private int sumEdge(Mat b) {
		int w = b.width();
		int h = b.height();
		int sum = 0;
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				double v = b.get(i, j)[0];
				if(v > 250) {
					sum++;
				}
			}
		}
		return sum;
	}

	/**
	 * 移除背景，并进行图像的均值一致化
	 * @param img
	 * @return
	 */
	private Mat removeBackground(Mat img) {
		int w = img.width();
		int h = img.height();

		Mat nImg = new Mat();
		img.copyTo(nImg);
		double sum = 0;
		int count = 0;
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				int px = i - 100;
				int py = j - 100;
				// 保留半径为 90 的圆形区域，其余区域做为背景置成白色
				if(px * px + py * py > OBJECT_CIRCLE_RADIO * OBJECT_CIRCLE_RADIO) {
					nImg.put(i, j, 255);
				}else {
					sum += img.get(i, j)[0];
					count++;
				}
			}
		}

		double avg = sum / count;
		// 保存灰度均值
		pretreamentInfo.put("gray_avg", avg);

		// 颜色均值一致化到 100
		double rate = IMG_GRAY_AVG_CONSISTENCY / avg;
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				int px = i - 100;
				int py = j - 100;
				if(px * px + py * py <= OBJECT_CIRCLE_RADIO * OBJECT_CIRCLE_RADIO) {
					double v = img.get(i, j)[0];
					nImg.put(i, j, v * rate);
				}
			}
		}
		return nImg;
	}

	/**
	 * 保留对比度去色（RTCP算法）
	 * @param img
	 * @return
	 */
	private Mat rgb2GrayRTCP(Mat img) {
		Mat tmp0Img = new Mat();
		// 压缩图片到 64px，提高计算速度（不改变原图大小）
		int size = 64;
		Imgproc.resize(img, tmp0Img, new Size(size, size));

		Mat imgArray = tmp0Img.reshape(0, 1);
		Mat imgShuffle = imgArray.clone();
		Core.randShuffle(imgShuffle, 1.0);

		Mat imgArrayLab = new Mat();
		Mat imgShuffleLab = new Mat();

		Imgproc.cvtColor(imgArray, imgArrayLab, Imgproc.COLOR_BGR2Lab);
		Imgproc.cvtColor(imgShuffle, imgShuffleLab, Imgproc.COLOR_BGR2Lab);

		imgArray.convertTo(imgArray, CvType.CV_64FC3, 1 / 255.0);
		imgShuffle.convertTo(imgShuffle, CvType.CV_64FC3, 1 / 255.0);
		imgArrayLab.convertTo(imgArrayLab, CvType.CV_64FC3, 1 / 255.0);
		imgShuffleLab.convertTo(imgShuffleLab, CvType.CV_64FC3, 1 / 255.0);

		int arraySize = imgArray.cols();
		List<Double> E = new ArrayList<>();
		Mat W = getGrayWeight();
		int eSize = W.rows();
		double sigma = 0.05;

		for(int i = 0; i < arraySize; i++) {
			double delta = Math.sqrt(
					Math.pow(imgArrayLab.get(0, i)[0] - imgShuffleLab.get(0, i)[0], 2) +
					Math.pow(imgArrayLab.get(0, i)[1] - imgShuffleLab.get(0, i)[1], 2) +
					Math.pow(imgArrayLab.get(0, i)[2] - imgShuffleLab.get(0, i)[2], 2)
		            );
			if(delta < 0.05) {
				continue;
			}
			for(int n = 0; n < eSize; n++) {
				double deltaG = (imgArray.get(0, i)[0] - imgShuffle.get(0, i)[0])*W.get(n, 0)[0]
		                + (imgArray.get(0, i)[1] - imgShuffle.get(0, i)[1])*W.get(n, 1)[0]
		                + (imgArray.get(0, i)[2] - imgShuffle.get(0, i)[2])*W.get(n, 2)[0];

	            double tmpE = Math.log(
	            			Math.exp(-Math.pow(deltaG + delta, 2) / (2 * Math.pow(sigma, 2))) +
	            			Math.exp(-Math.pow(deltaG - delta, 2) / (2 * Math.pow(sigma, 2)))
	            		);

	            if(E.size() <= n || E.get(n) == null) {
	            	E.add(0.0);
	            }
	            E.set(n, E.get(n) + tmpE);
			}
		}

		double maxE = 0;
		int maxIndex = 0;
		for(int i = 0; i < eSize; i++) {
			if(maxE < E.get(i)) {
				maxE = E.get(i);
				maxIndex = i;
			}
		}

		Mat gray = new Mat(img.size(), CvType.CV_8UC1);

		int w = img.width();
		int h = img.height();

		// 保存 rtcp 算法的计算权重
		pretreamentInfo.put("rtcp_weight", new double[] {W.get(maxIndex, 0)[0], W.get(maxIndex, 1)[0], W.get(maxIndex, 2)[0]});

		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				gray.put(j, i,
					W.get(maxIndex, 0)[0] * img.get(j, i)[0] +
					W.get(maxIndex, 1)[0] * img.get(j, i)[1] +
					W.get(maxIndex, 2)[0] * img.get(j, i)[2]
				);
			}
		}

		return gray;
	}

	/**
	 * 图片主体裁剪及大小调整
	 * @param imgData
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	private Mat imageCutAndResize(int width, int height) throws Exception {
		// 主体检测
		JSONObject jo = ObjectDetect.detect(imagePath);
		System.out.println(jo);
		Mat img = Imgcodecs.imread(imagePath);

		pretreamentInfo.put("img_width", img.width());
		pretreamentInfo.put("img_height", img.height());
		pretreamentInfo.put("object_detect_info", jo);

		JSONObject position = jo.getJSONObject("result");

		int x = position.getInt("left");
		int y = position.getInt("top");
		int w = position.getInt("width");
		int h = position.getInt("height");
		h = h > w ? w : h;

		Rect rect = new Rect(x, y, w, h);
		// 裁剪成 rect 形状
		Mat rectImg = new Mat(img, rect);
		Mat tmpImg = new Mat();

		Imgproc.resize(rectImg, tmpImg, new Size(width, height));

		return tmpImg;
	}


	/**
	 * 灰度阈值
	 * @return
	 */
	private Mat getGrayWeight() {
		double[] a = new double[]{0, 0, 1.0000,
    	        0, 0.1000, 0.9000,
    	        0, 0.2000, 0.8000,
    	        0, 0.3000, 0.7000,
    	        0, 0.4000, 0.6000,
    	        0, 0.5000, 0.5000,
    	        0, 0.6000, 0.4000,
    	        0, 0.7000, 0.3000,
    	        0, 0.8000, 0.2000,
    	        0, 0.9000, 0.1000,
    	        0, 1.0000, 0,
    	        0.1000, 0, 0.9000,
    	        0.1000, 0.1000, 0.8000,
    	        0.1000, 0.2000, 0.7000,
    	        0.1000, 0.3000, 0.6000,
    	        0.1000, 0.4000, 0.5000,
    	        0.1000, 0.5000, 0.4000,
    	        0.1000, 0.6000, 0.3000,
    	        0.1000, 0.7000, 0.2000,
    	        0.1000, 0.8000, 0.1000,
    	        0.1000, 0.9000, 0,
    	        0.2000, 0, 0.8000,
    	        0.2000, 0.1000, 0.7000,
    	        0.2000, 0.2000, 0.6000,
    	        0.2000, 0.3000, 0.5000,
    	        0.2000, 0.4000, 0.4000,
    	        0.2000, 0.5000, 0.3000,
    	        0.2000, 0.6000, 0.2000,
    	        0.2000, 0.7000, 0.1000,
    	        0.2000, 0.8000, 0,
    	        0.3000, 0, 0.7000,
    	        0.3000, 0.1000, 0.6000,
    	        0.3000, 0.2000, 0.5000,
    	        0.3000, 0.3000, 0.4000,
    	        0.3000, 0.4000, 0.3000,
    	        0.3000, 0.5000, 0.2000,
    	        0.3000, 0.6000, 0.1000,
    	        0.3000, 0.7000, 0.0000,
    	        0.4000, 0, 0.6000,
    	        0.4000, 0.1000, 0.5000,
    	        0.4000, 0.2000, 0.4000,
    	        0.4000, 0.3000, 0.3000,
    	        0.4000, 0.4000, 0.2000,
    	        0.4000, 0.5000, 0.1000,
    	        0.4000, 0.6000, 0.0000,
    	        0.5000, 0, 0.5000,
    	        0.5000, 0.1000, 0.4000,
    	        0.5000, 0.2000, 0.3000,
    	        0.5000, 0.3000, 0.2000,
    	        0.5000, 0.4000, 0.1000,
    	        0.5000, 0.5000, 0,
    	        0.6000, 0, 0.4000,
    	        0.6000, 0.1000, 0.3000,
    	        0.6000, 0.2000, 0.2000,
    	        0.6000, 0.3000, 0.1000,
    	        0.6000, 0.4000, 0.0000,
    	        0.7000, 0, 0.3000,
    	        0.7000, 0.1000, 0.2000,
    	        0.7000, 0.2000, 0.1000,
    	        0.7000, 0.3000, 0.0000,
    	        0.8000, 0, 0.2000,
    	        0.8000, 0.1000, 0.1000,
    	        0.8000, 0.2000, 0.0000,
    	        0.9000, 0, 0.1000,
    	        0.9000, 0.1000, 0.0000,
    	        1.0000, 0, 0 };
    	Mat m = Mat.zeros(66, 3, 0);
    	for(int i = 0; i < a.length; i++) {
    		m.put(i / 3, i % 3, a[i]);
    	}

    	return m;
	}

	public String getCutPath() {
		return cutPath;
	}

	public void setCutPath(String cutPath) {
		this.cutPath = cutPath;
	}


}
