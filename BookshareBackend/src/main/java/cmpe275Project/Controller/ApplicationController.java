package cmpe275Project.Controller;

import java.security.InvalidParameterException;
import java.util.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cmpe275Project.DAO.*;
import cmpe275Project.Model.*;

@RestController
@RequestMapping("/")
public class ApplicationController {
	Integer student_id = 1;
	private StudentDao studentdao = new StudentDaoImpl();
	private BookDao bookdao = new BookDaoImpl();
	private RentOrBuyDao rentOrBuyDao = new RentOrBuyDaoImpl(); 
	
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

    		Student student = new Student(firstname,lastname,email,phone,university);
			checkValidStudent(student);
		    
			// Call StudentDao Class method to create Student.
			studentdao.createStudent(student);
			System.out.println("1. Student added : " + student);
			return student;
    }
    
    // Update Student Info
    @RequestMapping( method = RequestMethod.POST, value = "/editStudent")
    public @ResponseBody Student updateStudent(@RequestParam(value = "firstname", required = true) String firstname,
    		@RequestParam(value = "lastname", required = true) String lastname,
    		@RequestParam(value = "email", required = true) String email,
    		@RequestParam(value = "phone", required = false) String phone,
    		@RequestParam(value = "university", required = false) String university) {

    		Student student = new Student(firstname,lastname,email,phone,university);
			checkValidStudent(student);
		    
			// Call StudentDao Class method to create Student.
			studentdao.updateStudent(student, student_id);
			System.out.println("1. Student Data Updated : " + student);
			return student;
    }
    
    // Create Book
    @RequestMapping( method = RequestMethod.POST, value = "/createbook")
    public @ResponseBody Book createBook(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "condition", required = false) String condition) {
    			
			Book book = new Book(student_id, title, author, isbn, desc, condition);
			checkValidBook(book);
			
			// Call StudentDao Class method to create Student.
			bookdao.createBook(book);
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
    					
			Book book = new Book(student_id, title, author, isbn, desc, price);
			checkValidBook(book);
			
			System.out.println("1. Book required Posted : " + book);
			return book;
    }

    // List of all available Book.
    @RequestMapping( method = RequestMethod.GET, value = "/listbook")
    public @ResponseBody String listBooks() {
    		
			System.out.println("1. All for Listing Found : ");
			return "Success";
    }
    
    // Search for a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/searchbook/{key}")
    public @ResponseBody List<Book> searchBook(@PathVariable(value = "key")String key) {
    		
    		List<Book> books = bookdao.searchBook(key);
			System.out.println("1. Search for a book : " + books);
			return books;
    }
    
    // Delete a Book.
    @RequestMapping( method = RequestMethod.DELETE, value = "/deletebook/{id}")
    public @ResponseBody Book deleteBook(@PathVariable(value = "id")String id) {
    	int idInt = Integer.parseInt(id);
    	Book book = bookdao.readBook(idInt);
    	if(book.getBookId() != null){
    		bookdao.deleteBook(idInt);
    	}
		System.out.println("1. Search for a book : " + book);
		return book;
    }
        
 	// Buy a Book. --- >>> Merge Transaction
 	@RequestMapping(method = RequestMethod.POST, value = "/buybook")
 	public @ResponseBody String buyBook(
 			@PathVariable(value = "id") int id)
 			 {

 		// Validate book
 		// checkValidBook(title, author, isbn, sellingPrice);

 		if (!(rentOrBuyDao.getBookStatus(id).equalsIgnoreCase("sold") || rentOrBuyDao.getBookStatus(id).equalsIgnoreCase("rented"))) {

 			// Change status in DB
 			if ((rentOrBuyDao.changeBookStatus(id, "Sold").equalsIgnoreCase("success"))) {
 				System.out.println("Book with id " + id + " bought");
 			} else {
 				System.out.println("Book buying failed, could not update");
 			}
 		} else {
 			System.out.println("Book you are trying to buy has status "
 					+ rentOrBuyDao.getBookStatus(id));
 		}

 		return "Success";
 	}

 	// Rent a Book.  --- >>> Merge Transaction
 	@RequestMapping(method = RequestMethod.POST, value = "/rentbook")
 	public @ResponseBody String rentBook(@PathVariable(value = "id") int id,
 			@RequestParam(value = "duration", required = true) String duration) {
 		
 		//Method to validate book
 		if (!(rentOrBuyDao.getBookStatus(id).equalsIgnoreCase("Sold") || rentOrBuyDao.getBookStatus(id).equalsIgnoreCase("Rented"))) {

 			// Change status in DB
 			if ((rentOrBuyDao.changeBookStatus(id, "Rented").equalsIgnoreCase("success"))) {
 				System.out.println("Book with id " + id + " rented for " + duration + " days");
 			} else {
 				System.out.println("Book buying failed, could not update");
 			}
 		} else {
 			System.out.println("Book you are trying to buy has status "
 					+ rentOrBuyDao.getBookStatus(id));
 		}

 		
 		return "Success";
 	}

    
    // Bid for a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/bidbook/{id}")
    public @ResponseBody Book bidBook(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			Book book = new Book(student_id, title, author, isbn, desc, price);
			
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
			
			//checkValidBook(title, author, isbn, price);
			Book book = new Book(student_id, title, author, isbn, desc, price);
		    
			System.out.println("1. Book Offer Accepted ");
			return book;
    }
    
    // Give Feedback for a Book.
    @RequestMapping( method = RequestMethod.POST, value = "/feedback")
    public @ResponseBody void feedback(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			System.out.println("Posted Feed Back: ");
    }
    
    // Give rating to a buyer/seller.
    @RequestMapping( method = RequestMethod.POST, value = "/rate")
    public @ResponseBody String ratePeople(@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			System.out.println("Give Rating : ");
			return "Success";
    }
    
    private void checkValidBook(Book book) {
		// TODO Auto-generated method stub
    	if(book.getBookTitle() == null || book.getBookAuthor() == null || book.getBookISBN() == null){
    		throw new InvalidParameterException();
    	}
    }
		
	private void checkValidStudent(Student student) {
		// TODO Auto-generated method stub
		if(student.getFirstName() == null || student.getLastName() == null || student.getEmail() == null || student.getUniversity() == null){
	    		throw new InvalidParameterException();
	    	}
	}
}
