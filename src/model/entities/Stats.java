package model.entities;


import java.util.HashMap;

public abstract class Stats {
    private int Health = 10;
    private int Constitution = 10;
    private int Agility = 10;
    private int Wisdom = 10;
    private HashMap<String, Integer> stats = new HashMap<String, Integer>();

    // Må differensiere mellom max*enter stat here* og current*enter stat here* (f.eks maxHealth og currentHealth)
    // Gjør i morgen!

    public Stats() {


    }

    public int getHealth() {
        return Health;
    }
    public void printHealth() {
        StringBuilder healthBar = new StringBuilder();
        // The health bar will give
    }
    public void addHealth(int health) {
        Health += health;
    }
    public void removeHealth(int health) {
        Health -= health;
    }

    public int getConstitution() {
        return Constitution;
    }
    public void addConstitution(int constitution) {
        Constitution += constitution;
    }
    public void removeConstitution( int constitution) {
        Constitution -= constitution;
    }

    public int getAgility() {
        return Agility;
    }
    public void addAgility(int agility) {
        Agility += agility;
    }
    public void removeAgility(int agility) {
        Agility -= agility;
    }

    public int getWisdom() {
        return Wisdom;
    }
    public void addWisdom(int wisdom) {
        Wisdom += wisdom;
    }
    public void removeWisdom(int wisdom) {
        Wisdom -= wisdom;
    }
}
