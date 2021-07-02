package main;

import database.Entity.Dish;
import utils.Load;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static int count=0;
    public static ArrayList<Dish> Dishes=new ArrayList<>();
    public static void main(String[] args) {
        BufferedWriter writer=Load.outPutFile();
        Load.Initialize();
        try {
            writer.write(Dishes.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(Dishes);
    }
}
