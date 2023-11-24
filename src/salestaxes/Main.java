package salestaxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Item> items = createOrder();
		
		ArrayList<ReceiptEntry> receiptEntries = createReceiptFromOrder(items);
		
		printReceipt(receiptEntries);
	}
	
	public static ArrayList<Item> createOrder() throws FileNotFoundException {
		File aFile = new File("src/resources/input3.txt");
		try (Scanner scanner = new Scanner(aFile)) {
			ArrayList<Item> items = new ArrayList<Item>();
			
			while(scanner.hasNextLine()) {
				Item item = new Item();
				String entry = scanner.nextLine();
				String[] itemInformation = entry.split(" ");
				item.parseItem(itemInformation);
				items.add(item);
			}
			return items;
		}
	}
	
	public static ArrayList<ReceiptEntry> createReceiptFromOrder(ArrayList<Item> items) throws FileNotFoundException {
		ArrayList<ReceiptEntry> receiptEntries = new ArrayList<ReceiptEntry>();
		TaxProcessor taxProcessor = new TaxProcessor();
		
		for (Item item: items) {
			double totalTax = taxProcessor.calculateTax(item);
			
			ReceiptEntry receiptEntry = new ReceiptEntry(
					item.getAmount(),
					item.getItemDescription(),
					totalTax + item.getPrice(),
					totalTax
					);
			
			receiptEntries.add(receiptEntry);
		}
		
		return receiptEntries;
	}
	
	public static void printReceipt(ArrayList<ReceiptEntry> receiptEntries) {
		double totalSalesTax = 0;
		double totalPrice = 0;
		
		for (ReceiptEntry receiptEntry: receiptEntries) {
			System.out.println(receiptEntry);
			totalSalesTax += receiptEntry.getTotalTax();
			totalPrice += receiptEntry.getPriceWithTax();
		}
		System.out.println(String.format("Sales taxes: %.2f", totalSalesTax));
		System.out.println(String.format("Total: %.2f", totalPrice));
	}

}
