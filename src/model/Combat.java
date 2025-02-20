package model;

import model.entities.GameCharacter;
import util.Settings;

public abstract class Combat {
    private static int numBattles = 0;

    // 1v1 constructor
    public static void playerCombat(GameCharacter player, GameCharacter enemy) throws InterruptedException {
        numBattles++;
        Settings.clearScreen();
        System.out.println();
        Thread.sleep(500);

    }

    // 1v2 constructor
    public static void playerCombat(GameCharacter player, GameCharacter firstEnemy, GameCharacter secondEnemy) {
        numBattles++;


    }

    // 2v2 constructor
    public static void playerCombat(GameCharacter firstPlayer, GameCharacter secondPlayer, GameCharacter firstEnemy, GameCharacter secondEnemy) {
        numBattles++;
    }

    private void combatText() {
        System.out.println();
    }

    public static int getNumBattles() {
        return numBattles;
    }
}
