package salestaxes;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class OrderEntry {
	private int numberOfItems;
	private String item;
	private double price;
	
	public int getNumberOfItems() {
		return this.numberOfItems;
	}
	
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
	public String getItem() {
		return this.item;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setOrderEntry(Scanner scanner) {
		String entry = scanner.nextLine();
		String[] entryInformation = entry.split(" ");
		
		String itemDescription = "";
		for (int i = 0; i < entryInformation.length; i++) {
			
			if (i == 0) {
				setNumberOfItems(Integer.parseInt(entryInformation[0]));
				
			} else if (i > 0 && i < entryInformation.length - 2) {
				itemDescription += entryInformation[i] + " ";
				
			} else if (i == entryInformation.length - 1) {
				setPrice(Double.parseDouble(entryInformation[i]));
			}
		}
		
		setItem(itemDescription.trim());
	}
}
