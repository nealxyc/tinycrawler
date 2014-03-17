package nealxyc.dataaccess.servlet;

import org.apache.commons.lang.StringUtils;

import nealxyc.dataaccess.AccessPointName;

public final class NameInspector {
	
	/**
	 * example: /MyWebApp
	 * 			/MyWebApp2/   (equivalents to /MyWebApp2, trailing '/' does not matter)
	 * 			MyWebApp3	  (equivalents to /MyWebApp3, Initial '/' is always assumed)
	 * 		
	 */
	private String baseUri ;
	public NameInspector(String baseUri){
		
		this.baseUri = Helper.dropLastSlashIfNeeded(Helper.prependSlashIfNeeded(baseUri));
	}
	
	protected String getModuleName(String reqURI) {
		
		if (!StringUtils.isEmpty(reqURI) && reqURI.startsWith(baseUri)) {
			int indexModuleStart = reqURI.indexOf(baseUri) + baseUri.length();
			int indexModuleEnd = reqURI.indexOf('/', indexModuleStart + 1);
			return reqURI.substring(indexModuleStart + 1, indexModuleEnd);
		}
		return "";
	}

	
	

	protected String getAccessPoint(String reqURI) {
		String moduleName = getModuleName(reqURI);
		if (!StringUtils.isEmpty(moduleName)) {
			String pre = baseUri + moduleName;
			int indexAPStart = reqURI.indexOf(pre) + pre.length();
			return reqURI.substring(indexAPStart);
		}
		return "";
	}
	
	public AccessPointName inspect(String reqURI){
		reqURI = Helper.prependSlashIfNeeded(reqURI);
		String moduleName = getModuleName(reqURI);
		if (!StringUtils.isEmpty(moduleName)) {
			String pre = baseUri + "/" + moduleName;
			int indexAPStart = reqURI.indexOf(pre) + pre.length() + 1;
			return new AccessPointName(moduleName, reqURI.substring(indexAPStart));
		}else{
			return new AccessPointName("", "");
		}
	}
	
	public static class Helper{
		public static String dropLastSlashIfNeeded(String s){
			if(!StringUtils.isEmpty(s) && s.endsWith("/")){
				return s.substring(0, s.length() - 1);
			}
			return s ;
		}
		
		public static String prependSlashIfNeeded(String s){
			if(!StringUtils.isEmpty(s) && !s.startsWith("/")){
				return "/" + s;
			}
			return s ;
		}
	}

}
