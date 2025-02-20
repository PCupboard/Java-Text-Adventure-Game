package model.entities;

import model.items.Weapon;

public class Player extends GameCharacter {

    public Player(String name, String description, Weapon currentWeapon) {
        super(name, description,currentWeapon);
    }
}
