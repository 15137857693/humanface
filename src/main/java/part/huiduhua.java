package part;
import java.util.LinkedList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
public class huiduhua {


        //静态代码块加载动态链接库
        static {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        }

        public static void main(String[] args) {

            /*
             * IMREAD_UNCHANGED = -1 ：不进行转化，比如保存为了16位的图片，读取出来仍然为16位。
             * IMREAD_GRAYSCALE = 0 ：进行转化为灰度图，比如保存为了16位的图片，读取出来为8位，类型为CV_8UC1。
             * IMREAD_COLOR = 1 ：进行转化为三通道图像。
             * IMREAD_ANYDEPTH = 2 ：如果图像深度为16位则读出为16位，32位则读出为32位，其余的转化为8位。
             * IMREAD_ANYCOLOR = 4 ：图像以任何可能的颜色格式读取
             * IMREAD_LOAD_GDAL = 8 ：使用GDAL驱动读取文件，GDAL(Geospatial Data Abstraction
             * Library)是一个在X/MIT许可协议下的开源栅格空间数据转换库。它利用抽象数据模型来表达所支持的各种文件格式。
             *	它还有一系列命令行工具来进行数据转换和处理。
             */

            Mat src = Imgcodecs.imread("./data/huiduhua.png",0);
            //Imgproc.resize(src, src, new Size(src.cols()/2,src.rows()/2));

            HighGui.imshow("原图", src);
            HighGui.waitKey();

            ImgCalcHist(src,"原图直方图");

            Mat dst = new Mat();
            //直方图均衡化,该算法对亮度进行归一化并增加图像的对比度。
            Imgproc.equalizeHist(src,dst);
            HighGui.imshow("直方图均衡化", dst);
            HighGui.waitKey();

            ImgCalcHist(dst,"直方图均衡化后的直方图");

        }

        /**
         * 直方图
         * @param src
         * @param windowName
         */
        public static void ImgCalcHist(Mat src,String windowName) {

            List<Mat> matList = new LinkedList<Mat>();
            matList.add(src);

            Mat histogram = new Mat();

            MatOfFloat ranges=new MatOfFloat(0,256);
            MatOfInt histSize = new MatOfInt(300);

            /*
             * 计算直方图
             * List<Mat> images：输入图像
             * MatOfInt channels：需要统计直方图的第几通道
             * Mat mask：掩膜，，计算掩膜内的直方图
             * Mat hist:输出的直方图数组
             * MatOfInt histSize：指的是直方图分成多少个区间，就是bin的个数
             * MatOfFloat ranges： 统计像素值得区间
             */
            Imgproc.calcHist(matList,new MatOfInt(0),new Mat(),histogram,histSize ,ranges);
            //创建直方图面板
            Mat histImage = Mat.zeros( 150, (int)histSize.get(0, 0)[0], CvType.CV_8UC1);
            //归一化直方图 详见https://blog.csdn.net/ren365880/article/details/103923813
            Core.normalize(histogram, histogram, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() );
            //绘制直线 详见：https://blog.csdn.net/ren365880/article/details/103952856
            for( int i = 0; i < (int)histSize.get(0, 0)[0]; i++ ){
                Imgproc.line(histImage,new org.opencv.core.Point(i, histImage.rows()),new org.opencv.core.Point(i, histImage.rows()-Math.round( histogram.get(i,0)[0])) ,new Scalar( 255, 255, 255),1, 8, 0 );
            }
            HighGui.imshow(windowName, histImage);
            HighGui.waitKey();
        }


    }




