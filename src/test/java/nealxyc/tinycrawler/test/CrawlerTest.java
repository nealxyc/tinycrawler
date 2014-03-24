package nealxyc.tinycrawler.test;

import java.util.List;

import nealxyc.tinycrawler.crawler.Controller;
import nealxyc.tinycrawler.search.WebPage;

import org.junit.Assert;
import org.junit.Test;

public class CrawlerTest {

	@Test
	public void testController() throws Exception{
		try {
			Controller ctrl = new Controller(0);
			
			ctrl.addSeedUrl("http://www.google.com");
			ctrl.addSeedUrl("http://www.usfca.edu");
			ctrl.addSeedUrl("https://www.youtube.com");
			ctrl.addSeedUrl("https://www.yahoo.com");
			ctrl.addSeedUrl("http://www.amazon.com/");
			ctrl.addSeedUrl("http://www.nytimes.com");
			List<WebPage> pages = ctrl.doCrawl();
			Assert.assertEquals(6, pages.size());
			
		} catch (Exception e) {
			throw e;
		}
		
	}
}
