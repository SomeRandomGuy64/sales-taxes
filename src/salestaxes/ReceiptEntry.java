package salestaxes;

public class ReceiptEntry {
	private int numberOfItems;
	private String item;
	private double priceWithTax;
	private double totalTax;
	
	public ReceiptEntry(int numberOfItems, String item, double priceWithTax, double totalTax) {
		this.numberOfItems = numberOfItems;
		this.item = item;
		this.priceWithTax = priceWithTax;
		this.totalTax  = totalTax;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getPriceWithTax() {
		return priceWithTax;
	}

	public void setPriceWithTax(double priceWithTax) {
		this.priceWithTax = priceWithTax;
	}
	
	public double getTotalTax() {
		return totalTax;
	}
	
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	
	public void printReciptEntry() {
		System.out.println(String.format("%d %s: %.2f", getNumberOfItems(), getItem(), getPriceWithTax()));
	}
	
}
