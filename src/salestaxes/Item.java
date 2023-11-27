package salestaxes;

public class Item {
	private int amount;
	private String itemDescription;
	private double price;
	private boolean isTaxExempt;
	private boolean isImported;
	
	public Item(int amount, String itemDescription, double price) {
		this.amount = amount;
		this.itemDescription = itemDescription;
		this.price = price;
		this.isImported = checkIsImported();
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
	
	public boolean getIsImported() {
		return this.isImported;
	}
	
	public boolean getIsTaxExempt() {
		return this.isTaxExempt;
	}
	
	public boolean checkIsImported() {
		return this.itemDescription.contains("imported");
	}
	
	public void checkIsTaxExempt(TaxExemptInventory taxExemptInventory) {
		for (String taxExemptItem: taxExemptInventory.getTaxExemptItems()) {
			if (itemDescription.toLowerCase().contains(taxExemptItem)) {
				this.isTaxExempt = true;
			}
		}
	}
}
