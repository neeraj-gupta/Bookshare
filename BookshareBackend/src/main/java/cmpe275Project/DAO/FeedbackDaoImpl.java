package cmpe275Project.DAO;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import cmpe275Project.Model.Feedback;
import cmpe275Project.config.SpringMongoConfig;

public class FeedbackDaoImpl implements FeedbackDao {
	
	private static MongoOperations mongoOps;
	
	public FeedbackDaoImpl() {
		if(mongoOps == null){
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	    	mongoOps = (MongoOperations)context.getBean("mongoTemplate");
		}
	}

	@Override
	public String addFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		String status = " ";
		
		if(feedback.getFeedbackId()!= null && feedback.getSellerId()!= null && feedback.getProviderId() != null)
		{
			System.out.println("mongoOps " + mongoOps);
			mongoOps.insert(feedback);
			status = "success";
		}
		else
		{
			status = "fail";
		}
		
		return status;
		
	}

}
