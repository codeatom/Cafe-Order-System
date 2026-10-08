package main.java.se.lexicon;

import java.util.Scanner;

public class CafeApp {

    private static final double VAT_RATE = 0.12;
    private static final double MEMBER_DISCOUNT_RATE = 0.15;
    private static final double LARGE_ORDER_DISCOUNT_RATE = 0.10;
    private static final double DISCOUNT_THRESHOLD = 150.00;


    private String getCustomerName(Scanner scanner) {
        System.out.print("Welcome! What is your name? ");
        return scanner.nextLine();
    }

    private void greetCustomer(String customerName) {
        System.out.println("Hi " + customerName + "! Here is our menu:");
        System.out.println();
    }

    private void displayMenu() {
        System.out.println("==============================");
        System.out.println("       Lexicon Cafe");
        System.out.println("==============================");
        System.out.println("1. Espresso         25.00 SEK");
        System.out.println("2. Cappuccino       35.00 SEK");
        System.out.println("3. Latte            40.00 SEK");
        System.out.println("4. Croissant        30.00 SEK");
        System.out.println("5. Sandwich         55.00 SEK");
        System.out.println("==============================");
        System.out.println();
    }
}