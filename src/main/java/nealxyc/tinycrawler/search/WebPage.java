package nealxyc.tinycrawler.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class WebPage {

	private String url ;
	private String content ;
	private List<String> outGoingUrls ;
	
	public static String type =	"WebPage" ;

	public String getURL(){
		return this.url ;
	}
	public void setURL(String url){
		this.url = url;
	}
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content ;
	}
	
	public List<String> getOutGoingUrls() {
		return outGoingUrls;
	}
	
	public void setOutGoingUrls(List<String> outGoingUrls) {
		this.outGoingUrls = outGoingUrls;
	}
	
	public void addOutGoingUrl(String url){
		if(this.outGoingUrls == null){
			this.outGoingUrls = new ArrayList<>();
		}
		
		this.outGoingUrls.add(url);
	}
	
	public void addAllOutGoingUrls(Collection<String> urls){
		if(this.outGoingUrls == null){
			this.outGoingUrls = new ArrayList<>();
		}
		this.outGoingUrls.addAll(outGoingUrls);
	}

}
