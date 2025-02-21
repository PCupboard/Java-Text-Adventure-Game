package model;

import model.entities.Enemy;
import model.entities.GameCharacter;
import model.entities.NonPlayableCharacter;
import model.entities.Player;
import org.jetbrains.annotations.NotNull;
import util.Settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Combat {
    private static int numBattles = 0;

    private static ArrayList<GameCharacter> gameCharacterPlayers = new ArrayList<>();
    private static ArrayList<GameCharacter> gameCharacterEnemies = new ArrayList<>();

    // 1v1 method
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
        Settings.clearScreen();

        // Battle logic here
        while (true) {
            System.out.println(Settings.BLUE+"ALLIES"+Settings.TEXT_RESET);

            for (GameCharacter player : gameCharacterPlayers) {
                System.out.println(player.getName() +  player.printHealth() + " " +
                        player.getCurrentHealth() + "/" + player.getMaxHealth() + " health");
                System.out.println("Equipped weapon: " + player.getCurrentWeapon().getNameWithRarity() +
                        " | " + player.getCurrentWeapon().getDamage() + " damage\n");
            }

            for (GameCharacter enemy : gameCharacterEnemies) {
                System.out.println(Settings.RED+"ENEMIES"+Settings.TEXT_RESET);
                System.out.println(enemy.getName()+ enemy.printHealth() + " " +
                                   enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + " health");
                System.out.println("Equipped weapon: " + enemy.getCurrentWeapon().getNameWithRarity() +
                        " | " + enemy.getCurrentWeapon().getDamage() + " damage\n");
            }

            for (GameCharacter character : gameCharacterPlayers) {
                System.out.println("What does "+ character.getName() +" want to do?");
                System.out.println("1. Attack");
                System.out.println("2. Run away");

                Scanner user_scanner = new Scanner(System.in);
                String userInput = user_scanner.next();

            }

            Scanner user_scanner = new Scanner(System.in);
            String userInput = user_scanner.next();

            switch (userInput) {
                case "1":
                    playerTurnAttack(gameCharacterEnemies.toArray(new GameCharacter[0]));
                    break;

                case "2":
                    System.out.println("You chose to run away!");

                default:
                    System.out.println("Unrecognized input :(");
            }
        }
    }


    // 1v2 method
    public static void playerCombat(GameCharacter @NotNull ... characters) {
        numBattles++;

        for (GameCharacter character : characters) {
            if (character instanceof Player) {
                gameCharacterPlayers.add(character);
            }
            else if (character instanceof Enemy) {
                gameCharacterEnemies.add(character);
            }
        }
    }

    // 2v2 method
    public static void playerCombat(GameCharacter firstPlayer, GameCharacter secondPlayer,
                                    GameCharacter firstEnemy,  GameCharacter secondEnemy) {
        numBattles++;
    }

    private static void playerTurnAttack(GameCharacter... character) {

    }


    private static void enemyTurn() {

    }

    public static int getNumBattles() {
        return numBattles;
    }
}
