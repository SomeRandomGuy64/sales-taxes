package salestaxes;

import java.util.ArrayList;

public class Receipt {
	private ArrayList<Item> items;
	private double totalTax;
	private double totalPrice;
	
	public Receipt() {
		items = new ArrayList<Item>();
		totalTax = 0;
		totalPrice = 0;
	}
	
	public void add(Item item) {
		items.add(item);
		totalTax += item.getTax();
		totalPrice += item.getPriceWithTax();
	}
	
	@Override
	public String toString() {
		String itemString = "";
		
		for (Item item: items) {
			itemString += item.toString();
		}
		itemString += String.format("Sales taxes: %.2f\n", totalTax);
		itemString += String.format("Total: %.2f", totalPrice);
		
		return itemString;
	}
}
