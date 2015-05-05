package cmpe275Project.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cmpe275Project.DAO.RentOrBuyDao;
import cmpe275Project.DAO.RentOrBuyDaoImpl;

@RestController
@RequestMapping("/")
public class RentOrBuyController {
	
	private RentOrBuyDao rentOrBuyDao = new RentOrBuyDaoImpl();
	
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


}
