package nealxyc.tinycrawler.web;

import twitter4j.JSONObject;
import nealxyc.dataaccess.AccessModule;
import nealxyc.dataaccess.AccessPoint;

public class SearchData extends AccessModule {

	@AccessPoint("/search")
	public JSONObject search(){
		return null ;
	}
}
