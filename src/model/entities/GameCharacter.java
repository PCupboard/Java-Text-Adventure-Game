package model.entities;
import model.items.Weapon;

public abstract class GameCharacter extends Stats {
    //private int id;
    protected String name;
    protected String description;
    protected Weapon currentWeapon;
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

    The storage would need to store weapons and different types of items (potions, keys, etc)
     */

    public GameCharacter(String name, String description, Weapon currentWeapon) {
        //id++;
        this.name = name;
        this.description = description;
        this.currentWeapon = currentWeapon;

    }

    public void combat(GameCharacter enemy) {
        enemy.removeCurrentHealth(currentWeapon.getDamage());
        removeCurrentHealth(enemy.currentWeapon.getDamage());
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }
    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
}
