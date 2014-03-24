package nealxyc.tinycrawler.crawler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import nealxyc.tinycrawler.search.WebPage;

public class Controller {
	
	private static Logger log = Logger.getLogger(Controller.class);
	static String crawlStorageFolder = "temp/crawl/root";
	
	CrawlController controller ;
	int numberOfCrawlers = 1;
	
	public Controller(int depth) throws Exception{
		String crawlStorageFolder = "temp/crawl/root";
        CrawlConfig config = new CrawlConfig();
        config.setFollowRedirects(true);
        config.setMaxDepthOfCrawling(depth);
        config.setCrawlStorageFolder(crawlStorageFolder);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        controller = new CrawlController(config, pageFetcher, robotstxtServer);
	}
    public static void main(String[] args) throws Exception {
            String crawlStorageFolder = "temp/crawl/root";
            int numberOfCrawlers = 1;

            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(crawlStorageFolder);

            /*
             * Instantiate the controller for this crawl.
             */
            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            /*
             * For each crawl, you need to add some seed urls. These are the first
             * URLs that are fetched and then the crawler starts following links
             * which are found in these pages
             */
            controller.addSeed("http://nutch.apache.org/");

            /*
             * Start the crawl. This is a blocking operation, meaning that your code
             * will reach the line after this only when crawling is finished.
             */
            controller.start(MyCrawler.class, numberOfCrawlers); 
            controller.getCrawlersLocalData();
    }
    
    public void addSeedUrl(String seedUrl) throws Exception{
        controller.addSeed(seedUrl);
    }
    
    public List<WebPage> doCrawl(){
    	controller.start(CrawlerResultHandler.class, numberOfCrawlers); 
        return CrawlerResultHandler.pages ;
    }
    
}
