package utils;

import java.awt.*;

public class Set {
    public static int fontByWidth(int width,String string){
        for(int i=1;i<5000;i++){
            Font f = new Font("宋体", Font.BOLD, i);

            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);

            // 高度
            if(fm.stringWidth(string)>width) return i;

        }
        return 0;
    }

    public static int fontByHeight(int height){
        for(int i=1;i<5000;i++){
            Font f = new Font("宋体", Font.BOLD, i);

            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);

            // 高度
            if(fm.getHeight()>height) return i;

        }
        return 0;
    }
}
