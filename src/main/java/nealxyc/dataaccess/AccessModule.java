/**
 * Copyright (c) 2014 Neal Xiong. All Rights Reserved.
 */
package nealxyc.dataaccess;

import java.lang.reflect.Method;

import nealxyc.dataaccess.servlet.NameInspector;

import org.apache.commons.lang.StringUtils;

/**
 * @author Neal Xiong (nxiong)
 * @created May 23, 2013
 * @vcsid $Id: AccessModule.java 238873 2013-08-02 20:33:36Z nxiong $
 */
public abstract class AccessModule {
	
	public AccessModule(){
		
	}
	//
	//	HashMap<String, Class<AccessModule>> registrationMap;
//	public Method lookupMethod(String ap) {
//		return Helper.lookupAccessPoint(this, ap);
//	}

	public static class Helper {

		public static AccessModule lookupModule(String moduleName) throws ClassNotFoundException, InstantiationException,
				IllegalAccessException {
			if (!StringUtils.isEmpty(moduleName)) {
				//Checks if the 'moduleName' corresponds to a subclass of AccessModule
				Class<?> cls = Class.forName(moduleName);
				if (AccessModule.class.isAssignableFrom(cls)) {
					//Creates an instance of that class.
					return (AccessModule) cls.newInstance();
				}

			}
			return null;
		}

		public static Method lookupAccessPoint(AccessModule am, String ap) {
			Method[] methods = am.getClass().getMethods();
			for (Method m : methods) {
				AccessPoint apoint = m.getAnnotation(AccessPoint.class);
				if (apoint != null && isAPMatch(apoint.value(), ap)) {
					return m;
				}
			}
			return null;
		}
		
		public static boolean isAPMatch(String annotated, String ap){
			annotated = NameInspector.Helper.dropLastSlashIfNeeded(NameInspector.Helper.prependSlashIfNeeded(annotated));
			ap = NameInspector.Helper.dropLastSlashIfNeeded(NameInspector.Helper.prependSlashIfNeeded(ap));
			
			return StringUtils.equals(annotated, ap);
		}

	}

}

