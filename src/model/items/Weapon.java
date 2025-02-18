package model.items;

public class Weapon extends Item {
    private int damage;
    private final boolean two_handed;

    public Weapon(String name, String description, Rarity rarity, int value, int damage, boolean two_handed) {
        super(name, description, rarity, value);
        this.damage = damage;
        this.two_handed = two_handed;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isTwo_handed() {
        return two_handed;
    }
}
