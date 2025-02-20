package model.items;

import util.Settings;

public abstract class Rarity {
    public static final String COMMON    = Settings.TEXT_BOLD+"\u001B[92m"      +"Common"   +Settings.TEXT_RESET; // Light Green
    public static final String UNCOMMON  = Settings.TEXT_BOLD+"\u001B[32;1;22m" +"Uncommon" +Settings.TEXT_RESET; // Dark Green
    public static final String RARE      = Settings.TEXT_BOLD+"\u001B[94m"      +"Rare"     +Settings.TEXT_RESET; // Light Blue
    public static final String VERY_RARE = Settings.TEXT_BOLD+"\u001B[38;5;27m" +"Very Rare"+Settings.TEXT_RESET; // Blue
    public static final String UNIQUE    = Settings.TEXT_BOLD+"\u001B[38;5;177m"+"Unique"   +Settings.TEXT_RESET; // Light Purple
    public static final String EPIC      = Settings.TEXT_BOLD+"\u001B[38;5;128m"+"Epic"     +Settings.TEXT_RESET; // Purple
    public static final String LEGENDARY = Settings.TEXT_BOLD+"\u001B[38;5;226m"+"Legendary"+Settings.TEXT_RESET; // Yellow/Gold

    // Should the rarity give extra damage/healing/armor to items?
    //

}
