package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Menu {
    public static void menuMain() throws InterruptedException, IOException {
        MenuDialogue.textMenuMain();

        while (true) {

            String option = Console.promptForSwitchString("Enter your command: ");

            switch (option) {
                case "D" -> {
                    submenuAddDeposit();
                }
                case "P" -> {
                    submenuMakePayment();
                }
                case "L" -> {
                    menuLedger();
                }
                case "X" -> {
                    return;
                }
                default -> System.out.println("Please enter a valid option.");
            }
        }
    }

    private static void submenuAddDeposit() {
        double balance = Process.balance;
        System.out.printf("""
                -------------------------------------
                Your current balance is $%,.2f.
                
                D) Proceed with new deposit.
                X) Return to main menu%n%n""", balance);

        while (true) {
            String option = Console.promptForSwitchString("Enter your command: ");

            switch (option) {
                case "D" -> {
                    Process.addDeposit();
                    balance = Process.balance;


                    System.out.printf("""
                            -------------------------------------
                            Your current balance is now $%,.2f.
                            -------------------------------------
                            What would you like to do next?
                            
                            D) Make another deposit
                            X) Return to main menu""", balance);
                    System.out.println();
                    option = Console.promptForSwitchString("Enter your command here: ");

                    switch (option) {
                        case "D" -> {
                        }
                        case "X" -> {
                            MenuDialogue.textMenuMain();
                            return;
                        }
                        default -> System.out.println("Please enter a valid option.");
                    }
                }
                case "X" -> {
                    MenuDialogue.textMenuMain();
                    return;
                }
                default -> System.out.println("Please enter a valid option.");
            }
        }
    }

    private static void submenuMakePayment() throws InterruptedException {
        double balance = Process.balance;
        boolean isCorrect = true;
        String recipient = "";
        double paymentAmount;

        MenuDialogue.textMakePayment(balance);

        Process.makePayment();

//        while (true) {
//            if (isCorrect) {
//                recipient = Console.promptForString("\nRecipient Full Name: ").trim();
//                if (recipient.equalsIgnoreCase("x")) {
//                    MenuDialogue.textMenuMain();
//                    return;
//                }
//                paymentAmount = Console.promptForDouble("Amount of Payment: ");
//            } else {
//                paymentAmount = Console.promptForDouble("Amount of Payment: ");
//            }
//
//            if (paymentAmount > balance) {
//                System.out.println("""
//                        -------------------------------------
//                        ERROR.
//                        You do not have sufficient funds to
//                        process this payment.
//
//                        D) Reduce payment amount
//                        X) Cancel payment""");
//
//                String option = Console.promptForSwitchString("Enter your command: ");
//                switch (option) {
//                    case "D" -> {
//                        isCorrect = false;
//                    }
//                    case "X" -> {
//                        MenuDialogue.textMenuMain();
//                    }
//                    default -> System.out.println("Please enter a valid option.");
//                }
//            } else {
//                System.out.printf("""
//                        -------------------------------------
//                        Recipient Name: %s
//                        Payment Amount: $%,.2f
//
//                        Is this correct?
//
//                        Y) Confirm and continue
//                        N) Revise recipient and payment amount
//                        X) Cancel payment
//                        """, recipient, paymentAmount);
//
//                String option = Console.promptForSwitchString("\nEnter your command: ");
//
//                switch (option) {
//                    case "Y" -> {
//                        Process.makePayment();
//                        System.out.println("Thank you.\n");
//                        Thread.sleep(3000);
//                        MenuDialogue.textMenuMain();
//                        return;
//                    }
//                    case "N" -> {
//                    }
//                    case "X" -> {
//                        MenuDialogue.textMenuMain();
//                        return;
//                    }
//                }
//            }
//        }
    }

    private static void menuLedger() throws IOException {
        MenuDialogue.textLedger();

        while (true) {
            String option = Console.promptForSwitchString("Enter your command here: ");

            switch (option) {
                case "A" -> {
                    submenuAllEntries();
                }
                case "D" -> {
                    submenuDepositsOnly();
                }
                case "P" -> {
                    submenuPaymentsOnly();
                }
                case "R" -> {
                    menuReports();
                }
                case "X" -> {
                    MenuDialogue.textMenuMain();
                    return;
                }
            }
        }
    }

    private static void submenuAllEntries() throws IOException {
        Process.loadTransaction();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            System.out.println("------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("------------------------------------------------------------------------------");
            Thread.sleep(3000);
            MenuDialogue.textLedger();
        } catch (IOException e) {
            System.out.println("Error.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void submenuDepositsOnly() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            System.out.println("------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");
                String amountText = listing[4].trim();

                amountText = amountText.replace("$", "").replace(",", "");

                double amount = Double.parseDouble(amountText);

                if (amount > 0) {
                    System.out.println(line);
                }
            }
            System.out.println("------------------------------------------------------------------------------");
            Thread.sleep(3000);
            MenuDialogue.textLedger();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error reading file.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid transaction amount.");
        }
    }

    private static void submenuPaymentsOnly() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            System.out.println("------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");
                String amountText = listing[4].trim();

                amountText = amountText.replace("$", "").replace(",", "");

                double amount = Double.parseDouble(amountText);

                if (amount < 0) {
                    System.out.println(line);
                }
            }
            System.out.println("------------------------------------------------------------------------------");
            Thread.sleep(3000);
            MenuDialogue.textLedger();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid transaction amount.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void menuReports() {
        MenuDialogue.textReport();

        while (true) {

            int option = Console.promptForInt("Enter your command: ");

            switch (option) {
                case 1 -> LedgerReport.monthToDate();
                case 2 -> LedgerReport.previousMonth();
                case 3 -> LedgerReport.yearToDate();
                case 4 -> LedgerReport.previousYear();
                case 5 -> LedgerReport.searchByVendor();
                case 0 -> {
                    return;

                }
            }
        }
    }

    private static void submenuReports() {
        System.out.println("R test");

    }
}