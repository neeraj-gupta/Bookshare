package cmpe275Project.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cmpe275Project.DAO.BookBidsDao;
import cmpe275Project.DAO.BookBidsDaoImpl;
import cmpe275Project.Model.Book;
import cmpe275Project.Model.BookBids;

public class BiddingController {
	
	Integer student_id = 1;
	BookBidsDao bookBidsDao = new BookBidsDaoImpl();
	
	// Bid for a Book.
    @RequestMapping( method = RequestMethod.POST, value = "/bidbook")
    public @ResponseBody String bidBook(@RequestBody BookBids bookBids)
    		{
    			
			String message = "";
			
			if(this.checkValidBid(bookBids))
			{
				BookBids bidsObj = new BookBids(student_id, bookBids.getBookId(), bookBids.getBookTitle(), bookBids.getBidPrice(), bookBids.getBasePrice());
				bookBidsDao.addBid(bidsObj);
			}
			else
			{
				message = "Invalid BidderId or BookId or Bidding Amount. Check parameters!";
			}
			
			
			 return message;
    }
    
    //Show all bids for a book
    @RequestMapping( method = RequestMethod.GET, value = "/listallbids/{id}") //Check URL with team
    public List<BookBids> listBids(@PathVariable(value = "id")int id) {
			
			//checkValidBook(title, author, isbn, price);
			List<BookBids> bids = bookBidsDao.listBids(id);
		    
			System.out.println("Bids are " +  bids);
			return bids;
    }
    
    
 // Accept offer for a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/acceptoffer/{id}") //Check URL with team
    public @ResponseBody Book acceptOffer(@RequestBody BookBids bookBids) {
			
			//checkValidBook(title, author, isbn, price);
			
		    
			System.out.println("1. Book Offer Accepted ");
			return  null; //book;
    }

	private boolean checkValidBid(BookBids bookBids) {
		// TODO Auto-generated method stub
		
		if(bookBids.getBidderId()!=null && bookBids.getBookId() != null && bookBids.getBidPrice() > 0.0 )
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}

  }
