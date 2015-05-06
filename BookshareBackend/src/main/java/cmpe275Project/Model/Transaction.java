package cmpe275Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import cmpe275Project.Controller.DateParser;


@Document(collection = "transactions")
public class Transaction {
	private static int counter;
	int transactionId;
	int bookId;
	int buyer;
	int seller;
	String transactionType;
	String transactionDate;
	double sellingPrice;
	
	public Transaction(int bookId, int buyer, int seller, String transactionType, double sellingPrice){
		super();
		this.transactionId = counter++;
		this.bookId = bookId;
		this.buyer = buyer;
		this.seller = seller;
		this.transactionType = transactionType;
		this.setTransactionDate();
		this.sellingPrice = sellingPrice;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public int getBuyer() {
		return buyer;
	}
	
	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}
	
	public int getSeller() {
		return seller;
	}
	
	public void setSeller(int seller) {
		this.seller = seller;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate() {
		DateParser dparser = new DateParser();
		this.transactionDate = dparser.getDate();
	}
	
	public double getSellingPrice() {
		return sellingPrice;
	}
	
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
}
