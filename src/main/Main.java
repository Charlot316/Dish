package main;

import database.Entity.Dish;
import utils.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static  int WIDTH = 800, HEIGHT = 700, FPS = 100;
    public static Canvas canvas;
    public static int count=0;
    public static ArrayList<Dish> Dishes=new ArrayList<>();
    public static JFrame frame = new JFrame("嘻餐Overcooked");

    public static void Initialize(){

        frame.setVisible(true);
        canvas = new Canvas(frame);
        frame.setContentPane(canvas);
        frame.setVisible(true);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        // 窗口大小固定
        // 窗口居中显示
        frame.setLocationRelativeTo(frame.getOwner());
        // 窗口关闭时结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        Initialize();

        Load.Initialize();

        frame.addComponentListener(new ComponentAdapter() {//拖动窗口监听

            public void componentResized(ComponentEvent e) {
                int width=frame.getWidth();//获取窗口宽度
                int height=frame.getHeight();//获取窗口高度  你也可以设置高度居中
                HEIGHT=height;
                WIDTH=width;
                frame.setSize(width,height);
                canvas.background.repaint();
            }

        });
    }
}
