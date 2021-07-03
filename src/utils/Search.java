package utils;

import database.Entity.Dish;

import java.util.ArrayList;
import java.util.Comparator;

public class Search {
    public static Comparator<Dish> comparatorDish= (o1, o2) -> (int) (o2.conformity-o1.conformity);

    public static ArrayList<Dish> SearchByName(ArrayList<Dish> Dishes,String name){
        ArrayList<Dish> tempDishes=new ArrayList<>();
        for(Dish dish :Dishes){
            dish.conformity=0;
            if(dish.name.equals(name)){
                dish.conformity=1000;
            }
            else if(dish.name.contains(name)){
                dish.conformity=100*name.length()/(double)dish.name.length();
                if(dish.conformity<=10) dish.conformity=10.1;
            }
            else {
                for(char i: name.toCharArray()){
                    if(dish.name.contains(String.valueOf(i))){
                        dish.conformity++;
                    }
                    if (dish.conformity>=10) dish.conformity=9.9;
                }
            }
            tempDishes.add(dish);
        }
        tempDishes.sort(comparatorDish);
        return tempDishes;
    }
}
