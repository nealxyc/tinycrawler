package nealxyc.tinycrawler.search;

public interface WebPage {

	public static String type =	WebPage.class.getSimpleName() ;
//	private static Logger log = Logger.getLogger(WebPage.class);
//	private Page crawlerPage ;
//	
//	public WebPage(Page page) {
//		this.crawlerPage = page ;
//	}
//	
	public String getURL();
	public String getContent();

}
