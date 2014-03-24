package nealxyc.tinycrawler.search;

import java.util.Date;
import java.util.List;

import twitter4j.Status ;
import twitter4j.URLEntity;

public class Tweet extends WebPage{

	public static String type = "Tweet";
	private String userId ;
	private Date createTime ;
	private Status status; 
	
	
	public String getUserId(){
		return this.userId ;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static Tweet fromStatus(Status status){
		Tweet t = new Tweet();
		t.setStatus(status);
		t.setContent(status.getText());
		t.setCreateTime(status.getCreatedAt());
		t.setUserId(String.valueOf(status.getUser().getId()));
		for(URLEntity entity: status.getURLEntities()){
			t.addOutGoingUrl(entity.getExpandedURL());
		}
		return t ;
	}

}
