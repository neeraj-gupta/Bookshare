package cmpe275Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="rentorbuy")
public class RentOrBuy {
	
	int bookId;
	int ownerId;
	double rentPrice;
	double sellingPrice;
	String status; //Sold or Rented
	int duration;
	String type; //Rent, Sell or Both
	
	public RentOrBuy() {
		// TODO Auto-generated constructor stub
	}
	
	public RentOrBuy(int bookId, int ownerId, double rentPrice, double sellingPrice,
			String status, int duration, String type) {
		super();
		this.bookId = bookId;
		this.ownerId = ownerId;
		this.rentPrice = rentPrice;
		this.sellingPrice = sellingPrice;
		this.status = status;
		this.duration = duration;
		this.type = type;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public double getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

