package main;

import database.Entity.Dish;
import utils.Load;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static int count=0;
    static ArrayList<Dish> Dishes=new ArrayList<>();
    public static void main(String[] args) {
        BufferedReader bufferedReader= Load.File();
        try {
            String command = bufferedReader.readLine();
            String[] arguments = command.split("\\s+");
            count=Integer.parseInt(arguments[0]);
            for (int i = 0; i < count; i++) {
                command = bufferedReader.readLine();
                arguments = command.split("\\s+");
                Dish tempDish=new Dish(arguments[0],Integer.parseInt(arguments[1]));
                Dishes.add(tempDish);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
