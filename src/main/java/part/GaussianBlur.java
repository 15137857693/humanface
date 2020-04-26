package part;


import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.Imgproc;

public class GaussianBlur {

    public static void main(String[] args) {
        try{
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            Mat src=Imgcodecs.imread("data/lenna.jpg");
            //读取图像到矩阵中
            if(src.empty()){
                throw new Exception("no file");
            }

            Mat dst = src.clone();
            //复制矩阵进入dst

            Imgproc.GaussianBlur(src,dst,new Size(13,13),10,10);
            //图像模糊化处理11
            Imgcodecs.imwrite("./data/gaussianblur1.jpg", dst);

            Imgproc.GaussianBlur(src,dst,new Size(31,5),80,3);
            //图像模糊化处理33
            Imgcodecs.imwrite("./data/gaussianblur2.jpg", dst);
        }catch(Exception e){
            System.out.println("例外：" + e);
        }

    }

}
