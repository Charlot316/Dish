package utils;

import database.Entity.Dish;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class Load {
    public static Image image(String path) {
        BufferedImage img = null;
        URL url = Load.class.getResource("/res/" + path);
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedReader File() {
        File file = new File("Menu.txt");
        FileReader reader=null;
        try {
            reader=new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(file.getAbsolutePath());
        return new BufferedReader(reader);
    }

    public static BufferedWriter outPutFile() {

        File file = new File("Menu2.txt");
        FileWriter outputStream=null;
        try {
            outputStream = new FileWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedWriter(outputStream);
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
