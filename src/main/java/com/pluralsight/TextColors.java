package com.pluralsight;

public class TextColors {

    // ----- Colors -----
    public static final String RESET   = "\u001B[0m";

    public static final String RED     = "\u001B[31m";
    public static final String BRIGHT_RED     = "\u001B[91m";
    public static final String BRIGHT_GREEN   = "\u001B[92m";
    public static final String BRIGHT_YELLOW  = "\u001B[93m";
    public static final String BRIGHT_BLUE    = "\u001B[94m";
    public static final String BRIGHT_CYAN    = "\u001B[96m";
    public static final String BRIGHT_WHITE   = "\u001B[97m";
    public static final String ORANGE = "\u001B[38;5;208m";
    public static final String PINK   = "\u001B[38;5;213m";

    // ----- Dividers -----
    public static final String SHORT_DASH = "-------------------------------------";
    public static final String LONG_DASH = "----------------------------------------------------------------------------------------";

    // ----- Generic color printing -----
    public static void print(String color, String text) {
        System.out.print(color + text + RESET);
    }

    public static void println(String color, String text) {
        System.out.println(color + text + RESET);
    }

    public static void printf(String color, String format, Object... args) {
        System.out.print(color + String.format(format, args) + RESET);
    }

    // ----- Color scheme -----
    /** Dashed lines: bright blue. */
    public static void dash() {
        println(BRIGHT_BLUE, SHORT_DASH);
    }

    public static void longDash() {
        println(BRIGHT_BLUE, LONG_DASH);
    }

    /** Regular menu text: bright cyan. */
    public static void menu(String text) {
        println(BRIGHT_CYAN, text);
    }

    /** Exit / cancel / back buttons: red. */
    public static void exitOption(String text) {
        println(RED, text);
    }
    /** Transaction rows: white. */
    public static void transaction(String text) {
        println(BRIGHT_WHITE, text);
    }
}