package main.java.se.lexicon;

import java.util.Scanner;

public class CafeApp {

    private static final double VAT_RATE = 0.12;
    private static final double MEMBER_DISCOUNT_RATE = 0.15;
    private static final double LARGE_ORDER_DISCOUNT_RATE = 0.10;
    private static final double DISCOUNT_THRESHOLD = 150.00;



    public void start() {
        Scanner scanner = new Scanner(System.in);

        String customerName = getCustomerName(scanner);

        greetCustomer(customerName);
        displayMenu();

        int itemNumber = getItemNumber(scanner);
        int quantity = getQuantity(scanner);

        String itemName = getItemName(itemNumber);
        double unitPrice = getItemPrice(itemNumber);

        double subtotal = calculateSubtotal(unitPrice, quantity);


        printReceipt(
                customerName,
                itemName,
                quantity,
                subtotal
        );

        scanner.close();
    }

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

    private int getItemNumber(Scanner scanner) {
        while (true) {
            System.out.print("Enter item number (1-5): ");

            if (scanner.hasNextInt()) {
                int itemNumber = scanner.nextInt();

                if (itemNumber >= 1 && itemNumber <= 5) {
                    return itemNumber;
                }
            } else {
                scanner.next();
            }

            System.out.println("Please enter a number between 1 and 5.");
        }
    }

    private int getQuantity(Scanner scanner) {
        while (true) {
            System.out.print("How many? ");

            if (scanner.hasNextInt()) {
                int quantity = scanner.nextInt();

                if (quantity > 0) {
                    return quantity;
                }
            } else {
                scanner.next();
            }

            System.out.println("Please enter a quantity greater than 0.");
        }
    }

    private String getItemName(int itemNumber) {
        return switch (itemNumber) {
            case 1 -> "Espresso";
            case 2 -> "Cappuccino";
            case 3 -> "Latte";
            case 4 -> "Croissant";
            case 5 -> "Sandwich";
            default -> "";
        };
    }

    private double getItemPrice(int itemNumber) {
        return switch (itemNumber) {
            case 1 -> 25.00;
            case 2 -> 35.00;
            case 3 -> 40.00;
            case 4 -> 30.00;
            case 5 -> 55.00;
            default -> 0.00;
        };
    }

    private double calculateSubtotal(double unitPrice, int quantity) {
        return unitPrice * quantity;
    }

    private void printReceipt(
            String customerName,
            String itemName,
            int quantity,
            double subtotal) {

        System.out.println();
        System.out.println("==============================");
        System.out.println("      LEXICON CAFE");
        System.out.println("==============================");

        System.out.printf("Customer  : %s%n", customerName);
        System.out.printf("Item      : %s x %d%n", itemName, quantity);
        System.out.printf("Subtotal  : %.2f SEK%n", subtotal);

        System.out.println("==============================");
        System.out.printf("   Thank you, %s!%n", customerName);
        System.out.println("   See you next time.");
        System.out.println("==============================");
    }


}