package cmpe275Project.MyExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Exceptions extends Exception {
	private static final long serialVersionUID = 1L;

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Duplicate Student Email")
	public static class DuplicateStudentEmailException extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DuplicateStudentEmailException(){}
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Book Not Found")
	public static class BookNotFoundException extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BookNotFoundException(){}
	}
}
