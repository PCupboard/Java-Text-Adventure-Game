package model.entities;
import model.Weapon;
import java.util.ArrayList;

public abstract class Character {
    private int id;
    private String name;
    private int health;
    private Weapon currentWeapon;
    /*
    The Weapon class holds damage values for each weapon, weapon creation will probably be done from a JSON file,
    that gets loaded whenever you acquire a new weapon, game needs weapon rarities perhaps?
    Rarities Common (light green), Uncommon (dark green), rare (light blue), very rare (dark blue),
    unique (indigo), epic (purple), legendary (yellow/gold)? <-- This text should go in the Weapon class xD

    How would damage values be changed? A weapon will have a flat attack value,
    which then gets added, subtracted, multiplied, or divided depending on the stat change?
    Should weapons at a certain rarity be able to have special abilities?
     */
    //private ArrayList<Storage> storage;
    /*
    The player needs some form of inventory how would this be achieved?
    The other entities do need current weapon, so they can get stronger with the player.
    If i ever end up adding NonPlayableCharacters to the game, i guess those would need inventories as well?
    The player could recruit NPCs and have them fight in a party?
    That would require combat where multiple entities are fighting against each other.
    The easiest way to achieve that would be some form of turn based combat.
    Just for making sure i can more easily expand upon the game later, the Storage will stay in the Character class.

    The storage would need to store weapons and different types of items (potions, keys etc)
     */






}
