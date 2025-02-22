package model.entities;
import model.items.Weapon;
import util.Settings;

public abstract class GameCharacter {
    protected String name;
    protected String description;
    protected Weapon currentWeapon;

    // CHARACTER STATS VARIABLES
    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private boolean characterDeadStatus = false;

    private int maxConstitution = 10;
    private int currentConstitution = maxConstitution;

    private int maxAgility = 10;
    private int currentAgility = maxAgility;

    private int maxWisdom = 10;
    private int currentWisdom = maxWisdom;


    /*
    The Weapon class holds damage values for each weapon, weapon creation will probably be done from a JSON file,
    that gets loaded whenever you acquire a new weapon, game needs weapon rarities perhaps?
    Rarities Common (light green), Uncommon (dark green), rare (light blue), very rare (dark blue),
    unique (indigo), epic (purple), legendary (yellow/gold)? <-- This text should go in the Weapon class xD

    How would damage values be changed? A weapon will have a flat attack value,
    which then gets added, subtracted, multiplied, or divided depending on the stat change?
    Should weapons at a certain rarity be able to have special abilities?
     */
    //private ArrayList<Storage> storage;
    /*
    The player needs some form of inventory how would this be achieved?
    The other entities do need current weapon, so they can get stronger with the player.
    If i ever end up adding NonPlayableCharacters to the game, i guess those would need inventories as well?
    The player could recruit NPCs and have them fight in a party?
    That would require combat where multiple entities are fighting against each other.
    The easiest way to achieve that would be some form of turn based combat.
    Just for making sure i can more easily expand upon the game later, the Storage will stay in the Character class.

    The storage would need to store weapons and different types of items (potions, keys, etc)
     */

    public GameCharacter(String name, String description, Weapon currentWeapon) {
        //id++;
        this.name = name;
        this.description = description;
        this.currentWeapon = currentWeapon;

    }

    public void combat(GameCharacter enemy) {
        enemy.removeCurrentHealth(currentWeapon.getDamage());
        removeCurrentHealth(enemy.currentWeapon.getDamage());
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

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }
    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public int getDamage() {
        return currentWeapon.getDamage();
    }

    public void attackCharacter(GameCharacter target) {
        target.removeCurrentHealth(getDamage());
    }

    // ################################################################################################
    // CHARACTER STATS METHODS
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
    // Health is allowed to go into negative values.
    // Can be thought of as a "damage" value to a character's body.
    // A health of -1000 means that the body has been atomically disintegrated.
    // Should the character be removed from the game if such a thing happens? hmmm
    public void addCurrentHealth(int CurrentHealth) {
        currentHealth += CurrentHealth;
        characterDeadStatus = isCharacterDead();
    }

    public void removeCurrentHealth(int CurrentHealth) {
        currentHealth -= CurrentHealth;
        characterDeadStatus = isCharacterDead();
    }
    private boolean isCharacterDead() {
        if (getCurrentHealth() <= 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean getCharacterDeadStatus() {
        return characterDeadStatus;
    }

    public String printHealth() {
        StringBuilder healthBar = new StringBuilder();
        healthBar.append("│");
        int maxHealthForBar = maxHealth / 10;
        int currentHealthForBar = currentHealth / 10;

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
    public int getCurrentConstitution() {
        return currentConstitution;
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
    public int getCurrentAgility() {
        return currentAgility;
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
    public int getCurrentWisdom() {
        return currentWisdom;
    }
    public void addMaxWisdom(int MaxWisdom) {
        maxWisdom += MaxWisdom;
    }
    public void removeMaxWisdom(int MaxWisdom) {
        maxWisdom -= MaxWisdom;
    }
}
