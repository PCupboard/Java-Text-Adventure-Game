package model.entities;
import model.items.Weapon;

import java.util.ArrayList;
import java.util.Random;


public class Enemy extends GameCharacter {

    public Enemy(String name, String description, Weapon currentWeapon) {
        super(name, description, currentWeapon);
    }
}
