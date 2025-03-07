package model.entities;
import model.Combat;
import model.items.Weapon;
import util.Settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class GameCharacter {

    Random random = new Random();

    protected String name;
    protected String description;
    protected Weapon currentWeapon;

    // CHARACTER STATS VARIABLES
    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private boolean isCharacterDead = false;

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
    public GameCharacter(String name, String description) {
        //id++;
        this.name = name;
        this.description = description;

    }


    public void combatTurn(ArrayList<GameCharacter> gameCharacterEnemies, ArrayList<GameCharacter> gameCharacterPlayers) throws InterruptedException, IOException {
        Scanner player_waiting = new Scanner(System.in);

        if (this instanceof Player || this instanceof NonPlayableCharacter) {
            String allyName = Settings.BLUE+getName()+Settings.TEXT_RESET;
            Scanner user_scanner = new Scanner(System.in);
            while (true) {
                Combat.printCombatDetails();
                System.out.println("What will " + allyName + " do?");
                System.out.println("1. Attack");
                System.out.println("2. Run away");
                System.out.println("3. View enemy details");

                String userInput = user_scanner.next();

                Combat.printCombatDetails();

                // Kristoffer sin cheat kode ;) laget 07.03.2025 COPYRIGHTED CODE BY KRISTOFFER KIRKERØD
                if (userInput.equals("krissErKul")){
                    addCurrentHealth(maxHealth);
                }

                else if (userInput.equals("1")) {
                    boolean validInput = false;
                    System.out.println("Which enemy does "+allyName+" want to attack?");

                    do {
                        try {
                            String userWhichEnemy = user_scanner.next();
                            System.out.print("\033[A"+"\33[2K\r");
                            int userWhichEnemyInt = Integer.parseInt(userWhichEnemy);

                            userWhichEnemyInt -= 1;

                            if (gameCharacterEnemies.get(userWhichEnemyInt).isCharacterDead) {
                                System.out.print(gameCharacterEnemies.get(userWhichEnemyInt).getName()+
                                        " is already dead! Don't desecrate a dead corpse!");
                                Thread.sleep(500);
                                System.out.print("\33[2K\r");
                                user_scanner.nextLine();
                            }

                            else if (userWhichEnemyInt >= 0) {
                                validInput = true;
                                attackCharacter(gameCharacterEnemies.get(userWhichEnemyInt));
                                System.out.print("\33[2K\r");
                                System.out.println(allyName+" dealt "+getDamage()+
                                        " towards the "+gameCharacterEnemies.get(userWhichEnemyInt).getName());
                                Thread.sleep(500);
                            }

                        } catch (Exception InputMismatchException) {
                            System.out.print("Input unrecognized! Enter the number of any enemy!");
                            Thread.sleep(850);
                            System.out.print("\u001B[2K\r");
                            user_scanner.nextLine();
                        }
                    }
                    while (!validInput);

                    break;
                }

                else if (userInput.equals("2")) {
                    System.out.println(allyName+" chose to run away!");
                    break;
                }

                else if (userInput.equals("3")) {
                    Combat.setViewEnemyDetails(true);
                }

                else {
                    System.out.println("Unrecognized input, Try again!");
                    Thread.sleep(500);
                }
            }
        }

        else if (this instanceof Enemy) {
            int targetRandomInt = random.nextInt(gameCharacterPlayers.size());
            GameCharacter targetToAttack = gameCharacterPlayers.get(targetRandomInt);
            attackCharacter(targetToAttack);
            Combat.printCombatDetails();
            System.out.println(name+" attacks "+Settings.BLUE+targetToAttack.getName()+Settings.TEXT_RESET+" for "+Settings.YELLOW
                               +getDamage()+Settings.TEXT_RESET+" Damage!");
            Thread.sleep(750);

            /*
            if (player_waiting.nextLine().isEmpty()) {
                return;
            }

             */
        }
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
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
        isCharacterDead = isCharacterDead();
    }

    public void removeCurrentHealth(int CurrentHealth) {
        currentHealth -= CurrentHealth;
        isCharacterDead = isCharacterDead();
    }
    private boolean isCharacterDead() {
        return (getCurrentHealth() <= 0);
    }
    public boolean getIsCharacterDead() {
        return isCharacterDead;
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
    public String printHealth(int allyNameLength) {
        StringBuilder healthBar = new StringBuilder();
        int maxHealthForBar = maxHealth / 10;
        int currentHealthForBar = currentHealth / 10;

        for (int i=1; i <= allyNameLength - getName().length(); i++) {
            healthBar.append(" ");
            //System.out.print(" ");
        }
        healthBar.append("│");

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
