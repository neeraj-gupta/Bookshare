package cmpe275Project.Controller;

import java.security.InvalidParameterException;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DBCollection;

import cmpe275Project.DAO.*;
import cmpe275Project.Model.Book;
import cmpe275Project.Model.PostBook;
import cmpe275Project.Model.Student;
import cmpe275Project.config.SpringMongoConfig;

@RestController
@RequestMapping("/")
public class ApplicationController {
	Integer student_id = 1;
	private StudentDao studentdao = new StudentDaoImpl();
	private BookDao bookdao = new BookDaoImpl();
	
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
    public @ResponseBody Student createStudent(@RequestBody Student student) {

    		Student studentLocal = new Student(student.getFirstName(),student.getLastName(),student.getEmail(),student.getPhone(),student.getUniversity());
			checkValidStudent(studentLocal);
		    
			// Call StudentDao Class method to create Student.
			studentdao.createStudent(studentLocal);
			System.out.println("1. Student added : " + studentLocal);
			return studentLocal;
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
    
        
    // Buy a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/buybook/{id}")
    public @ResponseBody String buyBook(@PathVariable(value = "id")Integer id, @RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "author", required = true) String author,
    		@RequestParam(value = "isbn", required = true) String isbn,
    		@RequestParam(value = "desc", required = false) String desc,
    		@RequestParam(value = "price", required = false) String price) {
    			
			Book book = new Book(student_id, title, author, isbn, desc, price);
			
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
    
    private void checkValidPostBook(PostBook postBook) {
		// TODO Auto-generated method stub
    	if(postBook.getBookTitle() == null || postBook.getBookAuthor() == null || postBook.getBookISBN() == null){
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
