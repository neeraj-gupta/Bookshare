package cmpe275Project.Model;

import java.sql.Date;

public class Feedback {
	
	private static Integer counter;
	private Integer feedbackId;
	private Integer providerId;
	private Integer sellerId;
	private Integer rating;
	private String feedbackDesc;
	private Date created_at;
	
	public Feedback(Integer providerid, Integer sellerid, Integer rating, String feedbackdesc, Date created_at){
		super();
		setFeedbackId(counter++);
		this.setProviderId(providerid);
		this.sellerId = sellerid;
		this.rating = rating;
		this.feedbackDesc = feedbackdesc;
		this.created_at = created_at;
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
