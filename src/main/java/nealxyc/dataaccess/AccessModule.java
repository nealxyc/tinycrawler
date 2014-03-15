/**
 * Copyright (c) 2014 Neal Xiong. All Rights Reserved.
 */
package nealxyc.dataaccess;

import java.lang.reflect.Method;

/**
 * @author Neal Xiong (nxiong)
 * @created May 23, 2013
 * @vcsid $Id: AccessModule.java 238873 2013-08-02 20:33:36Z nxiong $
 */
public abstract class AccessModule {
	//
	//	HashMap<String, Class<AccessModule>> registrationMap;
	public Method lookupMethod(String ap) {
		Method[] methods = this.getClass().getMethods();
		for (Method m : methods) {
			AccessPoint apoint = m.getAnnotation(AccessPoint.class);
			if (apoint != null && apoint.value().equals(ap)) {
				return m;
			}
		}
		return null;
	}

	public static class Helper {

		public AccessModule lookupModule(String moduleName) throws ClassNotFoundException, InstantiationException,
				IllegalAccessException {
			if (!isEmpty(moduleName)) {
				//Checks if the 'moduleName' corresponds to a subclass of AccessModule
				Class<?> cls = Class.forName(moduleName);
				if (AccessModule.class.isAssignableFrom(cls)) {
					//Creates an instance of that class.
					return (AccessModule) cls.newInstance();
				}

			}
			return null;
		}

		public Method lookupAccessPoint(AccessModule am, String ap) {
			return am.lookupMethod(ap);
		}

		protected boolean isEmpty(String str) {
			return str == null || str.equals("");
		}
	}

}

