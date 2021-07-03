package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.GlyphVector;
import java.sql.SQLException;

// 画布类
public class Canvas extends JLayeredPane{
    public JFrame frame;
    public KeyListener keyListener;
    public Square []squares=new Square[3];
    public Background background=new Background();
    /**
     * 初始化画板类
     * @param frame 当前的JFrame的对象
     */
    public Canvas(JFrame frame) {
        this.frame = frame;
        this.add(background);
        for(int i=0;i<3;i++){
            squares[i]=new Square(i);
            this.add(squares[i]);
        }
        setVisible(true);
    }


    /**
     * 绘画带有描边的字符串
     * @param str 字符串
     * @param f 字体
     * @param g 图形
     * @param x 横坐标
     * @param y 纵坐标
     * @param width 描边宽度
     * @param color1 填充颜色
     * @param color2 描边颜色
     */
    public void paintString(String str, Font f, Graphics g, Integer x, Integer y, Integer width, Color color1, Color color2) {
        GlyphVector v = f.createGlyphVector(getFontMetrics(f).getFontRenderContext(), str);
        Shape shape = v.getOutline();
        Rectangle bounds = shape.getBounds();
        Graphics2D gg = (Graphics2D) g;
        gg.translate(
                x, y
        );
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setColor(color1);
        gg.fill(shape);
        gg.setColor(color2);
        gg.setStroke(new BasicStroke(width));
        gg.draw(shape);
    }

    /**
     * 绘画给定位置居中的字符串
     * @param g 图形
     * @param text 字符串
     * @param width1 区域宽度
     * @param width2 起始横坐标
     * @param font 字体
     * @param y 纵坐标
     */
    public void drawCenteredString(Graphics g, String text, int width1, int width2, Font font, int y) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (width1 - metrics.stringWidth(text)) / 2 + width2;
        g.setFont(font);
        g.drawString(text, x, y);
    }

    /**
     * 绘画居中且带有描边的字符串
     * @param g 图形
     * @param text 字符串
     * @param width1 区域宽度
     * @param width2 起始横坐标
     * @param width 描边宽度
     * @param font 字体
     * @param y 纵坐标
     * @param color1 填充颜色
     * @param color2 描边颜色
     */
    public void drawCenteredStringByOutline(Graphics g, String text, int width1, int width2, int width, Font font, int y, Color color1, Color color2) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (width1 - metrics.stringWidth(text)) / 2 + width2;
        g.setFont(font);
        paintString(text, font, g, x, y, width, color1, color2);
        g.translate(-x, -y);
    }
}

