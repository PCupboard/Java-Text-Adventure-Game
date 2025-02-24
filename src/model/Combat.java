package model;

import model.entities.Enemy;
import model.entities.GameCharacter;
import model.entities.NonPlayableCharacter;
import model.entities.Player;
import org.jetbrains.annotations.NotNull;
import util.Settings;

import java.io.IOException;
import java.util.*;

public abstract class Combat {
    // Variables which need to be saved on exit from game (a.k.a need getters)
    private static int numBattles = 0;

    // Variables which need to be zeroed/cleared after each combat is done
    static boolean viewEnemyDetails = false;
    static boolean combatOngoing = true;
    private static final ArrayList<GameCharacter> gameCharacterPlayers = new ArrayList<>();
    private static final ArrayList<GameCharacter> gameCharacterEnemies = new ArrayList<>();

    // Variables that do not need zeroing/clearing after each combat
    private static final ArrayList<GameCharacter> charactersTurnList = new ArrayList<>();

    // <------------------------------------------------------------------------------------------->

    // Combat method
    public static void start(GameCharacter @NotNull... characters) throws IOException, InterruptedException {
        numBattles++;

        for (GameCharacter character : characters) {
            if (character instanceof Player || character instanceof NonPlayableCharacter) {
                gameCharacterPlayers.add(character);
            }
            else if (character instanceof Enemy) {
                gameCharacterEnemies.add(character);
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

                printCombatDetails();

                if (!character.getIsCharacterDead()) {
                    character.characterCombatTurn(gameCharacterEnemies, gameCharacterPlayers);
                }
            }

            charactersTurnList.clear();

        }
        while (combatOngoing);
        restartBattleVariables();
        // BATTLE LOOP ENDS HERE!

    }

    private static void commenceCombatPrint() throws InterruptedException {

        Thread.sleep(500);
        System.out.print("A battle between ");
        for (GameCharacter player : gameCharacterPlayers) {
            System.out.print(Settings.BLUE+player.getName()+Settings.TEXT_RESET+", ");
        }
        System.out.print("\u001B[38;5;172m"+" VS "+Settings.TEXT_RESET);
        for (GameCharacter enemy : gameCharacterEnemies) {
            System.out.print(Settings.RED+enemy.getName()+Settings.TEXT_RESET+", ");
        }
        System.out.println(" is commencing!");

        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);

    }

    private static void printCombatDetails() throws IOException, InterruptedException {
        Settings.clearScreen();
        System.out.println(Settings.BLUE+"ALLIES"+Settings.TEXT_RESET);
        for (GameCharacter player : gameCharacterPlayers) {
            if (player.getIsCharacterDead()) {
                System.out.println(player.getName() + Settings.RED+" DEAD "+Settings.TEXT_RESET+
                        player.getCurrentHealth() + "/" + player.getMaxHealth() + " health\n");
            }
            System.out.println(player.getName() + " " + player.printHealth() + " " +
                    player.getCurrentHealth() + "/" + player.getMaxHealth() + " health\n");
        }

        // Need to redo the code for this once i add storage for enemies, need to show how many potions they have.
        int numEnemy = 1;
        System.out.println(Settings.RED+"ENEMIES"+Settings.TEXT_RESET);
        for (GameCharacter enemy : gameCharacterEnemies) {
            System.out.println("┌─┐ ");
            System.out.println("│"+numEnemy+"│ "+enemy.getName() + " " + enemy.printHealth() + " " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + " health");
            if (viewEnemyDetails) {
                System.out.print("└─┘ ");
                System.out.println("Equipped weapon: " + enemy.getCurrentWeapon().getNameWithRarity() + " | " + enemy.getCurrentWeapon().getDamage() + " damage");
            } else {
                System.out.println("└─┘ ");
            }
            numEnemy++;
        }
        viewEnemyDetails = false;

        for (int i = 0; i < 40; i++) {
            System.out.print("─");
        }
        System.out.println();

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

    }

    public static int getNumBattles() {
        return numBattles;
    }
}
