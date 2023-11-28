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
				Parser parser = new Parser();
				
				String entry = scanner.nextLine();
				String[] itemInformation = entry.split(" ");
				parser.parseItem(itemInformation);
				Item item = new Item(parser.getAmount(), parser.getDescription(), parser.getPrice());
				setItemTaxValues(item, taxExemptInventory);
				items.add(item);
			}
			return items;
		}
	}
	
	public static void setItemTaxValues(Item item, TaxExemptInventory taxExemptInventory) {
		item.setIsTaxExempt(taxExemptInventory.getTaxExemptionStatus(item));
		item.calculateTax();
	}
	
	public static Receipt createReceiptFromOrder(ArrayList<Item> items) {
		Receipt receipt = new Receipt();
		
		for (Item item: items) {
			receipt.add(item);
		}
		return receipt;
	}
	
	public static void printReceipt(Receipt receipt) {
		System.out.println(receipt);
	}
}
