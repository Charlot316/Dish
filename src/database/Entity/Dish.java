package database.Entity;

public class Dish {
    public String name;
    public String path;
    public int Outrageous;

    public Dish(String name, int outrageous) {
        this.name = name;
        this.path=this.name+".png";
        Outrageous = outrageous;
    }

    public Dish(String name, String path, int outrageous) {
        this.name = name;
        this.path = this.name+"."+path;
        Outrageous = outrageous;
    }
}
