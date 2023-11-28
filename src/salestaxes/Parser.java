package salestaxes;

public class Parser {
	private int itemAmount;
	private String itemDescription;
	double itemPrice;
	
	public Parser() {
		itemAmount = 0;
		itemDescription = "";
		itemPrice = 0;
	}

	public void parseItem(String[] itemInformation) {
		for (int i = 0; i < itemInformation.length; i++) {
			
			if (i == 0) {
				itemAmount = Integer.parseInt(itemInformation[i]);
				
			} else if (i > 0 && i < itemInformation.length - 2) {
				itemDescription += itemInformation[i] + " ";
				
			} else if (i == itemInformation.length - 1) {
				itemPrice = Double.parseDouble(itemInformation[i]);
			}
		}
		itemDescription = itemDescription.trim();
	}
	
	public int getAmount() {
		return itemAmount;
	}
	
	public String getDescription() {
		return itemDescription;
	}
	
	public double getPrice() {
		return itemPrice;
	}
}
