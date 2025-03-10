package util;
import java.io.IOException;

public abstract class Settings {

    public static void clearScreen(String... arg) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static final String GAME_NAME = "Synthaxe";

    public static final String MOVE_UP_ONE_LINE = "\033[A";
    public static final String REMOVE_TEXT_CURRENT_LINE = "\33[2K\r";

    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_UNDERLINE = "\u001B[4m";
    public static final String TEXT_BOLD = "\u001B[1m";

    public static final String BLACK	= "\u001B[30m";
    public static final String RED		= "\u001B[31m";
    public static final String GREEN	= "\u001B[32m";
    public static final String YELLOW	= "\u001B[33m";
    public static final String BLUE	    = "\u001B[34m";
    public static final String MAGENTA	= "\u001B[35m";
    public static final String CYAN	    = "\u001B[36m";
}
