package salestaxes;

import java.util.ArrayList;

public class Receipt {
	private ArrayList<ReceiptEntry> receiptEntries = new ArrayList<ReceiptEntry>();
	private double totalTax;
	private double totalPrice;
	
	public void addReceiptEntryToReceipt(ReceiptEntry receiptEntry) {
		receiptEntries.add(receiptEntry);
		totalTax += receiptEntry.getTotalTax();
		totalPrice += receiptEntry.getPriceWithTax();
	}
	
	@Override
	public String toString() {
		String receiptString = "";
		
		for (ReceiptEntry receiptEntry: receiptEntries) {
			receiptString += receiptEntry.toString();
		}
		receiptString += String.format("Sales taxes: %.2f\n", totalTax);
		receiptString += String.format("Total: %.2f", totalPrice);
		
		return receiptString;
	}
}
