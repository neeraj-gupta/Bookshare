package cmpe275Project.Model;

public class BookBids {
	private static int counter;
	
	private Integer bidId;
	private Integer bidderId;
	private Integer bookId;
	private String bookTitle;
	private float bidPrice;
	private float basePrice;
	
	public BookBids(Integer bidderId, Integer bookId, String bookTitle, float bidPrice, float basePrice){
		super();
		setBidId(counter++);
		this.setBidderId(bidderId);
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.setBidPrice(bidPrice);
		this.setBasePrice(basePrice);
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public float getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Integer getBidderId() {
		return bidderId;
	}

	public void setBidderId(Integer bidderId) {
		this.bidderId = bidderId;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
}
