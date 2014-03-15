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
}
