package model.entities;

import model.items.Weapon;

import java.util.ArrayList;

public class Player extends Character {

    public Player(String name, String description, Weapon currentWeapon) {
        super(name, description,currentWeapon);
    }
}
