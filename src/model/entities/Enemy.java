package model.entities;
import model.items.Weapon;

public class Enemy extends Character {

    public Enemy(String name, String description, Weapon currentWeapon) {
        super(name, description, currentWeapon);
    }

}
