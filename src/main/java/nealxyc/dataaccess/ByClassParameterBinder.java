package nealxyc.dataaccess;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Uses class name to dynamically determine the correct parameters of a method.
 *
 */
public class ByClassParameterBinder extends AbstractParameterBinder {

	/**
	 * Uses class name as the binding.
	 *
	 * @param objs
	 * @return
	 * @see AbstractParameterBinder#bindParam(java.lang.Object[])
	 */
	@Override
	public Object bindParam(Object... objs) {

		return objs[0].getClass();
	}


	/**
	 * @param m
	 * @return
	 * @see AbstractParameterBinder#lookupParams(java.lang.reflect.Method)
	 */
	@Override
	public Object[] lookupParams(Method m) {
		Class<?>[] clss = m.getParameterTypes();
		Object[] params = new Object[clss.length];
		for (int i = 0; i < params.length; i++) {
			params[i] = get(clss[i]);
		}

		return params;
	}


	protected Object get(Object cls) {
		Set<Object> clss = paramKeyToValueMap.keySet();
		Class<?> topClass = (Class<?>) cls;
		for (Object obj : clss) {
			Class<?> c = (Class<?>) obj;
			if (topClass.isAssignableFrom(c)) {
				return paramKeyToValueMap.get(c);
			}
		}
		return null;
	}

}
