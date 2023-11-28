package salestaxes;

public class Parser {
	private static final double BASIC_TAX = 0.1;
	private static final double IMPORT_DUTY = 0.05;
	private TaxExemptInventory taxExemptInventory;
	
	public Parser(TaxExemptInventory taxExemptInventory) {
		this.taxExemptInventory = taxExemptInventory;
	}
	
	public Item parseItem(String[] itemInformation) {
		int itemAmount = 0;
		String itemDescription = "";
		double itemPrice = 0;
		
		for (int i = 0; i < itemInformation.length; i++) {
			
			if (i == 0) {
				itemAmount = Integer.parseInt(itemInformation[i]);
				
			} else if (i > 0 && i < itemInformation.length - 2) {
				itemDescription += itemInformation[i] + " ";
				
			} else if (i == itemInformation.length - 1) {
				itemPrice = Double.parseDouble(itemInformation[i]);
			}
		}
		
		itemDescription = itemDescription.trim();
		boolean isTaxExempt = taxExemptInventory.getTaxExemptionStatus(itemDescription);
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
