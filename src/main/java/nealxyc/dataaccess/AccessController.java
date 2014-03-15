package nealxyc.dataaccess;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

public class AccessController {
	
	public void doAccess(String moduleName, String ap){
//		String reqURI = request.getRequestURI() ;
//		String moduleName = getModuleName(reqURI);
//		String ap = getAccessPoint(reqURI);
		//		String restURI = reqURI.replace(moduleName, replacement)
//		if (StringUtils.isNotEmpty(moduleName)) {
//
//			try {
//				AccessModule ada = new AccessModule.Helper().lookupModule(moduleName);//lookupModule(moduleName);
//				//ada.setPermAccessorBean(pab);
//				if (ada != null) {
//					Method m = ada.lookupMethod(ap);//lookupMethod(ada.getClass(), ap);
//					if (m != null) {
//						ByClassParameterBinder binder = new ByClassParameterBinder();
//						binder.add(new HttpParameterMap(request.getParameterMap()));
//						Object[] params = binder.lookupParams(m);
//						try {
//							Object obj = m.invoke(ada, params);
//							if (obj != null) {
//								response.setStatus(200);
//								response.getWriter().print(obj.toString());
//							}
//						} catch (IllegalArgumentException e) {
//							log.error(e);
//							responseInternalError(response);
//						} catch (InvocationTargetException e) {
//							log.error(e);
//							responseInternalError(response);
//						}
//					} else {
//						responseAPNotFound(response);
//					}
//				} else {
//					responseModuleNotFound(response);
//				}
//			} catch (ClassNotFoundException e) {
//				log.debug("Module Not Found: " + "\n", e);
//				responseModuleNotFound(response);
//			} catch (InstantiationException e) {
//				log.error(e);
//				responseInternalError(response);
//			} catch (IllegalAccessException e) {
//				log.error(e);
//				responseInternalError(response);
//			}
//		}
	}
}
