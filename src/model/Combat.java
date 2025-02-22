package model;

import model.entities.Enemy;
import model.entities.GameCharacter;
import model.entities.NonPlayableCharacter;
import model.entities.Player;
import org.jetbrains.annotations.NotNull;
import util.Settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public abstract class Combat {
    // Variables which need to be saved on exit from game (a.k.a need getters)
    private static int numBattles = 0;

    // Variables which need to be zeroed/cleared after each combat is done
    static boolean viewEnemyDetails = false;
    private static ArrayList<GameCharacter> gameCharacterPlayers = new ArrayList<>();
    private static ArrayList<GameCharacter> gameCharacterEnemies = new ArrayList<>();
    private static ArrayList<GameCharacter> deadGameCharacterPlayers = new ArrayList<>();
    private static ArrayList<GameCharacter> deadGameCharacterEnemies = new ArrayList<>();

    static Random random = new Random();

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

        // Battle logic here
        while (true) {

            // Calculate health of each side. See if all characters on one side are completely dead.
            // If all are dead on one side, end battle.
            if (isOneSideDead()) {
                break;
            }

            if (calculatingMaxAgility()) {
                for (GameCharacter ally : gameCharacterPlayers) {
                    playerTurn(ally);
                }
                for (GameCharacter enemy : gameCharacterEnemies) {
                    enemyTurn(enemy);
                }
            }

            else {
                for (GameCharacter enemy : gameCharacterEnemies) {
                    enemyTurn(enemy);
                }
                for (GameCharacter ally : gameCharacterPlayers) {
                    playerTurn(ally);
                }
            }
        }

        restartBattleVariables();

    }

    // 2v2 method
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

    private static boolean calculatingMaxAgility() {
        int maxAgilityAllies = 0;
        int maxAgilityEnemies = 0;

        for (GameCharacter character : gameCharacterPlayers) {
            maxAgilityAllies += character.getCurrentAgility();
        }
        for (GameCharacter character : gameCharacterEnemies) {
            maxAgilityEnemies += character.getCurrentAgility();
        }
        if (maxAgilityAllies >= maxAgilityEnemies) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOneSideDead() {
        for (GameCharacter ally : gameCharacterPlayers) {
            if (ally.getCharacterDeadStatus()) {
                deadGameCharacterPlayers.add(ally);
            }
        }
        gameCharacterPlayers.removeAll(deadGameCharacterPlayers);

        if (gameCharacterPlayers.isEmpty()) {
            return true;
        }

        for (GameCharacter enemy : gameCharacterEnemies) {
            if (enemy.getCharacterDeadStatus()) {
                deadGameCharacterEnemies.add(enemy);
            }
        }
        gameCharacterEnemies.removeAll((deadGameCharacterEnemies));

        return gameCharacterEnemies.isEmpty();
    }

    private static void playerTurn(GameCharacter ally) throws IOException, InterruptedException {
        Scanner user_scanner = new Scanner(System.in);
        while (true) {
            printCombatDetails();
            System.out.println("What does " + Settings.BLUE + ally.getName() + Settings.TEXT_RESET + " want to do?");
            System.out.println("1. Attack");
            System.out.println("2. Run away");
            System.out.println("3. View enemy details");

            String userInput = user_scanner.next();

            if (userInput.contains("1")) {
                boolean validInput = false;
                do {
                    printCombatDetails();
                    System.out.println("Which enemy do you want to attack?");
                    try {
                        int userWhichEnemy = user_scanner.nextInt();
                        userWhichEnemy -= 1;
                        if (userWhichEnemy >= 0 && userWhichEnemy <= gameCharacterEnemies.size()) {
                            validInput = true;
                            ally.attackCharacter(gameCharacterEnemies.get(userWhichEnemy));
                        }
                        else {
                            System.out.println("This isn't the number of any enemies!");
                            Thread.sleep(500);
                        }

                    } catch (Exception InputMismatchException) {
                        System.out.print("Input unrecognized! Enter the number of the enemy!");
                        Thread.sleep(500);
                        System.out.print("\\33[2K");
                        user_scanner.nextLine();

                    }

                }
                while (!validInput);

                break;
            }
            else if (userInput.contains("2")) {
                System.out.println("You chose to run away!");
                break;
            }
            else if (userInput.contains("3")) {
                viewEnemyDetails = true;
            }
            else {
                System.out.println("Unrecognized input, Try again!");
                Thread.sleep(500);
            }
        }
    }

    private static void enemyTurn(GameCharacter enemy) throws IOException, InterruptedException {
        printCombatDetails();
        int targetRandomInt = random.nextInt(gameCharacterPlayers.size());
        GameCharacter targetToAttack = gameCharacterPlayers.get(targetRandomInt);
        enemy.attackCharacter(targetToAttack);
    }

    private static void restartBattleVariables() {
        gameCharacterPlayers.clear();
        gameCharacterEnemies.clear();

        deadGameCharacterEnemies.clear();
        deadGameCharacterPlayers.clear();

    }

    public static int getNumBattles() {
        return numBattles;
    }
}
