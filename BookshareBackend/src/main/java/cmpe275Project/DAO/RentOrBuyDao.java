package cmpe275Project.DAO;

public interface RentOrBuyDao {
	
	public String getBookStatus(int bookId);

	public String changeBookStatus(int id, String string);

}
