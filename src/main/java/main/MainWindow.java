package main;


import lombok.SneakyThrows;
import method.FaceEngine;
import method.OpencvTools;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.security.PublicKey;
import javax.swing.JFrame;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameGrabber;
public class MainWindow {
    public  static JButton btn_rigister;
    public static JButton btn_view1;
    public static JButton btn_view2;
    public static JButton btn_discern;
    public static JButton showcamera;
    public static JTextField ID_Text=null;
    public static JTextField Name_Text=null;
    public static JTextField Sex;
    public static JTextField Age;
    public static JTextField Similarity;
    public static JTextField LiVing;
    public static JTextField Angle;
    public static JLabel lab_register;
    public static JLabel lab_discern;
    public static JTextArea result;
    public static String ID;
    public static String Name;
    public static void main(String[] args) {

        // 此处处于 主线程，提交任务到 事件调度线程 创建窗口
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        // 此处处于 事件调度线程
                        MainWindow();
                    }
                }
        );
    }

    public static void MainWindow(){
        JFrame jf = new JFrame("人脸识别1.0");
        jf.setSize(1600, 900);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // 创建内容面板，指定布局为 null，则使用绝对布局
        JPanel panel = new JPanel(null);
        Font f = new Font("隶书", Font.BOLD,30);//设置字体
        //文本框
        ID_Text=new JTextField("请输入注册名称");
        ID_Text.setBounds(100,500,200,50);
        ID_Text.setFont(f);

        Name_Text=new JTextField("请输入姓名");
        Name_Text.setBounds(500,500,200,50);
        Name_Text.setFont(f);

        Sex=new JTextField("性别");
        Sex.setBounds(1000,100,500,50);
        Sex.setFont(f);

        Age=new JTextField("年龄");
        Age.setBounds(1000,200,500,50);
        Age.setFont(f);

        Similarity=new JTextField("相似度");
        Similarity.setBounds(1000,300,500,50);
        Similarity.setFont(f);

        LiVing=new JTextField("活体检测");
        LiVing.setBounds(1000,400,500,50);
        LiVing.setFont(f);

        Angle=new JTextField("3D角度");
        Angle.setBounds(800,500,800,50);
        Angle.setFont(f);
        //文本区域
        //result=new JTextArea(5,10);
        // 设置自动换行
        //result.setLineWrap(true);
        //result.setBounds(1100,50,500,800);
        //标签
        lab_register=new JLabel("查看注册图片");
        lab_register.setBounds(0,0,500,500);
        lab_register.setFont(f);

        lab_discern=new JLabel("查看识别图片");
        lab_discern.setBounds(500,0,500,500);
        lab_discern.setFont(f);
        //按钮
        btn_rigister=new JButton("注册");
        btn_rigister.setFont(f);
        btn_rigister.setBounds(100,600,150,80);

        btn_view1=new JButton("查看注册图片");
        btn_view1.setFont(f);
        btn_view1.setBounds(50,750,300,80);


        btn_discern =new JButton("识别");
        btn_discern.setFont(f);
        btn_discern.setBounds(500,600,150,80);

        btn_view2=new JButton("查看待识别图片");
        btn_view2.setFont(f);
        btn_view2.setBounds(450,750,350,80);

        showcamera=new JButton("识别结果");
        showcamera.setBounds(1200,0,300,50);
        showcamera.setFont(f);
        //监听事件
        btn_view1.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                // 此处处于 事件调度线程（所有监听器的回调都在 事件调度线程 中回调

                //lab_register.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\test\\1.jpg"));
                lab_register.setIcon(new ImageIcon("./register"+"/"+ ID +".jpg"));

            }
        });
        btn_view2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lab_discern.setIcon(new ImageIcon("./temp"+"/"+ Name +".jpg"));
            }
        });
        btn_rigister.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                ID=ID_Text.getText();
                //System.out.println(ID);
                OpencvTools.OpenCamera();
                //lab_register.setIcon(new ImageIcon("./register"+"/"+ MainWindow.ID +".jpg"));
                //lab_register.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\test\\1.jpg"));
            }
        });
        btn_discern.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                Name=Name_Text.getText();
                OpencvTools.TempPhoto();
            }
        });
        showcamera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FaceEngine.compare("C:\\Users\\user\\Desktop\\test\\photo\\1.jpg","C:\\Users\\user\\Desktop\\test\\photo\\2.jpg");
                FaceEngine.compare("./register"+"/"+ ID +".jpg","./temp"+"/"+ Name +".jpg");
            }
        });
        panel.add(ID_Text);
        panel.add(Name_Text);
        panel.add(Age);
        panel.add(Sex);
        panel.add(Similarity);
        panel.add(LiVing);
        panel.add(Angle);
        panel.add(showcamera);
        panel.add(lab_register);
        panel.add(lab_discern);
        panel.add(btn_view1);
        panel.add(btn_view2);
        panel.add(btn_rigister);
        panel.add(btn_discern);


        // 显示窗口
        jf.setResizable(false);
        jf.setContentPane(panel);
        jf.setVisible(true);

        /*
         * 也可以在 jf.setVisible(true) 之后添加按钮
         *
         * PS_01: jf.setVisible(true) 之后，内容面板才有宽高;
         * PS_02: 使用其他布局时, jf.setVisible(true) 之后添加的组件, 也会被当做是绝对布局来布置该组件（即需要手动指定坐标和宽高）;
         * PS_03: 使用其他布局时, jf.setVisible(true) 之前添加的组件, 如果在 jf.setVisible(true) 之后手动设置该组件的坐标和宽高,
         *        会将该组件当做绝对布局来对待（即设置坐标和宽高会生效）。
         */
    }

}


