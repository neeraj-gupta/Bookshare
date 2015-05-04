package cmpe275Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
	private static Integer counter = 1;
	@Id
	private Integer bookId;
	private Integer ownerId;
	private String bookTitle;
	private String bookAuthor;
	private String bookDesc;
	private String bookISBN;
	private String bookCondition;
	
	public Book(Integer ownerId, String bookTitle, String bookAuthor, String bookDesc, String bookISBN, String bookCondition){
		super();
		bookId = counter++;
		this.ownerId = ownerId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookDesc = bookDesc;
		this.bookISBN = bookISBN;
		this.bookCondition = bookCondition;
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

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getBookCondition() {
		return bookCondition;
	}

	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}
}
