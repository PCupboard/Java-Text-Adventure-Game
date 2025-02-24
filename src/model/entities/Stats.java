package model.entities;

import util.Settings;

public abstract class Stats {
    private int maxHealth = 100;
    private int currentHealth = maxHealth;

    private int maxConstitution = 10;
    private int currentConstitution = maxConstitution;

    private int maxAgility = 10;
    private int currentAgility = maxAgility;

    private int maxWisdom = 10;
    private int currentWisdom = maxWisdom;

    public int getMaxHealth() {
        return maxHealth;
    }
    public void addMaxHealth(int MaxHealth) {
        maxHealth += MaxHealth;
    }
    public void removeMaxHealth(int MaxHealth) {
        maxHealth -= MaxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    public void addCurrentHealth(int CurrentHealth) {
        currentHealth += CurrentHealth;
    }
    public void removeCurrentHealth(int CurrentHealth) {
        currentHealth -= CurrentHealth;
        if (isCharacterDead()) {
            System.out.println("Character has died!");
        }

    }
    private boolean isCharacterDead() {
        if (getCurrentHealth() <= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getMaxConstitution() {
        return maxConstitution;
    }
    public int getCurrentConstitution() { return currentConstitution; }
    public void addMaxConstitution(int MaxConstitution) {
        maxConstitution += MaxConstitution;
    }
    public void removeMaxConstitution( int MaxConstitution) {
        maxConstitution -= MaxConstitution;
    }

    public int getMaxAgility() {
        return maxAgility;
    }
    public int getCurrentAgility() { return currentAgility; }
    public void addMaxAgility(int MaxAgility) {
        maxAgility += MaxAgility;
    }
    public void removeMaxAgility(int MaxAgility) {
        maxAgility -= MaxAgility;
    }

    public int getMaxWisdom() {
        return maxWisdom;
    }
    public int getCurrentWisdom() { return currentWisdom; }
    public void addMaxWisdom(int MaxWisdom) {
        maxWisdom += MaxWisdom;
    }
    public void removeMaxWisdom(int MaxWisdom) {
        maxWisdom -= MaxWisdom;
    }
}
