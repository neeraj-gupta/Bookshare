package cmpe275Project.Controller;

import java.security.InvalidParameterException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cmpe275Project.DAO.FeedbackDao;
import cmpe275Project.DAO.FeedbackDaoImpl;
import cmpe275Project.Model.Feedback;


@RestController
@RequestMapping("/")
public class FeedbackController {
	
	Integer student_id = 1;
	private FeedbackDao feedbackDao = new FeedbackDaoImpl();
	
	// Give Feedback for a Book.
		@RequestMapping(method = RequestMethod.POST, value = "/givefeedback")
		public @ResponseBody void feedback(@RequestBody Feedback feedback) {

			this.checkValidFeedback(feedback);
			Feedback feedbackObj = new Feedback(student_id, feedback.getSellerId(),
					feedback.getRating(), feedback.getFeedbackDesc());
			if (feedbackDao.addFeedback(feedbackObj).equalsIgnoreCase("success")) {
				System.out.println("Posted Feed Back successfully");
			} else {
				System.out.println("Feedback couldnt be posted");
			}

		}
		
		private void checkValidFeedback(Feedback feedback) {
			if (feedback.getSellerId() == null || feedback.getRating() == null) {
				throw new InvalidParameterException();
			}
		}
}
