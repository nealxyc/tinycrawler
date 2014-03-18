package nealxyc.dataaccess.servlet;

import javax.servlet.ServletConfig;

public class ServletUtils {

	/**
	 * Try all the names in paramNames one by one to get the configured parameter value. The first one gets a non-null value will be returned.
	 * This allows user to have multiple alias to the parameter name.
	 * @param paramName
	 * @return
	 */
	public static String getInitParameter(ServletConfig conf, String...paramNames){
		String value = null ;
		for(String paramName: paramNames){
			value = conf.getInitParameter(paramName) ;
			if(value != null){
				return value ;
			}
		}
		return value ;
	}
}
