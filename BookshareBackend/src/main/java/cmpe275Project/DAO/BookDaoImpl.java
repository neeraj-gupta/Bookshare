package cmpe275Project.DAO;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cmpe275Project.Model.Book;
import cmpe275Project.MyExceptions.Exceptions;
import cmpe275Project.config.SpringMongoConfig;

public class BookDaoImpl implements BookDao {

	private static MongoOperations mongoOps;
	private static final String Book_COLLECTION = "books";  
	
	public BookDaoImpl(){
		if(mongoOps == null){
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	    	mongoOps = (MongoOperations)context.getBean("mongoTemplate");
		}
	}

	@Override
	public void createBook(Book book) {
		// TODO Auto-generated method stub
		if(!isExistingBook(book.getBookId())){
			book.setBookId(book.getBookId() + 1);
		}
		
		System.out.println("mongoOps " + mongoOps);
		mongoOps.insert(book, Book_COLLECTION);
	}
	
	@Override
	public Book readBook(Integer b_id) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(b_id));
		Book book = mongoOps.findOne(query, Book.class, Book_COLLECTION);
		return book;
	}

	private boolean isExistingBook(Integer bookId) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("id").is(bookId));
		Book existingId = mongoOps.findOne(query, Book.class, Book_COLLECTION);

		if(existingId != null){
			return true;
		}
		
		return false;
	}

	@Override
	public Book deleteBook(Integer b_id) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(b_id));
		Book book = mongoOps.findOne(query, Book.class, Book_COLLECTION);
		
		if(book == null){
			throw new Exceptions.BookNotFoundException();
		}
		
		mongoOps.remove(query, Book.class);
		
		return book;
	}

	@Override
	public List<Book> searchBook(String searchkey) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(searchkey));
		List<Book> book = mongoOps.find(query, Book.class, Book_COLLECTION);
		return book;
	}

	@Override
	public void bidBook(Integer b_id) {
		// TODO Auto-generated method stub
		
	}
}
