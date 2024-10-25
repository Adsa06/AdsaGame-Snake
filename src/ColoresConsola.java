package src;

public class ColoresConsola {

    public static String ANSI_RESET() {
        return "\u001B[0m";
    }
    public static String ANSI_RED() {
        return "\u001B[31m";
    }

    public static String ANSI_GREEN() {
        return "\u001B[32m";
    }

    public static String ANSI_YELLOW() {
        return "\u001B[33m";
    }

    public static String ANSI_BLUE() {
        return "\u001B[34m";
    }

    public static String ANSI_PURPLE() {
        return "\u001B[35m";
    }

    public static String ANSI_CYAN() {
        return "\u001B[36m";
    }

    public static String ANSI_WHITE() {
        return "\u001B[37m";
    }

    public static String ANSI_BOLD() {
        return "\u001B[1m";
    }

    public static String ANSI_UNDERLINE() {
        return "\u001B[4m";
    }

    public static String ANSI_INVERT() {
        return "\u001B[7m";
    }
    
}