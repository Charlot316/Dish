package database.Entity;

import utils.Load;

import java.awt.*;

public class Dish {
    public String name;
    public String path;
    public boolean isOutrageous=false;
    //public Image image;
    public double conformity=0;



    public Dish(String name) {
        this.name = name;
        this.path=this.name+".png";
        //image= Load.image("dishes/"+this.path);
    }

    public Dish(String name, String path) {
        this.name = name;
        this.path = this.name+"."+path;
        //image= Load.image(this.path);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                '}';
    }
}
