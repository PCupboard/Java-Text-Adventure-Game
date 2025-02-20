import model.entities.Enemy;
import model.entities.Player;
import model.items.Item;
import model.items.Rarity;
import model.items.Weapon;

public class Main {
    public static void main(String[] args) {
        System.out.println("ifirjfienfmfjdm");
        //System.out.print("\033[H\033[2J");
        System.out.print("\033[H\033[2J");
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

        Weapon fist = new Weapon("Nothing", "You have nothing currently equipped!", 0, Rarity.COMMON, 5, true);

        Player player = new Player("Tadas", fist);

        Enemy enemy = new Enemy("Goblin", fist);

        player.addHealth(50);
        System.out.println(player.getHealth());
        System.out.println(enemy.getHealth());
        player.addHealth(-30);
        System.out.println(player.getHealth());

        // Game loop
        while (true) {
            break;
        }
    }
}