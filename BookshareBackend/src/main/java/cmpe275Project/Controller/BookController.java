package cmpe275Project.Controller;

import java.security.InvalidParameterException;
import java.util.*;

import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cmpe275Project.DAO.*;
import cmpe275Project.Model.Book;
import cmpe275Project.Model.PostBook;

@RestController
@RequestMapping("/")
public class BookController {
	Integer student_id = 1;
	private BookDao bookdao = new BookDaoImpl();
    
    // Create Book
    @RequestMapping( method = RequestMethod.POST, value = "/createbook")
    public @ResponseBody Book createBook(@RequestBody Book book) {
    	
			Book bookLocal = new Book(student_id, book.getBookTitle(), book.getBookAuthor(), book.getBookISBN(), book.getBookDesc(), book.getBookCondition());
			checkValidBook(bookLocal);
			
			// Call StudentDao Class method to create Student.
			bookdao.createBook(bookLocal);
			System.out.println("1. Book added : " + bookLocal);
			return bookLocal;
    }
    
	// Post for a required Book.
    @RequestMapping( method = RequestMethod.POST, value = "/postbook")
    public @ResponseBody PostBook postForBook(@RequestBody PostBook postBook) {
    	
    		PostBook postBookLocal = new PostBook(student_id, postBook.getBookTitle(), postBook.getBookAuthor(), postBook.getBookISBN(), postBook.getBookDesc(), postBook.getBookCondition(), postBook.getPrice());
    		checkValidPostBook(postBookLocal);
			bookdao.postBook(postBookLocal);
			System.out.println("1. Book required Posted : " + postBookLocal);
			return postBookLocal;
    }

    // List of all available Book.
    @RequestMapping( method = RequestMethod.GET, value = "/listallbooks/{id}")
    public ResponseEntity<JSONArray> listBooks(@PathVariable(value = "id")int id) {
    	
    	List<Book> books = bookdao.listAllBooks(id);
    	JSONArray jsonArray = new JSONArray();
    	for(Book book : books){
    		jsonArray.add(book);
    	}
    	
    	System.out.println("1. All for Listing Found : ");
    	//return jsonArray;
    	return new ResponseEntity<JSONArray>(jsonArray, HttpStatus.ACCEPTED);
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
	
}
