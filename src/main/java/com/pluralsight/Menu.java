package com.pluralsight;

public class Menu {
    public static double currentBalance = 540.00;

    public static void menuMain() throws InterruptedException {
        textMenuMain();

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
                            textMenuMain();
                            return currentBalance;
                        }
                        default -> System.out.println("Please enter a valid option.");
                    }
                }
                case "X" -> {
                    textMenuMain();
                    return currentBalance;
                }
                default -> System.out.println("Please enter a valid option.");
            }
        }
    }

    private static void submenuMakePayment() throws InterruptedException {
        boolean isCorrect = true;
        String recipient = "";
        double paymentAmount;
        textMakePayment();

        while (true) {
            if (isCorrect) {
                recipient = Console.promptForString("\nRecipient Full Name: ").trim();
                if (recipient.equalsIgnoreCase("x")) {
                    textMenuMain();
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
                        textMenuMain();
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
                        textMenuMain();
                        return;
                    }
                    case "N" -> {
                    }
                    case "X" -> {
                        textMenuMain();
                        return;
                    }
                }
            }
        }
    }

    private static void menuLedger() {

    }

    private static void submenuReports() {
        System.out.println("R test");
    }

    private static void textMenuMain() {
        System.out.println("""
                -------------------------------------
                What would you like to do today?
                
                D) Add Deposit
                P) Make A Payment
                L) Access Ledger
                
                X) Exit the Application
                """);
    }

    private static void textAddDeposit() {
        System.out.println("""
                
                How much would you like to deposit?
                (Daily maximum is $1,000.00)""");
    }

    private static void textMakePayment() {
        System.out.printf("""
                -------------------------------------
                Current balance: $%.2f
                
                Please enter the information below to
                make a payment.
                
                To cancel, enter X.
                """, currentBalance);
    }

    private static void textLedger() {
        System.out.println("""
                -------------------------------------
                What entries would you like to view?
                
                A) All Entries
                D) Deposit Entries Only
                P) Payment Entries Only
                R) Reports
                
                X) Return to main menu""");

    }
}
