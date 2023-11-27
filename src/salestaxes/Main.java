package salestaxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String filepath = getFilepath(args);
		
		ArrayList<Item> items = createOrder(filepath);
		
		Receipt receipt = createReceiptFromOrder(items);
		
		printReceipt(receipt);
	}
	
	public static String getFilepath(String[] args) {
		return args[0];
	}
	
	public static ArrayList<Item> createOrder(String filepath) throws FileNotFoundException {
		TaxExemptInventory taxExemptInventory = new TaxExemptInventory();
		
		File aFile = new File(filepath);
		try (Scanner scanner = new Scanner(aFile)) {
			ArrayList<Item> items = new ArrayList<Item>();
			
			while(scanner.hasNextLine()) {
				String entry = scanner.nextLine();
				String[] itemInformation = entry.split(" ");
				Item item = parseItem(itemInformation);
				item.checkIsTaxExempt(taxExemptInventory);
				items.add(item);
			}
			return items;
		}
	}
	
	public static Item parseItem(String[] itemInformation) {
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
		Item item = new Item(itemAmount, itemDescription.trim(), itemPrice);
		return item;
	}
	
	public static Receipt createReceiptFromOrder(ArrayList<Item> items) {
		Receipt receipt = new Receipt();
		TaxProcessor taxProcessor = new TaxProcessor();
		
		for (Item item: items) {
			ReceiptEntry receiptEntry = new ReceiptEntry(item, taxProcessor);
			receipt.addReceiptEntryToReceipt(receiptEntry);
		}
		return receipt;
	}
	
	public static void printReceipt(Receipt receipt) {
		System.out.println(receipt);
	}

}
