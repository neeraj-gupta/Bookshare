package Model;

public class Book {
	private static int counter;
	
	private Integer bookId;
	private Integer ownerId;
	private String bookTitle;
	private String bookAuthor;
	private String bookDesc;
	private String bookISBN;
	private String bookCondition;
	
	public Book(Integer ownerId, String Title, String Author, String Desc, String ISBN, String condition){
		super();
		bookId = counter++;
		this.ownerId = ownerId;
		this.bookTitle = Title;
		this.bookAuthor = Author;
		this.bookDesc = Desc;
		this.bookISBN = ISBN;
		this.bookCondition = condition;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public String getBookCondition() {
		return bookCondition;
	}

	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
}
