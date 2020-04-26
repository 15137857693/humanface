package part;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
public class HaarFaceDetect {



        public static void main(String[] args)
        {
            // TODO Auto-generated method stub
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            System.out.println("\nRunning FaceDetector");
            CascadeClassifier faceDetector = new CascadeClassifier();
            faceDetector.load(
                    //这里是你opencv的安装地址
                    "D:\\opencv\\windows\\opencv4.2.0\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
            Mat image = Imgcodecs.imread("./data/facedetect.jpg");

            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(image, faceDetections);
            System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
            for (Rect rect : faceDetections.toArray())
            {
                Imgproc.rectangle(image, new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
            }

            String filename = "./data/facedetect_2.jpg";
            Imgcodecs.imwrite(filename, image);
        }

}
