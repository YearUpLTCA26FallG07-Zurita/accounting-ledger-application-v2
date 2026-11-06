package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

public class ProcessLedger {

    public static void monthToDate() {
        YearMonth month = YearMonth.now();

        LocalDate startDate = month.atDay(1);
        LocalDate endDate = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            TextColors.longDash();
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(listing[0].trim());

                if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                    TextColors.transaction(line);
                }
            }
            TextColors.longDash();
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProcessLedger.textReport();
    }



    public static void previousMonth() {
        YearMonth previousMonth = YearMonth.now().minusMonths(1);

        LocalDate startDate = previousMonth.atDay(1);
        LocalDate endDate = previousMonth.atEndOfMonth();

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            TextColors.longDash();
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(listing[0].trim());

                if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                    TextColors.transaction(line);
                }
            }
            TextColors.longDash();
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        textReport();
    }

    public static void yearToDate() {
        Year year = Year.now();

        LocalDate startDate = year.atDay(1);
        LocalDate endDate = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            TextColors.longDash();
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(listing[0].trim());

                if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                    TextColors.transaction(line);
                }
            }
            TextColors.longDash();
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        textReport();
    }

    public static void previousYear() {
        Year previousYear = Year.now().minusYears(1);

        LocalDate startDate = previousYear.atDay(1);
        LocalDate endDate = previousYear.atMonth(12).atEndOfMonth();

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            TextColors.longDash();
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(listing[0].trim());

                if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                    TextColors.transaction(line);
                }
            }
            TextColors.longDash();
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        textReport();
    }

    public static void searchByVendor() {
        String searchVendor = Console.promptForString("Enter the name of the vendor: ");

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            TextColors.longDash();
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                String vendor = listing[2].trim();

                if (searchVendor.equalsIgnoreCase(vendor)) {
                    TextColors.transaction(line);
                }
            }
            TextColors.longDash();
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        textReport();
    }

    public static void customSearch() {
        TextColors.dash();
        TextColors.menu("""
                Enter the following filter info.
                If a filter doesn't apply, press Enter.
                """);

        while (true) {
            String rangeStart = Console.promptForString("Start date (yyyy-MM-dd): ").trim();
            String rangeEnd = Console.promptForString("End date (yyyy-MM-dd): ").trim();
            String vendor = Console.promptForString("Vendor/Company: ").trim();
            String description = Console.promptForString("Transaction description: ").trim();
            String amount = Console.promptForString("Transaction price: ").trim().replace(",", "").replace("$", "");


            LocalDate startDate = null;
            LocalDate endDate = null;
            double price = 0;

            try {
                if (!rangeStart.isEmpty()) startDate = LocalDate.parse(rangeStart);
                if (!rangeEnd.isEmpty()) endDate = LocalDate.parse(rangeEnd);
                if (!amount.isEmpty()) price = Double.parseDouble(amount);
            } catch (DateTimeException | NumberFormatException e) {
                TextColors.menu("Invalid date or price. Please try again.\n");
                continue;
            }

            ArrayList<Transaction> finalListing = new ArrayList<>();

            for (Transaction t : ProcessGeneral.transactions) {
                if (startDate != null && t.getDate().isBefore(startDate)) {
                    continue;
                }
                if (endDate != null && t.getDate().isAfter(endDate)) {
                    continue;
                }
                if (!description.isEmpty() && !t.getDescription().equalsIgnoreCase(description)) {
                    continue;
                }
                if (!vendor.isEmpty() && !t.getVendor().equalsIgnoreCase(vendor)) {
                    continue;
                }
                if (price != 0 && Math.abs(t.getAmount()) != Math.abs(price)) {
                    continue;
                }
                finalListing.add(t);
            }

            TextColors.longDash();
            if (finalListing.isEmpty()) {
                TextColors.menu("No transactions found.");
            } else {
                for (Transaction t : finalListing) {
                    TextColors.transaction(t.toString());
                }
            }
            TextColors.longDash();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            textReport();
            return;
        }
    }

    private static void textReport() {
        TextColors.menu("What entries would you like to view?\n");
        System.out.println("A) All Entries");
        System.out.println("D) Deposit Entries Only");
        System.out.println("P) Payment Entries Only");
        System.out.println("R) Reports\n");
        TextColors.exitOption("X) Return to main menu\n");
    }
}