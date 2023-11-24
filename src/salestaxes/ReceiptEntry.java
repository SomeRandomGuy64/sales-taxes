package salestaxes;

public class ReceiptEntry {
	private int amount;
	private String itemDescription;
	private double priceWithTax;
	private double totalTax;
	
	public ReceiptEntry(int amount, String itemDescription, double priceWithTax, double totalTax) {
		this.amount = amount;
		this.itemDescription = itemDescription;
		this.priceWithTax = priceWithTax;
		this.totalTax  = totalTax;
	}
	
	public ReceiptEntry(Item item, TaxProcessor taxProcessor) {
		this.amount = item.getAmount();
		this.itemDescription = item.getItemDescription();
		this.totalTax  = taxProcessor.calculateTax(item);
		this.priceWithTax = taxProcessor.finalItemPrice(item);
	}

	public int getAmount() {
		return amount;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}

	public double getPriceWithTax() {
		return priceWithTax;
	}
	
	public double getTotalTax() {
		return totalTax;
	}

	@Override
	public String toString() {
		return String.format("%d %s: %.2f", getAmount(), getItemDescription(), getPriceWithTax());
	}
}
