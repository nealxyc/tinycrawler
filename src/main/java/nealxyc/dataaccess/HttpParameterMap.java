package nealxyc.dataaccess;

import java.util.Map;

/**
 */
public class HttpParameterMap {

	private final Map<String, String[]> map;


	public HttpParameterMap(Map<String, String[]> map) {
		this.map = map;
	}


	public String get(String key) {
		return getValue(key);
	}


	protected String getValue(String key) {
		String[] values = map.get(key);
		if (values != null && values.length > 0) {
			return values[0];
		}

		return null;

	}
	
	public String[] getValues(String key){
		String[] values = map.get(key);
		return values ;
	}
	
	public int getInt(String key, int defaultValue){
		try{
			String val = get(key);
			if(val == null){
				return defaultValue ;
			}else{
				return Integer.parseInt(val);
			}
		}catch(Exception e){
			return defaultValue ;
		}
	}
	
	public boolean getBoolean(String key, boolean defaultValue){
		try{
			return Boolean.parseBoolean(get(key));
		}catch(Exception e){
			return defaultValue ;
		}
	}
}
