package model.entities;
import model.items.Weapon;
import util.Settings;

import java.util.ArrayList;
import java.util.Random;


public class Enemy extends GameCharacter {

    public Enemy(String name, String description, Weapon currentWeapon) {
        super(name, description, currentWeapon);
    }

    public String printHealth(int maxEnemyNameLength) {
        StringBuilder healthBar = new StringBuilder();

        int maxHealthForBar = getMaxHealth() / 10;
        int currentHealthForBar = getCurrentHealth() / 10;

        for (int i=1; i <= maxEnemyNameLength - getName().length(); i++) {
            healthBar.append(" ");
            //System.out.print(" ");
        }

        healthBar.append("│");

        for (int i = 1; i <= currentHealthForBar; i++) {
            healthBar.append("\u001B[42m" + " " + Settings.TEXT_RESET); // green color
        }
        for (int i = 1; i <= (maxHealthForBar - currentHealthForBar); i++) {
            healthBar.append("\u001B[41m" + " " + Settings.TEXT_RESET); // red colour
        }
        healthBar.append("│");
        return healthBar.toString();
    }
}
