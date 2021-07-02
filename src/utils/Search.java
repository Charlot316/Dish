package utils;

import database.Entity.Dish;

import java.util.ArrayList;

public class Search {
    public static ArrayList<Dish> SearchByExactName(ArrayList<Dish> Dishes,String name){
        ArrayList<Dish> tempDishes=new ArrayList<>();
        for(Dish dish :Dishes){
            if(dish.name.equals(name)){
                tempDishes.add(dish);
            }
        }
        return tempDishes;
    }

    public static ArrayList<Dish> SearchByName(ArrayList<Dish> Dishes,String name){
        ArrayList<Dish> tempDishes=new ArrayList<>();
        for(Dish dish :Dishes){
            if(dish.name.contains(name)){
                tempDishes.add(dish);
            }
        }
        return tempDishes;
    }
}
