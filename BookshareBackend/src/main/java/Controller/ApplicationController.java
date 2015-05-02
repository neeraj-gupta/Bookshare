package Controller;

import java.security.InvalidParameterException;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import config.SpringMongoConfig;
import Model.Book;
import Model.Student;

@RestController
@RequestMapping("/")
public class ApplicationController {
	Integer student_id = -1;
	
	@RequestMapping( method = RequestMethod.GET, value = "/")
    public @ResponseBody String getHome() {    	
    	return "index";
    }
	
	@RequestMapping( method = RequestMethod.GET, value = "/error")
    public @ResponseBody String getError() {    	
    	return "login";
    }

    @RequestMapping("/resource")
	public Map<String, Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", "Hello World");
	    return model;
    }
    
    // Register Student
    @RequestMapping( method = RequestMethod.POST, value = "/student")
    public @ResponseBody Student createStudent(@RequestParam(value = "firstname", required = true) String firstname,
    		@RequestParam(value = "lastname", required = true) String lastname,
    		@RequestParam(value = "email", required = true) String email,
    		@RequestParam(value = "phone", required = false) String phone,
    		@RequestParam(value = "university", required = false) String university) {
    			
    			//For Annotation
    			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
    			checkValidStudent(firstname,lastname,email);
    			Student student = new Student(firstname,lastname,email,phone,university);
    		    
    			// Save in Database
    			mo.save(student);
    			System.out.println("1. Student added : " + student);
    			return student;
    }
    
    // Create Book
    @RequestMapping( method = RequestMethod.POST, value = "/createbook")
    public @ResponseBody Book createBook(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			checkValidBook(title, author, isbn, price);
			Book book = new Book(student_id, title, author, isbn, desc, price);
		    
			// Save in Database
			mo.save(book);
			System.out.println("1. Book added : " + book);
			return book;
    }
    
    // Post for a required Book.
    @RequestMapping( method = RequestMethod.POST, value = "/postbook")
    public @ResponseBody Book postForBook(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			checkValidBook(title, author, isbn, price);
			Book book = new Book(student_id, title, author, isbn, desc, price);
		    
			// Save in Database
			mo.save(book);
			System.out.println("1. Book required Posted : " + book);
			return book;
    }

    // List of all available Book.
    @RequestMapping( method = RequestMethod.GET, value = "/listbook")
    public @ResponseBody String listBooks() {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			
			System.out.println("1. All for Listing Found : ");
			return "Success";
    }
    
    // Search for a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/searchbook")
    public @ResponseBody Book searchBook(@PathVariable(value = "id")String id, @RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			checkValidBook(title, author, isbn, price);
			Book book = new Book(student_id, title, author, isbn, desc, price);
		    
			// Save in Database
			mo.save(book);
			System.out.println("1. Search for a book : " + book);
			return book;
    }
    
    // Buy a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/buybook/{id}")
    public @ResponseBody String buyBook(@PathVariable(value = "id")String id, @RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			Book book = new Book(student_id, title, author, isbn, desc, price);
			// Save in Database
			mo.save(book);
			System.out.println("1. Book required Posted : ");
			return "Success";
    }
    
    // Bid for a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/bidbook/{id}")
    public @ResponseBody Book bidBook(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			
			checkValidBook(title, author, isbn, price);
			Book book = new Book(student_id, title, author, isbn, desc, price);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			// Save in Database
			mo.save(book);
			System.out.println("1. Book Bid Successfull Posted : ");
			return book;
    }
    
    // Accept offer for a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/acceptoffer/{id}")
    public @ResponseBody Book acceptOffer(@PathVariable(value="id")String id, @RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			checkValidBook(title, author, isbn, price);
			Book book = new Book(student_id, title, author, isbn, desc, price);
		    
			// Save in Database
			mo.save(book);
			System.out.println("1. Book Offer Accepted ");
			return book;
    }
    
    // Give Feedback for a Book.
    @RequestMapping( method = RequestMethod.POST, value = "/feedback")
    public @ResponseBody Book feedback(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			checkValidBook(title, author, isbn, price);
			Book book = new Book(student_id, title, author, isbn, desc, price);
		    
			// Save in Database
			mo.save(book);
			System.out.println("Posted Feed Back: ");
			return book;
    }
    
    // Give rating to a buyer/seller.
    @RequestMapping( method = RequestMethod.POST, value = "/rate")
    public @ResponseBody String ratePeople(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			// For Annotation
			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mo = (MongoOperations) ctx.getBean("mongoTemplate");
			checkValidBook(title, author, isbn, price);
			Book book = new Book(student_id, title, author, isbn, desc, price);
		    
			// Save in Database
			mo.save(book);
			System.out.println("Give Rating : ");
			return "Success";
    }
    
    
	private void checkValidBook(String title, String author, String isbn, String price) {
		// TODO Auto-generated method stub
		if(title == null || author == null || isbn == null || price == null){
    		throw new InvalidParameterException();
    	}
	}

	private void checkValidStudent(String firstname, String lastname, String email) {
		// TODO Auto-generated method stub
		if(firstname == null || lastname == null || email == null){
    		throw new InvalidParameterException();
    	}
	}    
}
