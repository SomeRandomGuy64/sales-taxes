package salestaxes;

public class Item {
	private int amount;
	private String itemDescription;
	private double price;
	private double tax;
	
	public Item(int amount, String itemDescription, double price, double taxRate) {
		this.amount = amount;
		this.itemDescription = itemDescription;
		this.price = price;
		this.tax = taxRate;
	}
	
	public double getTax() {
		return this.tax;
	}
	
	public double getPriceWithTax() {
		return price + tax;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s: %.2f\n", amount, itemDescription, price + tax);
	}
}