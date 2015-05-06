package cmpe275Project.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cmpe275Project.DAO.BookBidsDao;
import cmpe275Project.DAO.BookBidsDaoImpl;
import cmpe275Project.DAO.RentOrBuyDao;
import cmpe275Project.DAO.RentOrBuyDaoImpl;
import cmpe275Project.DAO.TransactionDao;
import cmpe275Project.DAO.TransactionDaoImpl;
import cmpe275Project.Model.BookBids;
import cmpe275Project.Model.RentOrBuy;
import cmpe275Project.Model.Transaction;

public class BiddingController {
	
	Integer student_id = 1;
	BookBidsDao bookBidsDao = new BookBidsDaoImpl();
	RentOrBuyDao rentOrBuyDao = new RentOrBuyDaoImpl();
	TransactionDao transactionDao = new TransactionDaoImpl();
	
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
    public List<BookBids> listBids(@PathVariable(value = "id")Integer id) {
			
			//checkValidBook(title, author, isbn, price);
			List<BookBids> bids = bookBidsDao.listBids(id);
		    
			System.out.println("Bids are " +  bids);
			return bids;
    }
    
    
 // Accept offer for a Book.
    @RequestMapping( method = RequestMethod.GET, value = "/acceptoffer/{id}") //Check URL with team --> bid id will be sent 
    public String acceptOffer(@PathVariable(value = "id") Integer bidId) {
			
			String status = " ";
		    if(this.bidIdExist(bidId))
		    {
		    	BookBids bid = bookBidsDao.getBid(bidId);
		    	RentOrBuy rentOrBuy = bookBidsDao.getRentOrBuyRecord(bid.getBookId());
		    	
		    	if (!(rentOrBuyDao.getBookStatus(rentOrBuy.getBookId()).equalsIgnoreCase("sold") || rentOrBuyDao.getBookStatus(rentOrBuy.getBookId()).equalsIgnoreCase("rented"))) {

		 			// Change status in DB
		 			if ((rentOrBuyDao.changeBookStatus(rentOrBuy.getBookId(), "Sold").equalsIgnoreCase("success"))) //Changes status
		 			{
		 				//Check the value of type
		 				Transaction transaction = new Transaction(rentOrBuy.getBookId(), student_id ,rentOrBuy.getOwnerId(), rentOrBuy.getType(), rentOrBuy.getSellingPrice());
		 				transactionDao.createTransaction(transaction);
		 				
		 				System.out.println("Book with id " + rentOrBuy.getBookId() + " bought");
		 				System.out.println("Transaction id is: " + transaction.getTransactionId());
		 			} else {
		 				System.out.println("Book buying failed, could not update");
		 			}
		 		} else {
		 			System.out.println("Book you are trying to buy has status "
		 					+ rentOrBuyDao.getBookStatus(rentOrBuy.getBookId()));
		 		}
		    	status = "success";
		    }
		    else
		    {
		    	status = "fail";
		    }
			return status; //book;
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
	
	private boolean bidIdExist(Integer bidId)
	{
		if(bookBidsDao.bidIdExist(bidId))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

  }
