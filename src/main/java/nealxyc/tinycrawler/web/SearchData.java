package nealxyc.tinycrawler.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import twitter4j.JSONObject;
import nealxyc.dataaccess.AccessModule;
import nealxyc.dataaccess.AccessPoint;
import nealxyc.dataaccess.HttpParameterMap;
import nealxyc.tinycrawler.crawler.Controller;
import nealxyc.tinycrawler.search.Tweet;
import nealxyc.tinycrawler.search.TwitterSearch;
import nealxyc.tinycrawler.search.WebPage;

public class SearchData extends AccessModule {

	private static Logger log = Logger.getLogger(SearchData.class);
	
	@AccessPoint("/search")
	public String search(HttpParameterMap map) throws Exception{
		String q = map.get("q");
		int depth = map.getInt("depth", 2);
		Gson gson = new Gson();
		List<Tweet> tweets = TwitterSearch.search(q);
		List<WebPage> pages = new ArrayList<>();
		Controller ctrl = new Controller(depth);
		for(Tweet tweet: tweets){
			List<String> urls = tweet.getOutGoingUrls();
			if(urls != null){
				for(String url: urls){
					log.debug("Starting to crawl: " + url + "\nDepth: " + depth);
					try {
//						pages.addAll(Controller.crawl(url, depth));
						ctrl.addSeedUrl(url);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			pages.add(tweet);
		}
		pages.addAll(ctrl.doCrawl());
		log.debug("Crawling are done.");
		return gson.toJson(pages);
	}
}
