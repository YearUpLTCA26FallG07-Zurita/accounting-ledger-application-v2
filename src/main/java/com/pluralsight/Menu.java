package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu {
    public static void menuMain() {
        TextColors.dash();
        TextColors.menu("Main Menu\n");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make A Payment");
        System.out.println("L) Access Ledger\n");
        TextColors.exitOption("X) Exit the Application\n");


        while (true) {

            String option = Console.promptForSwitchString("Enter your command: ");

            switch (option) {
                case "D" -> submenuAddDeposit();
                case "P" -> submenuMakePayment();
                case "L" -> {
                    TextColors.dash();
                    menuLedger();
                }
                case "X" -> { return; }
                default -> TextColors.menu("Please enter a valid option.");
            }
        }
    }

    private static void submenuAddDeposit() {
        double balance = ProcessGeneral.balance;

        TextColors.dash();
        TextColors.printf(TextColors.ORANGE, "Your current balance is $%,.2f.%n%n", balance);
        System.out.println("D) Proceed with new deposit.");
        TextColors.exitOption("X) Return to main menu\n");

        while (true) {
            String option = Console.promptForSwitchString("Enter your command: ");

            switch (option) {
                case "D" -> {
                    ProcessGeneral.addDeposit();
                    balance = ProcessGeneral.balance;

                    TextColors.printf(TextColors.ORANGE, "Your current balance is now $%,.2f.%n", balance);
                    TextColors.dash();
                    TextColors.menu("What would you like to do next?\n");
                    System.out.println("D) Make another deposit");
                    TextColors.exitOption("X) Return to main menu\n");

                    option = Console.promptForSwitchString("Enter your command here: ");

                    switch (option) {
                        case "D" -> {
                        }
                        case "X" -> {
                            TextColors.dash();
                            TextColors.menu("Main Menu\n");
                            System.out.println("D) Add Deposit");
                            System.out.println("P) Make A Payment");
                            System.out.println("L) Access Ledger\n");
                            TextColors.exitOption("X) Exit the Application\n");
                            return;
                        }
                        default -> TextColors.menu("Please enter a valid option.");
                    }
                }
                case "X" -> {
                    TextColors.dash();
                    TextColors.menu("Main Menu\n");
                    System.out.println("D) Add Deposit");
                    System.out.println("P) Make A Payment");
                    System.out.println("L) Access Ledger\n");
                    TextColors.exitOption("X) Exit the Application\n");
                    return;
                }
                default -> TextColors.menu("Please enter a valid option.");
            }
        }
    }

    private static void submenuMakePayment() {
        boolean isCorrect = true;   // true = ask for recipient, amount, and description
        String recipient = "";
        String description = "";
        double paymentAmount;

        TextColors.dash();
        TextColors.printf(TextColors.BRIGHT_CYAN, "Current balance: $%,.2f%n%n", ProcessGeneral.balance);
        TextColors.menu("""
                Please enter the information below to
                make a payment.
                """);
        TextColors.exitOption("To cancel, enter X.\n");
        try {
            while (true) {
                double balance = ProcessGeneral.balance;

                TextColors.dash();
                if (isCorrect) {
                    recipient = Console.promptForString("Enter the name of the recipient: ").trim();
                    if (recipient.equalsIgnoreCase("x")) {
                        TextColors.dash();
                        TextColors.menu("Main Menu\n");
                        System.out.println("D) Add Deposit");
                        System.out.println("P) Make A Payment");
                        System.out.println("L) Access Ledger\n");
                        TextColors.exitOption("X) Exit the Application\n");
                        return;
                    }
                    paymentAmount = Math.abs(Console.promptForDouble("Enter payment amount: "));
                    description = Console.promptForString("Enter short description of the payment: ").trim();
                } else {
                    paymentAmount = Math.abs(Console.promptForDouble("Enter payment amount: "));
                }
                TextColors.dash();

                if (paymentAmount > balance) {
                    System.out.println(TextColors.RED + """
                            ERROR.
                            You do not have sufficient funds to
                            process this payment.
                            """);
                    System.out.println("D) Reduce payment amount");
                    TextColors.exitOption("X) Cancel payment");

                    String option = Console.promptForSwitchString("Enter your command: ");
                    switch (option) {
                        case "D" -> isCorrect = false;
                        case "X" -> {
                            TextColors.dash();
                            TextColors.menu("Main Menu\n");
                            System.out.println("D) Add Deposit");
                            System.out.println("P) Make A Payment");
                            System.out.println("L) Access Ledger\n");
                            TextColors.exitOption("X) Exit the Application\n");
                            return;
                        }
                        default -> TextColors.menu("Please enter a valid option.");
                    }
                } else {
                    TextColors.printf(TextColors.ORANGE, """
                            Recipient Name: %s
                            Description:    %s
                            Payment Amount: $%,.2f

                            """, recipient, description, paymentAmount);
                    TextColors.menu("Is this correct?\n");
                    System.out.println("Y) Confirm and continue");
                    System.out.println("N) Revise payment details");
                    TextColors.exitOption("X) Cancel payment");

                    String option = Console.promptForSwitchString("\nEnter your command: ");

                    switch (option) {
                        case "Y" -> {
                            ProcessGeneral.makePayment(recipient, description, paymentAmount);
                            TextColors.menu(TextColors.PINK + "Thank you.\n");
                            Thread.sleep(3000);
                            TextColors.dash();
                            TextColors.menu("Main Menu\n");
                            System.out.println("D) Add Deposit");
                            System.out.println("P) Make A Payment");
                            System.out.println("L) Access Ledger\n");
                            TextColors.exitOption("X) Exit the Application\n");
                            return;
                        }
                        case "N" -> isCorrect = true;   // ask for everything again
                        case "X" -> {
                            TextColors.dash();
                            TextColors.menu("Main Menu\n");
                            System.out.println("D) Add Deposit");
                            System.out.println("P) Make A Payment");
                            System.out.println("L) Access Ledger\n");
                            TextColors.exitOption("X) Exit the Application\n");
                            return;
                        }
                        default -> TextColors.menu("Please enter a valid option.");
                    }
                }
            }
        } catch (InterruptedException e) {
            TextColors.menu("Error");
        }
    }

    private static void menuLedger() {
        TextColors.menu("What entries would you like to view?\n");
        System.out.println("A) All Entries");
        System.out.println("D) Deposit Entries Only");
        System.out.println("P) Payment Entries Only");
        System.out.println("R) Reports\n");
        TextColors.exitOption("X) Return to main menu\n");
        while (true) {
            String option = Console.promptForSwitchString("Enter your command here: ");

            switch (option) {
                case "A" -> submenuAllEntries();
                case "D" -> submenuDepositsOnly();
                case "P" -> submenuPaymentsOnly();
                case "R" -> menuReports();
                case "X" -> {
                    TextColors.dash();
                    TextColors.menu("Main Menu\n");
                    System.out.println("D) Add Deposit");
                    System.out.println("P) Make A Payment");
                    System.out.println("L) Access Ledger\n");
                    TextColors.exitOption("X) Exit the Application\n");
                    return;
                }
                default -> TextColors.menu("Please enter a valid option.");
            }
        }
    }

    private static void submenuAllEntries() {
        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            TextColors.longDash();
            while ((line = reader.readLine()) != null) {
                TextColors.transaction(line);
            }
            TextColors.longDash();
            Thread.sleep(3000);
            TextColors.menu("What entries would you like to view?\n");
            System.out.println("A) All Entries");
            System.out.println("D) Deposit Entries Only");
            System.out.println("P) Payment Entries Only");
            System.out.println("R) Reports\n");
            TextColors.exitOption("X) Return to main menu\n");
        } catch (IOException e) {
            TextColors.menu("Error.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void submenuDepositsOnly() {
        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            TextColors.longDash();
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");
                String amountText = listing[4].replaceAll("\u001B\\[[0-9;]*m", "").trim();

                amountText = amountText.replace("$", "").replace(",", "");

                double amount = Double.parseDouble(amountText);

                if (amount > 0) {
                    TextColors.transaction(line);
                }
            }
            TextColors.longDash();
            Thread.sleep(3000);
            TextColors.menu("What entries would you like to view?\n");
            System.out.println("A) All Entries");
            System.out.println("D) Deposit Entries Only");
            System.out.println("P) Payment Entries Only");
            System.out.println("R) Reports\n");
            TextColors.exitOption("X) Return to main menu\n");
        } catch (IOException | InterruptedException e) {
            TextColors.menu("Error reading file.");
        } catch (NumberFormatException e) {
            TextColors.menu("Invalid transaction amount.");
        }
    }

    private static void submenuPaymentsOnly() {
        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            TextColors.longDash();
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");
                String amountText = listing[4].replaceAll("\u001B\\[[0-9;]*m", "").trim();

                amountText = amountText.replace("$", "").replace(",", "");

                double amount = Double.parseDouble(amountText);

                if (amount < 0) {
                    TextColors.transaction(line);
                }
            }
            TextColors.longDash();
            Thread.sleep(3000);
            TextColors.menu("What entries would you like to view?\n");
            System.out.println("A) All Entries");
            System.out.println("D) Deposit Entries Only");
            System.out.println("P) Payment Entries Only");
            System.out.println("R) Reports\n");
            TextColors.exitOption("X) Return to main menu\n");
        } catch (IOException e) {
            TextColors.menu("Error reading file.");
        } catch (NumberFormatException e) {
            TextColors.menu("Invalid transaction amount.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void menuReports() {
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

        while (true) {

            int option = Console.promptForInt("Enter your command: ");

            switch (option) {
                case 1 -> ProcessLedger.monthToDate();
                case 2 -> ProcessLedger.previousMonth();
                case 3 -> ProcessLedger.yearToDate();
                case 4 -> ProcessLedger.previousYear();
                case 5 -> ProcessLedger.searchByVendor();
                case 6 -> ProcessLedger.customSearch();
                case 0 -> {
                    TextColors.menu("What entries would you like to view?\n");
                    System.out.println("A) All Entries");
                    System.out.println("D) Deposit Entries Only");
                    System.out.println("P) Payment Entries Only");
                    System.out.println("R) Reports\n");
                    TextColors.exitOption("X) Return to main menu\n");
                    return;
                }
                default -> TextColors.menu("Please enter a valid number.");
            }
        }
    }
}