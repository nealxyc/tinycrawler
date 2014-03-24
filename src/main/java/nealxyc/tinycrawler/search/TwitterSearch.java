package nealxyc.tinycrawler.search;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import org.apache.log4j.Logger;

public class TwitterSearch {
	private static Logger log = Logger.getLogger(TwitterSearch.class);
	private static String language = "en" ;//default language
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Twitter twitter = TwitterFactory.getSingleton();
	    Query query = new Query("mh370");
	    QueryResult result;
		try {
			result = twitter.search(query);
			for (Status status : result.getTweets()) {
		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		    }
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public static List<Tweet> search(String q){
		List<Tweet> tweets = new ArrayList<>();
		Twitter twitter = TwitterFactory.getSingleton();
	    Query query = new Query(q);
	    query.setLang(language);
	    QueryResult result;
		try {
			result = twitter.search(query);
			log.debug("Twitter search completed. Tweets retrieved: "+ result.getTweets().size());
			for (Status status : result.getTweets()) {
		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		        tweets.add(Tweet.fromStatus(status));
		    }
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return tweets;
	}

}
