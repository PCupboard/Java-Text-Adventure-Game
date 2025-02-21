package model.items;

public class Weapon extends Item {
    private int damage;
    private final boolean twoHanded;

    public Weapon(String name, String description, int sellValue, String rarity, int damage, boolean twoHanded) {
        super(name, description, sellValue, rarity);
        this.damage = damage;
        this.twoHanded = twoHanded;
    }

    public int getDamage() {
        return damage;
    }

    public String getNameWithRarity() {
        if (rarity.isBlank()) {
            return name;
        }
        else {
            return rarity+" "+name;
        }
    }

    public boolean getTwoHanded() {
        return twoHanded;
    }
}
