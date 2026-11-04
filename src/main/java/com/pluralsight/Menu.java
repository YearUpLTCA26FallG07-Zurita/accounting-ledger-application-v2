package com.pluralsight;

public class Menu {
    public static void menuMain() {

        textMenuMain();

        while (true) {

            String option = Console.promptForString("Enter your command: ").toUpperCase().trim();

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
        double currentBalance = 540.00;

        System.out.printf("""
                -------------------------------------
               Your current balance is $%.2f.
               """, currentBalance);
        textAddDeposit();


        while (true) {

            double deposit = Console.promptForDouble("Enter your amount: ");

            if (deposit <= 0) {
                System.out.println("Please enter an amount greater than $0.");
            } else {
                currentBalance += deposit;
                return currentBalance;
            }


//            switch (option) {
//                case "A" -> {
//                }
//                case "X" -> {
//                    textMenuMain();
//                    return;
//                }
//                default -> System.out.println("Please enter a valid option.");
//            }
        }
    }

    private static void submenuMakePayment() {
        System.out.println("P test");
    }

    private static void submenuLedger() {
        System.out.println("L test");
    }
    private static void textMenuMain(){
        System.out.println("""
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
               (Daily maximum is $1,000.00)
               """);
    }
}
