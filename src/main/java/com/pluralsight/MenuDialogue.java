package com.pluralsight;

public class MenuDialogue {

    public static void textMenuMain() {
        System.out.println("""
                -------------------------------------
                What would you like to do today?
                
                D) Add Deposit
                P) Make A Payment
                L) Access Ledger
                
                X) Exit the Application
                """);
    }

    public static void textAddDeposit() {
        System.out.println("""
                
                How much would you like to deposit?
                (Daily maximum is $1,000.00)""");
    }

    public static void textMakePayment(double currentBalance) {
        System.out.printf("""
                -------------------------------------
                Current balance: $%,.2f
                
                Please enter the information below to
                make a payment.
                
                To cancel, enter X.
                """, currentBalance);
    }

    public static void textLedger() {
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
