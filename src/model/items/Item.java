package model.items;

import util.Settings;

public class Item {
    /*
    Thought of as a general class for all items.
    Items include stuff like potions, weapons, armor, 
     */
    private int id = 0;
    protected String name;
    protected String description;
    protected int sellValue;
    protected String rarity;

    // Constructors
    public Item(String name, String description, int sellValue, String rarity) {
        id++;
        this.name = name;
        this.description = description;
        this.sellValue = sellValue;
        this.rarity = rarity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getSellValue() {
        return sellValue;
    }

    public String getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return rarity+" "+name+Settings.TEXT_RESET+"\n"+description;
    }
}
