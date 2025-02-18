package model.items;

public class Item {
    /*
    Thought of as a general class for all items.
    Items include stuff like potions, weapons, armor, 
     */
    private int id = 0;
    protected String name;
    protected String description;
    protected int value;
    protected Rarity rarity;

    // Constructors
    public Item(String name, String description, Rarity rarity, int value) {
        id++;
        this.name = name;
        this.description = description;
        this.value = value;
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

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
