package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final LocalDate date;
    private final LocalTime time;
    private final String description;
    private final String vendor;
    private final double amount;

    public DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("hh:mm a");

    // Constructor #1 - used for constructing entries from user input. Date and time are default so they
    //                  don't get asked for.

    public Transaction(String vendor, String description, double amount) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Constructor #2 - used for constructing entries to be appended to transactions.csv. Since FileWriter
    //                  will only append Strings, date and time use parsing methods.

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = LocalDate.parse(date, dateFmt);
        this.time = LocalTime.parse(time, timeFmt);
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }


    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;

    }
    @Override
    public String toString() {
        String color;
        String formattedAmount;

        if (amount < 0) {
            color = TextColors.BRIGHT_RED;       // payment
            formattedAmount = "-$" + String.format("%,.2f", Math.abs(amount));
        } else {
            color = TextColors.BRIGHT_GREEN;     // deposit
            formattedAmount = " $" + String.format("%,.2f", amount);
        }

        return String.format("%-12s | %-12s | %-20s | %-22s | %s",
                date.format(dateFmt),
                time.format(timeFmt),
                vendor,
                "\"" + description + "\"",
                color + formattedAmount + TextColors.RESET);
    }
}