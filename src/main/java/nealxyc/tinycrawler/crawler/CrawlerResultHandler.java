package nealxyc.tinycrawler.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

import nealxyc.tinycrawler.search.HtmlPage;
import nealxyc.tinycrawler.search.WebPage;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class CrawlerResultHandler extends WebCrawler {

	private static Logger log = Logger.getLogger(CrawlerResultHandler.class);
	public volatile static List<WebPage> pages = new ArrayList<>();
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
                                                      + "|png|tiff?|mid|mp2|mp3|mp4"
                                                      + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
                                                      + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    /**
     * You should implement this function to specify whether
     * the given url should be crawled or not (based on your
     * crawling logic).
     */
    @Override
    public boolean shouldVisit(WebURL url) {
            String href = url.getURL().toLowerCase();
            return !FILTERS.matcher(href).matches() ;
    }

    /**
     * This function is called when a page is fetched and ready 
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {          
            String url = page.getWebURL().getURL();
//            System.out.println("URL: " + url);

            if (page.getParseData() instanceof HtmlParseData) {
                    HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    HtmlPage hp = new HtmlPage();
                    hp.setURL(url);
                    hp.setContent(htmlParseData.getText());
                    hp.setTitle(htmlParseData.getTitle());
                    hp.addAllOutGoingUrls(Collections2.transform(htmlParseData.getOutgoingUrls(), new Function<WebURL, String>(){

						@Override
						public String apply(WebURL input) {
							return input.getURL();
						}}));
//                    String text = htmlParseData.getText();
//                    String html = htmlParseData.getHtml();
//                    List<WebURL> links = htmlParseData.getOutgoingUrls();
                    
//                    System.out.println("Saved link: " + url);
                    addWebPage(hp);
                    log.info("Saved link: " + url);
            }
    }
    
    public synchronized void addWebPage(WebPage page){
		pages.add(page);
    }

}
