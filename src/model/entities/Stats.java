package model.entities;


import java.util.HashMap;

public abstract class Stats {
    private int Health = 10;
    private int Constitution = 10;
    private int Agility = 10;
    private int Wisdom = 10;
    private HashMap<String, Integer> stats = new HashMap<String, Integer>();

    public Stats() {


    }

    public int getHealth() {
        return Health;
    }
    public void addHealth(int Health) {
        this.Health += Health;
    }

    public int getConstitution() {
        return Constitution;
    }
    public void addConstitution(int constitution) {
        Constitution += constitution;
    }

    public int getAgility() {
        return Agility;
    }
    public void addAgility(int agility) {
        Agility += agility;
    }

    public int getWisdom() {
        return Wisdom;
    }
    public void addWisdom(int wisdom) {
        Wisdom += wisdom;
    }
}
