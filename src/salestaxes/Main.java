package salestaxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<OrderEntry> orderEntries = createOrder();
		
		ArrayList<ReceiptEntry> receiptEntries = createReceiptFromOrder(orderEntries);
		
		printReceipt(receiptEntries);
	}
	
	public static ArrayList<OrderEntry> createOrder() throws FileNotFoundException {
		File aFile = new File("src/resources/input3.txt");
		Scanner scanner = new Scanner(aFile);
		
		ArrayList<OrderEntry> order = new ArrayList<OrderEntry>();
		
		while(scanner.hasNextLine()) {
			OrderEntry orderEntry = new OrderEntry();
			orderEntry.setOrderEntry(scanner);
			order.add(orderEntry);
		}
		
		scanner.close();
		
		return order;
	}
	
	public static ArrayList<ReceiptEntry> createReceiptFromOrder(ArrayList<OrderEntry> orderEntries) {
		ArrayList<ReceiptEntry> receiptEntries = new ArrayList<ReceiptEntry>();
		TaxProcessor taxProcessor = new TaxProcessor();
		
		for (OrderEntry orderEntry: orderEntries) {
			double totalTax = taxProcessor.addTax(orderEntry);
			
			ReceiptEntry receiptEntry = new ReceiptEntry(
					orderEntry.getNumberOfItems(),
					orderEntry.getItem(),
					totalTax + orderEntry.getPrice(),
					totalTax);
			
			receiptEntries.add(receiptEntry);
		}
		
		return receiptEntries;
	}
	
	public static void printReceipt(ArrayList<ReceiptEntry> receiptEntries) {
		double totalSalesTax = 0;
		double totalPrice = 0;
		
		for (ReceiptEntry receiptEntry: receiptEntries) {
			receiptEntry.printReciptEntry();
			totalSalesTax += receiptEntry.getTotalTax();
			totalPrice += receiptEntry.getPriceWithTax();
		}
		System.out.println(String.format("Sales taxes: %.2f", totalSalesTax));
		System.out.println(String.format("Total: %.2f", totalPrice));
	}

}
