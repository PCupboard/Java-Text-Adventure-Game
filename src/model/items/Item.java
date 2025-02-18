package model.items;

public class Item {
    /*
    Thought of as a general class for all items.
    Items include stuff like potions, weapons, armor, 
     */
    private int id;
    private String name;
    private String description;
    private double attack;
    private double defence;

    public void Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Constructors
    public void Item(int id, String name, String description, double attack, double defence) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attack = attack;
        this.defence = defence;
    }

}
