package cmpe275Project.DAO;

import java.util.List;

import cmpe275Project.Model.Book;
import cmpe275Project.Model.PostBook;

public interface BookDao {
	public void createBook(Book book);
	public Book readBook(Integer b_id);
	public Book deleteBook(Integer b_id);
	public List<Book> searchBook(String key);
	public void bidBook(Integer b_id);
	void postBook(PostBook postBook);
	List<Book> listAllBooks(int id);
}
