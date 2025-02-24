package model;

import util.Settings;

import java.io.IOException;
import java.util.Scanner;

public abstract class Menus {

    Scanner userInput = new Scanner(System.in);

    public void mainMenu() throws IOException, InterruptedException {
        Settings.clearScreen();
        System.out.println("1. Start Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit Game");

        String userInputString = userInput.next();

        switch (userInputString) {
            case "1":
                System.out.println("Start le game");

            case "2":
                System.out.println("Load le game");

            case "3":
                System.out.println("Exit le game");
        }
    }
}
