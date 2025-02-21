package model;

import model.entities.GameCharacter;
import util.Settings;

import java.io.IOException;

public abstract class Combat {
    private static int numBattles = 0;

    // 1v1 method
    public static void playerCombat(GameCharacter player, GameCharacter enemy) throws IOException, InterruptedException {
        numBattles++;
        Thread.sleep(500);
        System.out.println("A battle between "+Settings.BLUE+player.getName()+Settings.TEXT_RESET+
                " and "+Settings.RED+enemy.getName()+Settings.TEXT_RESET+" is commencing!");
        System.out.print("."); Thread.sleep(500); System.out.print("."); Thread.sleep(500);
        System.out.print("."); Thread.sleep(500); System.out.print("."); Thread.sleep(500);
        Settings.clearScreen();

        // Battle logic here
        while (true) {
            System.out.println(enemy.getName()+" | current health: "+enemy.getCurrentHealth());
        }

    }

    // 1v2 method
    public static void playerCombat(GameCharacter player, GameCharacter firstEnemy, GameCharacter secondEnemy) {
        numBattles++;
    }

    // 2v2 method
    public static void playerCombat(GameCharacter firstPlayer, GameCharacter secondPlayer,
                                    GameCharacter firstEnemy,  GameCharacter secondEnemy) {
        numBattles++;
    }

    public static int getNumBattles() {
        return numBattles;
    }
}
