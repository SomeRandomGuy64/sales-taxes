package salestaxes;

public class Item {
	private int amount;
	private String itemDescription;
	private double price;
	private double tax;
	
	public Item(int amount, String itemDescription, double price, double tax) {
		this.amount = amount;
		this.itemDescription = itemDescription;
		this.price = price;
		this.tax = tax;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public String getItemDescription() {
		return this.itemDescription;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public double getTax() {
		return this.tax;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s: %.2f\n", amount, itemDescription, price + tax);
	}
}