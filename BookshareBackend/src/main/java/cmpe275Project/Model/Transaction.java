package cmpe275Project.Model;

import java.util.Date;

public class Transaction {
	private static int counter;
	int transactionId;
	int bookId;
	int buyer;
	int seller;
	String transactionType;
	Date transactionDate;
	double sellingPrice;
	
	Transaction(int bookid, int buyer, int seller, String type, Date date, double price){
		super();
		this.transactionId = counter++;
		this.bookId = bookid;
		this.buyer = buyer;
		this.seller = seller;
		this.transactionType = type;
		this.transactionDate = date;
		this.sellingPrice = price;
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
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date date) {
		this.transactionDate = date;
	}
	
	public double getSellingPrice() {
		return sellingPrice;
	}
	
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
}