package method;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import main.MainWindow;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.presets.opencv_core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpencvTools {
    static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
    public static void OpenCamera()throws Exception, InterruptedException{
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//0表示本机摄像头  当然这里也可以换成网络摄像头地址
        grabber.start();   //开始获取摄像头数据
        CanvasFrame canvas = new CanvasFrame("倒计时5秒自动拍照注册");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//窗口关闭时程序运行结束
        canvas.setAlwaysOnTop(true);
        int i=0;
        while(true){
            if(i==30){//窗口是否关闭
                System.out.println("已关闭");
                grabber.stop();//停止抓取
                canvas.dispose();
                //System.exit(2);//退出
            }
            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame表示一帧视频图像
            //调用doExecuteFrame()方法，将截取的图片保存在本地
            if(i==1)CatchPhoto(grabber.grabFrame(),"./register"+"/"+ MainWindow.ID +".jpg");
            Thread.sleep(50);//50毫秒刷新一次图像
            i++;
        }
    }
    public static void TempPhoto()throws Exception, InterruptedException{
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//0表示本机摄像头  当然这里也可以换成网络摄像头地址
        grabber.start();   //开始获取摄像头数据
        CanvasFrame canvas = new CanvasFrame("倒计时5秒自动拍照识别");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//窗口关闭时程序运行结束
        canvas.setAlwaysOnTop(true);
        int i=0;
        while(true){
            if(i==30){//窗口是否关闭
                System.out.println("已关闭");
                grabber.stop();//停止抓取
                canvas.dispose();
                //System.exit(2);//退出
            }
            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame表示一帧视频图像
            //调用doExecuteFrame()方法，将截取的图片保存在本地
            if(i==1)CatchPhoto(grabber.grabFrame(),"./temp"+"/"+ MainWindow.Name+".jpg");
            Thread.sleep(50);//50毫秒刷新一次图像
            i++;
        }
    }






    /**
     *
     * @param f 表示帧
     * @param targetFileName 存储路径
     */
    //
    public static void CatchPhoto(Frame f, String targetFileName) {
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targetFileName);
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

