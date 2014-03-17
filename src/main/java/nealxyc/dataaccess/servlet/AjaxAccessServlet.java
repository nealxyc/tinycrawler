package nealxyc.dataaccess.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nealxyc.dataaccess.AccessModule;
import nealxyc.dataaccess.AccessPoint;
import nealxyc.dataaccess.AccessPointName;
import nealxyc.dataaccess.ByClassParameterBinder;
import nealxyc.dataaccess.AccessModule.Helper;
import nealxyc.dataaccess.HttpParameterMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class AjaxAccessServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AjaxAccessServlet.class);
	private String baseUri = "/ajax";
	private NameInspector ni = null;

	//	private HashMap<String, Object> paramNameToValueMap;

	@Override
	public void init(ServletConfig conf) {
		String uri = conf.getInitParameter("baseURI") ;
		if(uri != null){
			setBaseURI(uri);
		}
		this.ni = new NameInspector(baseUri);
	}

	/** Process the HTTP Get request */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession sess = request.getSession(false);

		String reqURI = request.getRequestURI() ;
		AccessPointName apn = this.ni.inspect(reqURI);
		log.debug("Request url: " + reqURI);
		if (StringUtils.isNotEmpty(apn.moduleName)) {
			log.debug("Module Name: " + apn.moduleName);
			log.debug("Access Point: " +apn.accessPoint);
			try {
				AccessModule ada = AccessModule.Helper.lookupModule(apn.moduleName);//lookupModule(moduleName);
				//ada.setPermAccessorBean(pab);
				if (ada != null) {
					Method m = AccessModule.Helper.lookupAccessPoint(ada, apn.accessPoint) ;//lookupMethod(ada.getClass(), ap);
					if (m != null) {
						ByClassParameterBinder binder = new ByClassParameterBinder();
						binder.add(request).add(response);
						binder.add(new HttpParameterMap(request.getParameterMap()));
						Object[] params = binder.lookupParams(m);
						try {
							Object obj = m.invoke(ada, params);
							if (obj != null) {
								response.setStatus(200);
								response.getWriter().print(obj.toString());
							}
						} catch (IllegalArgumentException e) {
							log.error(e);
							responseInternalError(response);
						} catch (InvocationTargetException e) {
							log.error(e);
							responseInternalError(response);
						}
					} else {
						responseAPNotFound(response);
					}
				} else {
					responseModuleNotFound(response);
				}
			} catch (ClassNotFoundException e) {
				log.debug("Module Not Found: " + "\n", e);
				responseModuleNotFound(response);
			} catch (InstantiationException e) {
				log.error(e);
				responseInternalError(response);
			} catch (IllegalAccessException e) {
				log.error(e);
				responseInternalError(response);
			}
		}
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}


	/** Clean up resources */
	@Override
	public void destroy() {

	}


	protected void setBaseURI(String baseURI) {
		baseUri = baseURI;
	}




	protected boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}


	protected void responseModuleNotFound(HttpServletResponse response) throws IOException {
		responseMsg(response, 404, "Module Not Found");
	}


	protected void responseAPNotFound(HttpServletResponse response) throws IOException {
		responseMsg(response, 404, "Access Point Not Found");
	}

	protected void responseInternalError(HttpServletResponse response) throws IOException {
		responseMsg(response, 500, "Internal Server Error");
	}


	protected void responseMsg(HttpServletResponse response, int status, String msg) throws IOException {
		response.setStatus(status);
		response.getWriter().print("//" + status + " " + msg);
	}
	
	/**
	 * Looks up the class with name {@code moduleName}. It needs to be a sub class of {@link AccessModule}. If found returns an
	 * instance of that class by calling its default constructor.
	 *
	 * @param moduleName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	protected AccessModule lookupModule(String moduleName) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		if (!isEmpty(moduleName)) {
			//Checks if the 'moduleName' corresponds to a subclass of AjaxDataAccess
			Class<?> cls = Class.forName(moduleName);
			if (AccessModule.class.isAssignableFrom(cls)) {
				//Creates an instance of that class.
				return (AccessModule) cls.newInstance();
			}

		}
		return null;
	}
	
	/**
	 * Looks up the method of class {@code clazz} that has an Annotation of type {@link AccessPoint} with value equals to String
	 * {@code ap}.
	 *
	 * @param clazz
	 * @param ap
	 * @return
	 */
	protected Method lookupMethod(Class<?> clazz, String ap) {
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			AccessPoint apoint = m.getAnnotation(AccessPoint.class);
			if (apoint != null && apoint.value().equals(ap)) {
				return m;
			}
		}
		return null;
	}

	

}
