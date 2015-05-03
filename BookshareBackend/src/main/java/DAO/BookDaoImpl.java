package DAO;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import config.SpringMongoConfig;
import Model.Book;
import MyExceptions.Exceptions;

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
		
	}

	@Override
	public Book deleteBook(Integer b_id) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("id").is(b_id));
		Book book = mongoOps.findOne(query, Book.class, Book_COLLECTION);
		
		if(book == null){
			throw new Exceptions.BookNotFoundException();
		}
		
		mongoOps.remove(query, Book.class, Book_COLLECTION);
		
		return book;
	}

	@Override
	public Book searchBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bidBook(Integer b_id) {
		// TODO Auto-generated method stub
		
	}
}
