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
            if(!dish.isOutrageous) {
                if (dish.name.equals(name)) {
                    dish.conformity = 1000;
                    tempDishes.add(dish);
                } else if (dish.name.contains(name)) {
                    dish.conformity = 1000 * name.length() / (double) dish.name.length();
                    if (dish.conformity <= 100) dish.conformity = 100.1;
                    tempDishes.add(dish);
                } else {
                    boolean contain=false;
                    for (char i : name.toCharArray()) {
                        if (dish.name.contains(String.valueOf(i))) {
                            dish.conformity += 10 / dish.name.length();
                            contain=true;
                        }
                    }
                    if (dish.conformity >= 100) dish.conformity = 99.9;
                    if(contain) tempDishes.add(dish);
                }

            }
        }
        tempDishes.sort(comparatorDish);
        return tempDishes;
    }

    public static ArrayList<Dish> SearchOutrageous(ArrayList<Dish> Dishes,String name){
        ArrayList<Dish> tempDishes=new ArrayList<>();
        for(Dish dish :Dishes){
            dish.conformity=0;
            if(dish.isOutrageous) {
                if (dish.name.equals(name)) {
                    dish.conformity = 1000;
                    tempDishes.add(dish);
                } else if (dish.name.contains(name)) {
                    dish.conformity = 1000 * name.length() / (double) dish.name.length();
                    if (dish.conformity <= 100) dish.conformity = 100.1;
                    tempDishes.add(dish);
                } else {
                    boolean contain=false;
                    for (char i : name.toCharArray()) {
                        if (dish.name.contains(String.valueOf(i))) {
                            dish.conformity += 10 / dish.name.length();
                            contain=true;
                        }
                    }
                    if (dish.conformity >= 100) dish.conformity = 99.9;
                    if(contain) tempDishes.add(dish);
                }

            }
        }
        tempDishes.sort(comparatorDish);
        return tempDishes;
    }
}
