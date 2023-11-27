package salestaxes;

public class TaxProcessor {
	private static final double BASIC_TAX = 0.1;
	private static final double IMPORT_DUTY = 0.05;
	
	public double finalItemPrice(Item item) {
		return calculateTax(item) + item.getPrice();
	}
	
	public double calculateTax(Item item) {		
		double basicTaxToAdd = 0;
		double importDutyToAdd = 0;
		if (!item.getIsTaxExempt()) {
			basicTaxToAdd = item.getPrice() * BASIC_TAX;
		}
		if (item.getIsImported()) {
			importDutyToAdd = item.getPrice() * IMPORT_DUTY;
		}
		
		double totalTax = basicTaxToAdd + importDutyToAdd;
		totalTax = roundTaxUp(totalTax);
		
		return totalTax;
	}
	
	private double roundTaxUp(double tax) {
		return Math.ceil(tax * 20.0) / 20.0;
	}
	
}
