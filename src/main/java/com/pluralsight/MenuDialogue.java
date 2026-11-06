package com.pluralsight;

public class MenuDialogue {

    public static void textMenuMain() {
        TextColors.dash();
        TextColors.menu("Main Menu\n");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make A Payment");
        System.out.println("L) Access Ledger\n");
        TextColors.exitOption("X) Exit the Application\n");
    }

    public static void textMakePayment(double currentBalance) {
        TextColors.dash();
        TextColors.printf(TextColors.BRIGHT_CYAN, "Current balance: $%,.2f%n%n", currentBalance);
        TextColors.menu("""
                Please enter the information below to
                make a payment.
                """);
        TextColors.exitOption("To cancel, enter X.\n");
    }

    public static void textLedger() {
        TextColors.menu("What entries would you like to view?\n");
        System.out.println("A) All Entries");
        System.out.println("D) Deposit Entries Only");
        System.out.println("P) Payment Entries Only");
        System.out.println("R) Reports\n");
        TextColors.exitOption("X) Return to main menu\n");
    }

    public static void textReport() {
        TextColors.menu("""
                Select a filter for your report.""" + TextColors.RESET + """
                
                \n1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search By Vendor
                6) Custom Search
                """);
        TextColors.exitOption("0) Back to Ledger\n");
    }
}