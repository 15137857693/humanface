package test;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;


import javax.swing.*;

public class CatchPhoto {
    static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

     public static void main(String[] args) throws Exception, InterruptedException
     {
         OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
         grabber.start();   //开始获取摄像头数据
         CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
         canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         canvas.setAlwaysOnTop(true);
         int ex = 0;
         while(true)
         {
             if(!canvas.isDisplayable())
             {//窗口是否关闭
                 grabber.stop();//停止抓取
                 System.exit(2);//退出
                 break;
             }
             canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
            //opencv_core.Mat mat = converter.convertToMat(grabber.grabFrame());
             //opencv_core.Mat mat = converter.convertToMat(grabber.grabFrame());
             ex++;
             //opencv_imgcodecs.imwrite("./data/rigester/yasuo2.png", mat);
             Thread.sleep(200);//50毫秒刷新一次图像
         }
     }

   /* public String getBase64Face() throws FrameGrabber.Exception, InterruptedException{
        // 摄像机
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        // 格式转换器
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        // 图片帧
        Frame frame = null;
        try {
            grabber.start();
            frame = grabber.grab();
            grabber.close();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        // 转换格式后存储
        opencv_core.Mat mat = converter.convert(frame);
        String filename = "D:\\WorkPlace\\cloud\\src\\main\\resources\\temp\\" +
                CryptoUtil.getUUID() + ".png";
        opencv_imgcodecs.imwrite(filename, mat);

        // 读取文件到byte[]
        FileInputStream imageStream = null;
        byte[] image = null;
        try {
            imageStream = new FileInputStream(filename);
            image = new byte[imageStream.available()];
            imageStream.read(image);
            imageStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BASE64 编码
        String result = Base64.getEncoder().encodeToString(image);
        return result;
    }*/
}
