package salestaxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String filepath = args[0];
		ArrayList<Item> items = createOrder(filepath);
		Receipt receipt = createReceiptFromOrder(items);
		System.out.println(receipt);
	}

	public static ArrayList<Item> createOrder(String filepath) throws FileNotFoundException {
		TaxExemptInventory taxExemptInventory = new TaxExemptInventory();
		Parser parser = new Parser(taxExemptInventory);
		
		File aFile = new File(filepath);
		try (Scanner scanner = new Scanner(aFile)) {
			ArrayList<Item> items = new ArrayList<Item>();
			
			while(scanner.hasNextLine()) {				
				String entry = scanner.nextLine();
				Item item = parser.parseItem(entry);
				items.add(item);
			}
			return items;
		}
	}
	
	public static Receipt createReceiptFromOrder(ArrayList<Item> items) {
		Receipt receipt = new Receipt();
		
		for (Item item: items) {
			receipt.add(item);
		}
		return receipt;
	}
}
