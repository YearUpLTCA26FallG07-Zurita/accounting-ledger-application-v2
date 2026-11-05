package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm a");

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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return date.format(dateFmt) + "|" + time.format(timeFmt) + "|" + description + "|" + vendor + "|" + amount;
    }
}
