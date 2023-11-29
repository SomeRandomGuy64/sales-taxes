package salestaxes;

public class Parser {
	private static final double BASIC_TAX = 0.1;
	private static final double IMPORT_DUTY = 0.05;
	private TaxExemptInventory taxExemptInventory;
	
	public Parser(TaxExemptInventory taxExemptInventory) {
		this.taxExemptInventory = taxExemptInventory;
	}
	
	public Item parseItem(String entry) {
		int itemAmount = 0;
		String itemDescription = "";
		double itemPrice = 0;
		
		String[] tokens = entry.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			
			if (i == 0) {
				itemAmount = Integer.parseInt(tokens[i]);
				
			} else if (i > 0 && i < tokens.length - 2) {
				itemDescription += tokens[i] + " ";
				
			} else if (i == tokens.length - 1) {
				itemPrice = Double.parseDouble(tokens[i]);
			}
		}
		
		itemDescription = itemDescription.trim();
		boolean isTaxExempt = taxExemptInventory.isTaxExempt(itemDescription);
		boolean isImported = itemDescription.contains("imported");
		double tax = calculateTax(itemPrice, isTaxExempt, isImported);
		Item item = new Item(itemAmount, itemDescription, itemPrice, tax);
		return item;
	}
	
	private double calculateTax(double price, boolean isTaxExempt, boolean isImported) {
		double taxRate = workOutTaxRate(isTaxExempt, isImported);
		
		double tax = price * taxRate;
		tax = roundTaxUp(tax);
		return tax;
	}
	
	private double workOutTaxRate(boolean isTaxExempt, boolean isImported) {
		double taxRate = 0;
		if (!isTaxExempt) {
			taxRate += BASIC_TAX;
		}
		if (isImported) {
			taxRate += IMPORT_DUTY;
		}
		return taxRate;
	}
	
	private double roundTaxUp(double tax) {
		return Math.ceil(tax * 20.0) / 20.0;
	}
}
