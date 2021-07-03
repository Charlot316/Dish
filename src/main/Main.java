package main;

import database.Entity.Dish;
import utils.Load;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static final int WIDTH = 800, HEIGHT = 700, FPS = 100;
    public static Canvas canvas;
    public static int count=0;
    public static ArrayList<Dish> Dishes=new ArrayList<>();

    public static void Initialize(){
        JFrame frame = new JFrame("Dishes！");
        frame.setVisible(true);
        canvas = new Canvas(frame);
        frame.setContentPane(canvas);
        frame.setVisible(true);
        frame.setSize(WIDTH, HEIGHT);
        // 窗口大小固定
        frame.setResizable(false);
        // 窗口居中显示
        frame.setLocationRelativeTo(frame.getOwner());
        // 窗口关闭时结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        Initialize();

        Load.Initialize();


        System.out.println(Dishes);
    }
}
