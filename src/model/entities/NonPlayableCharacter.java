package model.entities;

import model.items.Weapon;

public class NonPlayableCharacter extends GameCharacter {

    public NonPlayableCharacter(String name, String description, Weapon currentWeapon) {
        super(name, description, currentWeapon);
    }

}
