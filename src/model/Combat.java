package model;

import model.entities.Enemy;
import model.entities.GameCharacter;
import model.entities.NonPlayableCharacter;
import model.entities.Player;
import util.Settings;

import java.io.IOException;
import java.util.*;

public abstract class Combat {
    // Variables which need to be saved on exit from game (a.k.a need getters)
    private static int numBattles = 0;

    // Variables which need to be zeroed/cleared after each combat is done
    static boolean viewEnemyDetails = false;
    static boolean combatOngoing = true;
    static boolean isFirstTurn = true;
    static int longestNameAllies = 0;
    static int longestNameEnemies = 0;
    private static final ArrayList<GameCharacter> gameCharacterPlayers = new ArrayList<>();
    private static final ArrayList<GameCharacter> gameCharacterEnemies = new ArrayList<>();

    // Variables that do not need zeroing/clearing after each combat
    private static final ArrayList<GameCharacter> charactersTurnList = new ArrayList<>();

    // <------------------------------------------------------------------------------------------->

    // Combat method
    public static void start(GameCharacter... characters) throws IOException, InterruptedException {
        numBattles++;

        for (GameCharacter character : characters) {
            if (character instanceof Player || character instanceof NonPlayableCharacter) {
                gameCharacterPlayers.add(character);
                if (character.getName().length() > longestNameAllies) {
                    longestNameAllies = character.getName().length();
                }
            }
            else if (character instanceof Enemy) {
                gameCharacterEnemies.add(character);
                if (character.getName().length() > longestNameEnemies) {
                    longestNameEnemies = character.getName().length();
                }
            }
        }

        commenceCombatPrint();

        // ############################################################################
        // BATTLE LOOP STARTS HERE!
        do {
            calculateTurnOrder();

            for (GameCharacter character : charactersTurnList) {

                if (isOneSideDead()) {
                    // The combat has ended, need to print out some sort of reward screen :D
                    combatOngoing = false;
                    break;
                }

                if (!character.getIsCharacterDead()) {
                    character.combatTurn(gameCharacterEnemies, gameCharacterPlayers);
                }
            }

            charactersTurnList.clear();

        }
        while (combatOngoing);
        restartBattleVariables();
        // BATTLE LOOP ENDS HERE!
        printCombatDetails();

        // Rewards screen:

    }

    private static void commenceCombatPrint() throws InterruptedException {
        int lastIterationInt = 0;

        Thread.sleep(500);
        System.out.print("A battle between ");
        for (GameCharacter player : gameCharacterPlayers) {
            if (++lastIterationInt == gameCharacterPlayers.size()) {
                System.out.print(Settings.BLUE+player.getName()+Settings.TEXT_RESET+" ");
            }
            else {
                System.out.print(Settings.BLUE+player.getName()+Settings.TEXT_RESET+", ");
            }
        }

        lastIterationInt = 0;

        System.out.print("\u001B[38;5;172m"+"VS "+Settings.TEXT_RESET);
        for (GameCharacter enemy : gameCharacterEnemies) {
            if (++lastIterationInt == gameCharacterEnemies.size()) {
                System.out.print(Settings.RED+enemy.getName()+Settings.TEXT_RESET+" ");
            }
            else {
                System.out.print(Settings.RED+enemy.getName()+Settings.TEXT_RESET+", ");
            }
        }
        System.out.println("is commencing!");

        System.out.print(".");
        Thread.sleep(600);
        System.out.print(".");
        Thread.sleep(600);
        System.out.print(".");
        Thread.sleep(600);
        System.out.print(".");
        Thread.sleep(600);

    }

    public static void printCombatDetails() throws IOException, InterruptedException {
        Settings.clearScreen();
        System.out.println(Settings.BLUE+"ALLIES"+Settings.TEXT_RESET);
        for (GameCharacter player : gameCharacterPlayers) {
            if (player.getIsCharacterDead()) {
                System.out.println(player.getName() + Settings.RED+" DEAD "+Settings.TEXT_RESET+
                        player.getCurrentHealth() + "/" + player.getMaxHealth() + " health\n");
            }
            else {
                System.out.println(player.getName() + " " + player.printHealth(longestNameAllies) + " " +
                        player.getCurrentHealth() + "/" + player.getMaxHealth() + " health\n");
            }
        }

        // Need to redo the code for this once i add storage or special abilities for enemies.
        int numEnemy = 1;
        System.out.println(Settings.RED+"ENEMIES"+Settings.TEXT_RESET);
        for (GameCharacter enemy : gameCharacterEnemies) {

            if (enemy.getIsCharacterDead()) {
                System.out.println(enemy.getName() + Settings.RED+" DEAD "+Settings.TEXT_RESET+
                        enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + " health\n");
            }
            else {
                System.out.println("│"+numEnemy+"│ "+ enemy.getName()+ " " + enemy.printHealth(longestNameEnemies) +
                        " " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + " health\n");
            }
            numEnemy++;

        }
        viewEnemyDetails = false;

        for (int i = 0; i < 40; i++) {
            System.out.print("─");
        }
        System.out.println();

    }

    private static void afterCombatRewardScreen() throws IOException, InterruptedException {
        printCombatDetails();

    }

    private static void calculateTurnOrder() {
        int maxAgilityAllies = 0;
        int maxAgilityEnemies = 0;

        for (GameCharacter character : gameCharacterPlayers) {
            maxAgilityAllies += character.getCurrentAgility();
        }
        for (GameCharacter character : gameCharacterEnemies) {
            maxAgilityEnemies += character.getCurrentAgility();
        }
        if (maxAgilityAllies >= maxAgilityEnemies) {
            charactersTurnList.addAll(gameCharacterPlayers);
            charactersTurnList.addAll(gameCharacterEnemies);
        } else {
            charactersTurnList.addAll(gameCharacterEnemies);
            charactersTurnList.addAll(gameCharacterPlayers);
        }
    }

    private static boolean isOneSideDead() {
        boolean oneSideDead = true;

        for (GameCharacter ally : gameCharacterPlayers) {
            if (!ally.getIsCharacterDead()) {
                oneSideDead = false;
                break;
            }
        }

        if (oneSideDead) {
            return true;
        }

        oneSideDead = true;
        for (GameCharacter enemy : gameCharacterEnemies) {
            if (!enemy.getIsCharacterDead()) {
                oneSideDead = false;
                break;
            }
        }

        return oneSideDead;
    }

    private static void restartBattleVariables() {
        gameCharacterPlayers.clear();
        gameCharacterEnemies.clear();

        combatOngoing = true;
        isFirstTurn = true;
        longestNameAllies = 0;
        longestNameEnemies = 0;

    }

    public static void setViewEnemyDetails(boolean viewEnemyDetails) {
        Combat.viewEnemyDetails = viewEnemyDetails;
    }

    public static int getNumBattles() {
        return numBattles;
    }
}
