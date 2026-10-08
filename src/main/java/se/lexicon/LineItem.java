package main.java.se.lexicon;

public class LineItem {

    private String itemName;
    private double unitPrice;
    private int quantity;

    public LineItem(String itemName, double unitPrice, int quantity) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public double lineTotal() {
        return unitPrice * quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}