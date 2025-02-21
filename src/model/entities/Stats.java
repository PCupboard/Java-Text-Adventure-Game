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

    //private HashMap<String, Integer> stats = new HashMap<String, Integer>();

    // Må differensiere mellom max*enter stat here* og current*enter stat here* (f.eks maxHealth og currentHealth)
    // Gjør i morgen!

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
    }

    public String printHealth() {
        StringBuilder healthBar = new StringBuilder();
        healthBar.append("│");
        int maxHealthForBar = maxHealth / 10;
        int currentHealthForBar = currentHealth / 10;

        //System.out.println("Current health bar: "+currentHealthForBar);
        //System.out.println("Max health bar: "+maxHealthForBar);
        for (int i = 1; i <= currentHealthForBar; i++) {
            healthBar.append("\u001B[42m" + " " + Settings.TEXT_RESET);
        }
        for (int i = 1; i <= (maxHealthForBar - currentHealthForBar); i++) {
            healthBar.append("\u001B[41m" + " " + Settings.TEXT_RESET);
        }
        healthBar.append("│");
        return healthBar.toString();

    }

    public int getMaxConstitution() {
        return maxConstitution;
    }
    public void addMaxConstitution(int MaxConstitution) {
        maxConstitution += MaxConstitution;
    }
    public void removeMaxConstitution( int MaxConstitution) {
        maxConstitution -= MaxConstitution;
    }

    public int getMaxAgility() {
        return maxAgility;
    }
    public void addMaxAgility(int MaxAgility) {
        maxAgility += MaxAgility;
    }
    public void removeMaxAgility(int MaxAgility) {
        maxAgility -= MaxAgility;
    }

    public int getMaxWisdom() {
        return maxWisdom;
    }
    public void addMaxWisdom(int MaxWisdom) {
        maxWisdom += MaxWisdom;
    }
    public void removeMaxWisdom(int MaxWisdom) {
        maxWisdom -= MaxWisdom;
    }
}
