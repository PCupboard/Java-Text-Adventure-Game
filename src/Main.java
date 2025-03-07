import jdk.jshell.execution.Util;
import model.Combat;
import model.entities.Enemy;
import model.entities.NonPlayableCharacter;
import model.entities.Player;
import model.items.Item;
import model.items.Rarity;
import model.items.Weapon;
import util.Settings;
import util.loreStrings;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("ifirjfienfmfjdm");
        Settings.clearScreen();
        System.out.println("The screen was flushed!");
        // Leker med å cleare terminalen for tekst

        System.out.println(Rarity.COMMON);
        System.out.println(Rarity.UNCOMMON);
        System.out.println(Rarity.RARE);
        System.out.println(Rarity.VERY_RARE);
        System.out.println(Rarity.UNIQUE);
        System.out.println(Rarity.EPIC);
        System.out.println(Rarity.LEGENDARY);

        Item potion = new Item("Healing Potion", "A healing potion crafted by the gods themselves", 1, Rarity.LEGENDARY);
        System.out.println(potion);

        Weapon fist = new Weapon("Nothing", "You have nothing currently equipped!", 0, "", 20, true);
        Weapon magical_wand = new Weapon("Magical Wand", "You equipped the magical wand", 0, Rarity.RARE, 25, true);


        Player player = new Player("Tadas", "The player character!", fist);
        NonPlayableCharacter npc1 = new NonPlayableCharacter("Kristoffer", "An adventurer who set out after losing his hometown", fist);

        Enemy enemy = new Enemy("Goblin", "A common enemy found all throughout the world", fist);
        Enemy enemy01 = new Enemy("Goblin", "A common enemy found all throughout the world", fist);
        System.out.println();
        Enemy enemy02 = new Enemy("Hob Goblin", "A rarer enemy with mystical powers that escape human hands", magical_wand);
        enemy02.addMaxHealth(200);
        enemy02.addCurrentHealth(200);
        System.out.println("player start health: "+player.getMaxHealth()+"\n"+"player weapon damage: "+player.getCurrentWeapon().getDamage()+"\n");
        System.out.println("enemy start health: "+enemy.getMaxHealth()+"\n"+"enemy weapon damage: "+enemy.getCurrentWeapon().getDamage());

        player.addMaxHealth(80);
        player.addCurrentHealth(80);

        npc1.addMaxHealth(40);
        npc1.addCurrentHealth(40);

        Settings.clearScreen();

        enemy.removeCurrentHealth(60);

        Combat.start(player, enemy, npc1, enemy02 ,enemy01);

        Scanner user_scanner = new Scanner(System.in);
        user_scanner.next();


        // Everything below here is supposed to be in the final game


        // Game loop
        while (true) {
            Settings.clearScreen();
            /*
            Game launches into main menu. Player starts game. Player chooses name.
            After player chooses name, gets launched into the starting area.
            Game will only have the starting area on launch. :D
             */
            System.out.println(loreStrings.INTRO);

            break;
        }
    }
}