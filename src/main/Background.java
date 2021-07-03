package main;

import utils.Load;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {

    public Image backgroundImg;

    public Background() {
        setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        setVisible(true);
        setLayout(null);
        backgroundImg = Load.image("setup/背景.png");
    }

    public void paint(Graphics g) {
        setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        setVisible(true);
        setLayout(null);
        g.drawImage(backgroundImg, 0,0,Main.WIDTH,Main.HEIGHT,0, 0, backgroundImg.getWidth(null), backgroundImg.getHeight(null),null);
    }

}
