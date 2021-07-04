package utils;

import database.Entity.Dish;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Load {
    public static Image image(String path) {
        BufferedImage img = null;
        File file = new File("res/"+path);
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedReader File() {
        File file = new File("Menu.txt");
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert isr != null;
        return new BufferedReader(isr);
    }

    public static BufferedWriter outPutFile() {

        FileOutputStream writerStream = null;
        try {
            writerStream = new FileOutputStream("Menu.txt", false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter oWriter;
        assert writerStream != null;
        oWriter = new BufferedWriter(new OutputStreamWriter(writerStream, StandardCharsets.UTF_8));
        return oWriter;
    }

    public static void Initialize(){
        BufferedReader bufferedReader= Load.File();
        try {
            String command = bufferedReader.readLine();
            String[] arguments = command.split("\\s+");
            Main.count=Integer.parseInt(arguments[0]);
            for (int i = 0; i < Main.count; i++) {
                command = bufferedReader.readLine();
                arguments = command.split("\\s+");
                Dish tempDish=new Dish(arguments[0]);
                if(arguments.length>1) tempDish.isOutrageous=true;
                Main.Dishes.add(tempDish);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
