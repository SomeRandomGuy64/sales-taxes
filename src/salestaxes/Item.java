package salestaxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Item {
	private int amount;
	private String itemDescription;
	private double price;
	private boolean isBasicTaxExempt;
	private boolean isImported;
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getItemDescription() {
		return this.itemDescription;
	}
	
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean getIsImported() {
		return this.isImported;
	}
	
	public boolean getIsBasicTaxExempt() {
		return this.isBasicTaxExempt;
	}
	
	public boolean checkIsImported() {
		if (this.itemDescription.contains("imported")) {
			return true;
		}
		return false;
	}
	
	public boolean checkIsBasicTaxExempt() throws FileNotFoundException {
		File basicTaxExemptionFile = new File("src/resources/basic_tax_exempt.txt");
		try (Scanner scanner = new Scanner(basicTaxExemptionFile)) {
			while(scanner.hasNextLine()) {
				if (this.itemDescription.toLowerCase().contains(scanner.nextLine().toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	public void parseItem(String[] itemInformation) throws FileNotFoundException {
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
		setItem(itemAmount, itemDescription.trim(), itemPrice);
	}

	private void setItem(int itemAmount, String itemDescription, double itemPrice) throws FileNotFoundException {
		this.amount = itemAmount;
		this.itemDescription = itemDescription;
		this.price = itemPrice;
		this.isBasicTaxExempt = checkIsBasicTaxExempt();
		this.isImported = checkIsImported();
	}
}
