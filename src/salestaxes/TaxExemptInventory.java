package salestaxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaxExemptInventory {
	private ArrayList<String> taxExemptItems = new ArrayList<String>();
	
	public TaxExemptInventory() throws FileNotFoundException {
		loadInventory();
	}
	
	public ArrayList<String> getTaxExemptItems() {
		return this.taxExemptItems;
	}
	
	public boolean getTaxExemptionStatus(Item item) {
		for (String taxExemptItem: taxExemptItems) {
			if (item.getItemDescription().toLowerCase().contains(taxExemptItem)) {
				return true;
			}
		}
		return false;
	}

	private void loadInventory() throws FileNotFoundException {
		File taxExemptionFile = new File("src/resources/basic_tax_exempt.txt");
		try (Scanner scanner = new Scanner(taxExemptionFile)) {
			while(scanner.hasNextLine()) {
				taxExemptItems.add(scanner.nextLine().toLowerCase());
			}
		}
	}
}
