package cmpe275Project.DAO;

import cmpe275Project.Model.Book;

public interface BookDao {
	public void createBook(Book book);
	public Book deleteBook(Integer b_id);
	public Book searchBook();
	public void bidBook(Integer b_id);
}
