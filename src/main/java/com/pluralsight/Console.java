package com.pluralsight;

import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    // ----- Prompts (all bright yellow) -----
    public static String promptForSwitchString(String prompt) {
        TextColors.print(TextColors.BRIGHT_YELLOW, prompt);
        return scanner.nextLine().toUpperCase().trim();
    }

    public static String promptForString(String prompt) {
        TextColors.print(TextColors.BRIGHT_YELLOW, prompt);
        return scanner.nextLine();
    }

    public static int promptForInt(String prompt) {

        int result;

        while (true) {
            try {
                TextColors.print(TextColors.BRIGHT_YELLOW, prompt);
                result = scanner.nextInt();
                scanner.nextLine();
                return result;
            } catch (Exception e) {
                TextColors.menu("Invalid input, please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public static double promptForDouble(String prompt) {

        double result;

        while (true) {
            try {
                TextColors.print(TextColors.BRIGHT_YELLOW, prompt);
                result = scanner.nextDouble();
                scanner.nextLine();
                return result;
            } catch (Exception e) {
                TextColors.menu("Invalid input, please enter a number.");
                scanner.nextLine();
            }
        }
    }
}