package main;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel{
    public Integer x;
    public Integer y;
    public Integer width;
    public double interval=0.05;
    public Integer height;
    public double horizontalSize=0.4;
    public Integer count;
    public Image backgroundImg;
    public int id;
    /**
     * 构造函数
     */
    public Square(int i) {
        this.id=i;
        change();
        this.setBackground(Color.white);
        setOpaque(false);
        setBounds(x, y, width, height);
        setVisible(true);


    }

    public void change(){

        switch(this.id){
            case 0:
                x=(int)(Main.WIDTH*interval);
                y=(int)(Main.HEIGHT*interval);
                width=(int)(Main.WIDTH*(horizontalSize-interval/2));
                height=(int)(Main.HEIGHT*horizontalSize);
                break;
            case 2:
                x=(int)(Main.WIDTH*interval);
                y=(int)(Main.HEIGHT*(horizontalSize+interval));
                width=(int)(Main.WIDTH*(horizontalSize-interval/2));
                height=(int)(Main.HEIGHT*(1-(horizontalSize+interval)-interval));
                break;
            case 1:
                x=(int)(Main.WIDTH*(horizontalSize+interval/2));
                y=(int)(Main.HEIGHT*interval);
                width=(int)(Main.WIDTH*((1-horizontalSize)-interval/2));
                height=(int)(Main.HEIGHT*(1-2*interval));
                break;
        }
    }
    /**
     * 绘画左侧选择歌曲面板
     * @param g 图形
     */
    public void paint(Graphics g) {

    }

}
