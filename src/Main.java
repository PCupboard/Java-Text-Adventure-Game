import model.entities.Enemy;
import model.entities.Player;
import model.items.Item;
import model.items.Rarity;
import model.items.Weapon;
import util.Settings;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("ifirjfienfmfjdm");
        System.out.print(Settings.MOVE_CARET_TO_TOP);
        System.out.flush();
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

        Weapon fist = new Weapon("Empty", "You have nothing currently equipped!", 0, Rarity.COMMON, 5, true);

        Player player = new Player("Tadas", "The player character!", fist);

        Enemy enemy = new Enemy("Goblin", "A common enemy found all throughout the world", fist);
        System.out.println();

        player.addHealth(20);
        System.out.println("player start health: "+player.getHealth()+"\n"+"player weapon damage: "+player.getCurrentWeapon().getDamage()+"\n");
        System.out.println("enemy start health: "+enemy.getHealth()+"\n"+"enemy weapon damage: "+enemy.getCurrentWeapon().getDamage());

        System.out.println(Settings.MOVE_CARET_TO_TOP);
        System.out.flush();

        player.combat(enemy);
        player.combat(enemy);
        System.out.println("Player health: "+player.getHealth());
        System.out.println("Enemy health: "+enemy.getHealth());

        // Game loop
        while (true) {
            break;
        }
    }
}