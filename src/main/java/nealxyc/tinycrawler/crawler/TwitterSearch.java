package nealxyc.tinycrawler.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterSearch {

	private String language = "en" ;//default language
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
	
//	public Page[] search(String q){
//		
//		String crawlStorageFolder = "temp/crawl/root";
//        int numberOfCrawlers = 1;
//
//        CrawlConfig config = new CrawlConfig();
//        config.setCrawlStorageFolder(crawlStorageFolder);
//
//        /*
//         * Instantiate the controller for this crawl.
//         */
//        PageFetcher pageFetcher = new PageFetcher(config);
//        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
//        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
//        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//
//        /*
//         * For each crawl, you need to add some seed urls. These are the first
//         * URLs that are fetched and then the crawler starts following links
//         * which are found in these pages
//         */
//        controller.addSeed("http://nutch.apache.org/");
//
//        /*
//         * Start the crawl. This is a blocking operation, meaning that your code
//         * will reach the line after this only when crawling is finished.
//         */
//        controller.start(MyCrawler.class, numberOfCrawlers);  
//        
//		Twitter twitter = TwitterFactory.getSingleton();
//	    Query query = new Query(q);
//	    QueryResult result;
//		try {
//			result = twitter.search(query);
//			for (Status status : result.getTweets()) {
//				
//		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
//		    }
//		} catch (TwitterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
