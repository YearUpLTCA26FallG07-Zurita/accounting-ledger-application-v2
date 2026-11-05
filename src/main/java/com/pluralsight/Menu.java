package com.pluralsight;

public class Menu {
    public static double currentBalance = 540.00;

    public static void menuMain() throws InterruptedException {
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

    private static double submenuAddDeposit() {

        System.out.printf("""
                -------------------------------------
                Your current balance is $%.2f.
                
                D) Proceed with new deposit.
                X) Return to main menu%n%n""", currentBalance);

        while (true) {
            String option = Console.promptForSwitchString("Enter your command: ");

            switch (option) {
                case "D" -> {
                    MenuDialogue.textAddDeposit();

                    processAddDeposit();

                    double deposit = Console.promptForDouble("\nEnter your amount: ");

                    if (deposit <= 0) {
                        System.out.println("Please enter an amount greater than $0.");
                    } else {
                        currentBalance += deposit;
                    }
                    System.out.printf("""
                            -------------------------------------
                            Your current balance is now $%.2f.
                            -------------------------------------
                            What would you like to do next?
                            
                            D) Make another deposit
                            X) Return to main menu""", currentBalance);
                    System.out.println();
                    option = Console.promptForSwitchString("Enter your command here: ");

                    switch (option) {
                        case "D" -> {
                        }
                        case "X" -> {
                            MenuDialogue.textMenuMain();
                            return currentBalance;
                        }
                        default -> System.out.println("Please enter a valid option.");
                    }
                }
                case "X" -> {
                    MenuDialogue.textMenuMain();
                    return currentBalance;
                }
                default -> System.out.println("Please enter a valid option.");
            }
        }
    }

    private static void processAddDeposit() {
        double deposit = Console.promptForDouble("\nEnter your amount: ");

            if (deposit > 0) {
                currentBalance += deposit;
            } else {
                System.out.println("Please enter an amount greater than $0.");
            }
        }

    private static void submenuMakePayment() throws InterruptedException {
        boolean isCorrect = true;
        String recipient = "";
        double paymentAmount;

        MenuDialogue.textMakePayment(currentBalance);

        while (true) {
            if (isCorrect) {
                recipient = Console.promptForString("\nRecipient Full Name: ").trim();
                if (recipient.equalsIgnoreCase("x")) {
                    MenuDialogue.textMenuMain();
                    return;
                }
                paymentAmount = Console.promptForDouble("Amount of Payment: ");
            } else {
                paymentAmount = Console.promptForDouble("Amount of Payment: ");
            }

            if (paymentAmount > currentBalance) {
                System.out.println("""
                        -------------------------------------
                        ERROR.
                        You do not have sufficient funds to
                        process this payment.
                        
                        D) Reduce payment amount
                        X) Cancel payment""");

                String option = Console.promptForSwitchString("Enter your command: ");
                switch (option) {
                    case "D" -> {
                        isCorrect = false;
                    }
                    case "X" -> {
                        MenuDialogue.textMenuMain();
                    }
                    default -> System.out.println("Please enter a valid option.");
                }
            } else {
                System.out.printf("""
                        -------------------------------------
                        Recipient Name: %s
                        Payment Amount: $%.2f
                        
                        Is this correct?
                        
                        Y) Confirm and continue
                        N) Revise recipient and payment amount
                        X) Cancel payment
                        """, recipient, paymentAmount);

                String option = Console.promptForSwitchString("\nEnter your command: ");

                switch (option) {
                    case "Y" -> {
                        System.out.println("Thank you.\n");
                        Thread.sleep(3000);
                        MenuDialogue.textMenuMain();
                        return;
                    }
                    case "N" -> {
                    }
                    case "X" -> {
                        MenuDialogue.textMenuMain();
                        return;
                    }
                }
            }
        }
    }
    private static void menuLedger() {
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

    private static void submenuAllEntries() {
        System.out.println("All entries");
    }

    private static void submenuDepositsOnly() {
        System.out.println("Deposits");
    }

    private static void submenuPaymentsOnly() {
        System.out.println("Payments");
    }

    private static void menuReports() {
    }

    private static void submenuReports() {
        System.out.println("R test");
    }

}
