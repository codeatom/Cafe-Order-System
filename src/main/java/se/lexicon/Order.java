package main.java.se.lexicon;

import java.util.ArrayList;

public class Order {

    private static final double VAT_RATE = 0.12;
    private static final double MEMBER_DISCOUNT_RATE = 0.15;
    private static final double LARGE_ORDER_DISCOUNT_RATE = 0.10;
    private static final double DISCOUNT_THRESHOLD = 150.00;

    private String customerName;
    private boolean loyaltyMember;

    private ArrayList<LineItem> items = new ArrayList<>();

    public void addItem(String itemName, double unitPrice, int quantity) {
        LineItem lineItem = new LineItem(itemName, unitPrice, quantity);
        items.add(lineItem);
    }

    public double calculateSubtotal() {
        double subtotal = 0.00;

        for (LineItem item : items) {
            subtotal += item.lineTotal();
        }

        return subtotal;
    }

    public double calculateDiscount() {
        double subtotal = calculateSubtotal();

        if (loyaltyMember) {
            return subtotal * MEMBER_DISCOUNT_RATE;
        }

        if (subtotal > DISCOUNT_THRESHOLD) {
            return subtotal * LARGE_ORDER_DISCOUNT_RATE;
        }

        return 0.00;
    }

    public double calculateVat() {
        double priceAfterDiscount =
                calculateSubtotal() - calculateDiscount();

        return priceAfterDiscount * VAT_RATE;
    }

    public double calculateTotal() {
        double priceAfterDiscount =
                calculateSubtotal() - calculateDiscount();

        return priceAfterDiscount + calculateVat();
    }

    public void printReceipt() {
        double subtotal = calculateSubtotal();
        double discount = calculateDiscount();
        double vat = calculateVat();
        double total = calculateTotal();

        System.out.println();
        System.out.println("==============================");
        System.out.println("      LEXICON CAFE");
        System.out.println("==============================");

        System.out.printf("Customer  : %s%n", customerName);
        System.out.println("------------------------------");

        for (LineItem item : items) {
            System.out.printf(
                    "  %-15s x%-3d %7.2f SEK%n",
                    item.getItemName(),
                    item.getQuantity(),
                    item.lineTotal()
            );
        }

        System.out.println("------------------------------");
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    public void setLoyaltyMember(boolean loyaltyMember) {
        this.loyaltyMember = loyaltyMember;
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }
}