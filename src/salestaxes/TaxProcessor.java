package salestaxes;

public class TaxProcessor {
	private final double basicTax = 0.1;
	private final double importDuty = 0.05;
	private final String[] books = {"book"};
	private final String[] food = {"chocolate"};
	private final String[] medical = {"pills"};
	
	public double addTax(OrderEntry orderEntry) {		
		double basicTaxToAdd = 0;
		double importDutyToAdd = 0;
		if (!isBasicExempt(orderEntry.getItem())) {
			basicTaxToAdd = orderEntry.getPrice() * basicTax;
		}
		if (isImported(orderEntry.getItem())) {
			importDutyToAdd = orderEntry.getPrice() * importDuty;
		}
		
		double totalTax = roundTaxUp(basicTaxToAdd, importDutyToAdd);
		
		return totalTax;
	}
	
	private boolean isBasicExempt(String item) {
		return isBook(item) || isFood(item) || isMedical(item); 
	}
	
	private boolean isBook(String item) {
		return checkItemType(this.books, item);
	}
	
	private boolean isFood(String item) {
		return checkItemType(this.food, item);
	}
	
	private boolean isMedical(String item) {
		return checkItemType(this.medical, item);
	}
	
	private boolean isImported(String item) {
		if (item.toLowerCase().contains("imported")) {
			return true;
		}
		return false;
	}
	
	private boolean checkItemType(String[] items, String specifiedItem) {
		for (String item : items) {
			if (specifiedItem.toLowerCase().contains(item)) {
				return true;
			}
		}
		return false;
	}
	
	private double roundTaxUp(double basicTax, double importDuty) {
		return Math.ceil((basicTax + importDuty) * 20.0) / 20.0;
	}
}
