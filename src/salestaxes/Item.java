package salestaxes;

public class Item {
	private int amount;
	private String itemDescription;
	private double price;
	private double tax;
	private double priceWithTax;
	private boolean isTaxExempt;
	private boolean isImported;
	private static final double BASIC_TAX = 0.1;
	private static final double IMPORT_DUTY = 0.05;
	
	public Item(int amount, String itemDescription, double price) {
		this.amount = amount;
		this.itemDescription = itemDescription;
		this.price = price;
		this.isImported = checkIsImported();
		this.isTaxExempt = false;
		this.tax = 0;
		this.priceWithTax = 0;
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
	
	public void setIsTaxExempt(boolean isTaxExempt) {
		this.isTaxExempt = isTaxExempt;
	}
	
	public boolean isImported() {
		return this.isImported;
	}
	
	public boolean isTaxExempt() {
		return this.isTaxExempt;
	}
	
	public boolean checkIsImported() {
		return this.itemDescription.contains("imported");
	}
	
	public double getPriceWithTax() {
		return this.priceWithTax;
	}
	
	public double getTax() {
		return this.tax;
	}
	
	public void calculateTax() {
		double taxRate = workOutTaxRate();
		
		this.tax = price * taxRate;
		this.tax = roundTaxUp();
		
		this.priceWithTax = this.price + this.tax;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s: %.2f\n", getAmount(), getItemDescription(), getPriceWithTax());
	}
	
	private double workOutTaxRate() {
		double taxRate = 0;
		if (!isTaxExempt()) {
			taxRate += BASIC_TAX;
		}
		if (isImported()) {
			taxRate += IMPORT_DUTY;
		}
		return taxRate;
	}
	
	private double roundTaxUp() {
		return Math.ceil(this.tax * 20.0) / 20.0;
	}
}