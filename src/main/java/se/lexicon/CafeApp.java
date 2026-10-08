package main.java.se.lexicon;

import java.util.Scanner;

public class CafeApp {

    private static final double VAT_RATE = 0.12;
    private static final double MEMBER_DISCOUNT_RATE = 0.15;
    private static final double LARGE_ORDER_DISCOUNT_RATE = 0.10;
    private static final double DISCOUNT_THRESHOLD = 150.00;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        int customersServed = 0;
        double totalRevenue = 0.00;

        while (true) {

            if (!startNextCustomer(scanner)) {
                break;
            }

            String customerName = getCustomerName(scanner);

            // "done" can also be entered instead of a customer name.
            if (customerName == null) {
                break;
            }

            greetCustomer(customerName);
            displayMenu();

            int itemNumber = getItemNumber(scanner);
            int quantity = getQuantity(scanner);

            String itemName = getItemName(itemNumber);
            double unitPrice = getItemPrice(itemNumber);

            double subtotal = calculateSubtotal(unitPrice, quantity);

            boolean loyaltyMember = isLoyaltyMember(scanner);
            double discount = calculateDiscount(subtotal, loyaltyMember);

            double priceAfterDiscount = subtotal - discount;

            double vat = calculateVat(priceAfterDiscount);
            double total = priceAfterDiscount + vat;

            printReceipt(
                    customerName,
                    itemName,
                    quantity,
                    subtotal,
                    discount,
                    vat,
                    total
            );

            customersServed++;
            totalRevenue += total;
        }

        printEndOfDayReport(customersServed, totalRevenue);

        scanner.close();
    }

    private boolean startNextCustomer(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("Cashier Please Enter");
            System.out.println("NEXT to continue");
            System.out.println("DONE to exit");
            System.out.println();


            String command = scanner.next().trim().toLowerCase();

            if (command.equals("next")) {
                return true;
            }

            if (command.equalsIgnoreCase("DONE")) {
                return false;
            }

            System.out.println();
            System.out.println("Cashier Please Enter");
            System.out.println("NEXT to continue");
            System.out.println("DONE to exit");
            System.out.println();

        }
    }

    private String getCustomerName(Scanner scanner) {
        System.out.println();
        System.out.print("Welcome! What is your name?");

        String customerName = scanner.next().trim();

        if (customerName.equalsIgnoreCase("DONE")) {
            return null;
        }

        return customerName;
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

    private boolean isLoyaltyMember(Scanner scanner) {
        while (true) {
            System.out.print("Loyalty member? (yes/no): ");

            String answer = scanner.next().trim().toLowerCase();

            if (answer.equals("yes")) {
                return true;
            }

            if (answer.equals("no")) {
                return false;
            }

            System.out.println("Please answer yes or no.");
        }
    }

    private double calculateDiscount(
            double subtotal,
            boolean loyaltyMember) {

        if (loyaltyMember) {
            return subtotal * MEMBER_DISCOUNT_RATE;
        }

        if (subtotal > DISCOUNT_THRESHOLD) {
            return subtotal * LARGE_ORDER_DISCOUNT_RATE;
        }

        return 0.00;
    }

    private double calculateVat(double priceAfterDiscount) {
        return priceAfterDiscount * VAT_RATE;
    }

    private void printReceipt(
            String customerName,
            String itemName,
            int quantity,
            double subtotal,
            double discount,
            double vat,
            double total) {

        System.out.println();
        System.out.println("==============================");
        System.out.println("      LEXICON CAFE");
        System.out.println("==============================");

        System.out.printf("Customer  : %s%n", customerName);
        System.out.printf("Item      : %s x %d%n", itemName, quantity);
        System.out.printf("Subtotal  : %.2f SEK%n", subtotal);

        if (discount > 0) {
            System.out.printf("Discount  : -%.2f SEK%n", discount);
        }

        System.out.printf("VAT       : %.2f SEK%n", vat);
        System.out.println("------------------------------");
        System.out.printf("TOTAL     : %.2f SEK%n", total);
        System.out.println("==============================");
        System.out.printf("   Thank you, %s!%n", customerName);
        System.out.println("   See you next time.");
        System.out.println("==============================");
    }

    private void printEndOfDayReport(
            int customersServed,
            double totalRevenue) {

        System.out.println();
        System.out.println("==============================");
        System.out.println("      END OF DAY REPORT");
        System.out.println("==============================");
        System.out.printf("Customers served : %d%n", customersServed);
        System.out.printf("Total revenue    : %.2f SEK%n", totalRevenue);
        System.out.println("==============================");
    }
}