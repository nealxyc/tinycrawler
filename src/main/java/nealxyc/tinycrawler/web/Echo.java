package nealxyc.tinycrawler.web;

import nealxyc.dataaccess.AccessModule;
import nealxyc.dataaccess.AccessPoint;
import nealxyc.dataaccess.HttpParameterMap;

public class Echo extends AccessModule{

	@AccessPoint("/echo")
	public String echo(HttpParameterMap map){
		return "Hello" ;
	}
	
}
