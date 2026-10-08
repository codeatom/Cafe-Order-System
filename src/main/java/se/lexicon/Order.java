package main.java.se.lexicon;


public class Order {

    private static final double VAT_RATE = 0.12;
    private static final double MEMBER_DISCOUNT_RATE = 0.15;
    private static final double LARGE_ORDER_DISCOUNT_RATE = 0.10;
    private static final double DISCOUNT_THRESHOLD = 150.00;

    private String customerName;
    private String itemName;
    private int quantity;
    private double unitPrice;
    private boolean loyaltyMember;

    public double calculateSubtotal() {
        return unitPrice * quantity;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    public void setLoyaltyMember(boolean loyaltyMember) {
        this.loyaltyMember = loyaltyMember;
    }
}
