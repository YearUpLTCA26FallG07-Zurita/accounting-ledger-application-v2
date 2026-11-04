package com.pluralsight;

public class Menu {
    public static double currentBalance = 540.00;
    public static void menuMain() {

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
                    submenuLedger();
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
               Your current balance is $%.2f.%n""", currentBalance);
        textAddDeposit();

        while (true) {

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
                    X) Return to main menu
                    %n""", currentBalance);

            String option = Console.promptForSwitchString("Enter your command here: ");

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
    }

    private static void submenuMakePayment() {
        boolean isCorrect = true;
        String recipient = "";
        double paymentAmount;
        textMakePayment();

        while (true) {
            if (isCorrect) {
                recipient = Console.promptForString("\nRecipient Full Name: ");
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
                    N) Update recipient name
                    
                    X) Cancel payment
                    """, recipient, paymentAmount);

                String option = Console.promptForSwitchString("\nEnter your command: ");


            }


        }

    }

    private static void submenuLedger() {
        System.out.println("L test");
    }
    private static void textMenuMain(){
        System.out.println("""
               -------------------------------------
               What would you like to do today?
               
               D) Add Deposit
               P) Make A Payment
               L) Access Ledger
        
               X) Exit the Application
               """);
    }
    private static void textAddDeposit(){
        System.out.println("""
               
               How much would you like to deposit?
               (Daily maximum is $1,000.00)""");
    }
    private static void textMakePayment(){
        System.out.printf("""
                -------------------------------------
                Current balance: $%.2f
                
                Please enter the information below to
                make a payment.
                """, currentBalance);
    }
}
