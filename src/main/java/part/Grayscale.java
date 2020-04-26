package part;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Grayscale {
    public static void main(String[] args) {
            transform();
    }
   private static void transform(){
       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
       Mat srcImage = Imgcodecs.imread("./data/yasuo.png");
       Mat dstImage = new Mat();
       Imgproc.cvtColor(srcImage, dstImage, Imgproc.COLOR_BGR2GRAY,0);
       Imgcodecs.imwrite("./data/yasuo2.png", dstImage);
   }

}
