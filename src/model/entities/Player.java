package model.entities;

import model.items.Weapon;

import java.util.ArrayList;

public class Player extends Character {

    public Player(String name, Weapon currentWeapon) {
        super(name, currentWeapon);
    }
}
