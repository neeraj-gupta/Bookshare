package cmpe275Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import cmpe275Project.Controller.DateParser;

@Document(collection = "feedbacks")
public class Feedback {
	private static Integer counter;
	@Id
	private Integer feedbackId;
	private Integer providerId;
	private Integer sellerId;
	private Integer rating;
	private String feedbackDesc;
	private String created_at;
	
	public Feedback(Integer providerId, Integer sellerId, Integer rating, String feedbackDesc){
		super();
		feedbackId = counter++;
		this.providerId = providerId;
		this.sellerId = sellerId;
		this.rating = rating;
		this.feedbackDesc = feedbackDesc;
		this.setCreated_at();
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getFeedbackDesc() {
		return feedbackDesc;
	}

	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at() {
		DateParser dparser = new DateParser();
		this.created_at = dparser.getDate();
	}
}
