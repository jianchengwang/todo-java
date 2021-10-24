package opencv.imageSimilarity.utils;

import org.opencv.core.*;
import org.opencv.features2d.FlannBasedMatcher;
import org.opencv.features2d.SIFT;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wjc
 * @date 2021/10/24
 */
public class SiftUtils {
    public static double siftCompare(byte[] image1Bytes, byte[] image2Bytes) {

        Mat image1 = new Mat(200,200, CvType.CV_8UC1);
        Mat image2 = new Mat(200,200, CvType.CV_8UC1);

        image1.put(0,0,image1Bytes);
        image2.put(0,0,image2Bytes);

        Mat clone1=image1.clone();
        Mat src1=image1.clone();

        Mat clone2=image2.clone();
        Mat src2=image2.clone();

        Imgproc.GaussianBlur(clone1, clone1, new Size(3, 3), 0, 0);
//        Imgproc.cvtColor(clone1, clone1,Imgproc.COLOR_BGR2GRAY);

        Imgproc.GaussianBlur(clone2, clone2, new Size(3, 3), 0, 0);
//        Imgproc.cvtColor(clone2, clone2,Imgproc.COLOR_BGR2GRAY);

        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        Mat des1=new Mat();
        Mat des2=new Mat();
//	匹配的点数
        SIFT sift=SIFT.create(200);
        //提取对象关键点
//	sift.detect(clone1,keypoints1);
//	sift.detect(clone2,keypoints2);
        //提取描述子
//	sift.compute(clone1,keypoints1, des1);
//	sift.compute(clone2,keypoints2, des2);

        sift.detectAndCompute(clone1, new Mat(), keypoints1, des1);
        sift.detectAndCompute(clone2, new Mat(), keypoints2, des2);

        MatOfDMatch md=new  MatOfDMatch();
        FlannBasedMatcher matcher=FlannBasedMatcher.create();

        matcher.match(des1, des2, md);

        double maxDist = 0;
        double minDist = 50;

        DMatch[] mats = md.toArray();
        List<DMatch> bestMatches= new ArrayList<DMatch>();

        for (int i = 0; i < mats.length; i++) {
            double dist = mats[i].distance;
            if (dist < minDist) {
                minDist = dist;
            }
            if (dist > maxDist) {
                maxDist = dist;
            }
        }
        System.out.println("max_dist : "+maxDist);
        System.out.println("min_dist : "+minDist);

        double threshold = 3 * minDist;
        double threshold2 = 2 * minDist;

        if (threshold2 >= maxDist){
            threshold = minDist * 1.1;
        }
        else if (threshold >= maxDist){
            threshold = threshold2 * 1.4;
        }

        if(0d==threshold) {
            threshold=0.3*maxDist;
        }

        System.out.println("Threshold : "+threshold);

        for (int i = 0; i < mats.length; i++)
        {
            //distance越小,代表DMatch的匹配率越高,
            Double dist = (double) mats[i].distance;
            System.out.println(String.format(i + " match distance best : %s", dist));
            if (dist <= threshold)
            {
                bestMatches.add(mats[i]);
                System.out.println(String.format(i + " best match added : %s", dist));
            }
        }
        md.fromList(bestMatches);
        return Double.valueOf(bestMatches.size())/mats.length;
    }
}
