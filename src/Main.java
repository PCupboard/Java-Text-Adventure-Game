import model.items.Rarity;
import util.Settings;

public class Main {
    public static void main(String[] args) {
        System.out.println("ifirjfienfmfjdm");
        //System.out.print("\033[H\033[2J");
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("The screen was flushed!");
        // Leker med å cleare terminalen for tekst

        System.out.println(Settings.GREEN+Rarity.COMMON+Settings.TEXT_RESET);
    }
}