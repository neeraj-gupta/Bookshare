package cmpe275Project.DAO;

import java.util.List;

import cmpe275Project.Model.BookBids;

public interface BookBidsDao {
	
	public void addBid(BookBids bookBids);

	public List<BookBids> listBids(int id);

}
