package cmpe275Project.DAO;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cmpe275Project.Model.BookBids;
import cmpe275Project.config.SpringMongoConfig;

public class BookBidsDaoImpl implements BookBidsDao {
	
	private static MongoOperations mongoOps;
	
	

	public BookBidsDaoImpl() {
		// TODO Auto-generated constructor stub
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		try{
		if(mongoOps == null){
			
	    	mongoOps = (MongoOperations)context.getBean("mongoTemplate");
		}
		}
		catch(Exception e)
		{
			System.out.println("Exception caught in BookBidsDaoImpl with message " + e.getMessage());
		}
		finally
		{
			context.close();
		}
	}
	@Override
	public void addBid(BookBids bookBids) {
		// TODO Auto-generated method stub
			System.out.println("mongoOps " + mongoOps);
			mongoOps.insert(bookBids);
	}
	@Override
	public List<BookBids> listBids(int id) {
		// TODO Auto-generated method stub
		
		Query query = new Query(Criteria.where("bookId").is(id));
		List<BookBids> bids = mongoOps.find(query, BookBids.class);
		return bids;
	}

	/*
	 * public List<Book> listAllBooks(int id) {
		Query query = new Query(Criteria.where("ownerId").is(id));
		List<Book> book = mongoOps.find(query, Book.class, Book_COLLECTION);
		return book;
	}
	 */
}
