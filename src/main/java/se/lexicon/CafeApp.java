package main.java.se.lexicon;

import java.util.Scanner;

public class CafeApp {

    public void start() {
        Scanner scanner = new Scanner(System.in);

        int customersServed = 0;
        double totalRevenue = 0.00;

        while (true) {

            if (!startNextCustomer(scanner)) {
                break;
            }

            String customerName = getCustomerName(scanner);

            if (customerName == null) {
                break;
            }

            greetCustomer(customerName);
            displayMenu();

            boolean loyaltyMember = isLoyaltyMember(scanner);

            Order order = new Order();
            order.setCustomerName(customerName);
            order.setLoyaltyMember(loyaltyMember);

            // Inner loop to add multiple items to the same order.
            while (true) {

                int itemNumber = getItemNumber(scanner);

                if (itemNumber == 0) {
                    break;
                }

                int quantity = getQuantity(scanner);

                String itemName = getItemName(itemNumber);
                double unitPrice = getItemPrice(itemNumber);

                order.addItem(itemName, unitPrice, quantity);

                System.out.println(itemName + " added.");
                System.out.println();
            }

            order.printReceipt();

            customersServed++;
            totalRevenue += order.calculateTotal();
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

            if (command.equals("done")) {
                return false;
            }

            System.out.println();
            System.out.println("Please enter NEXT or DONE.");
        }
    }

    private String getCustomerName(Scanner scanner) {
        System.out.print("Welcome! What is your name? ");

        String customerName = scanner.next().trim();

        if (customerName.equalsIgnoreCase("done")) {
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
            System.out.print("Enter item number (1-5, or 0 to finish): ");

            if (scanner.hasNextInt()) {
                int itemNumber = scanner.nextInt();

                if (itemNumber >= 0 && itemNumber <= 5) {
                    return itemNumber;
                }
            } else {
                scanner.next();
            }

            System.out.println(
                    "Please enter a number between 1 and 5, or 0 to finish."
            );
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