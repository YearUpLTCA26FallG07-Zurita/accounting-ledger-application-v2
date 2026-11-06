package com.pluralsight;

public class MenuDialogue {

    public static void textMenuMain() {
        System.out.println("""
                -------------------------------------
                Main Menu

                D) Add Deposit
                P) Make A Payment
                L) Access Ledger

                X) Exit the Application
                """);
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
                
                X) Return to main menu
                """);

    }
    public static void textReport() {
        System.out.println("""
                Select a filter for your report.
                
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search By Vendor
                
                !NEW! 6) Custom Search
                
                0) Back to Ledger
                """);
    }
}
