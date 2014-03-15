package nealxyc.dataaccess;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Uses Java reflection to dynamically determine the correct parameters of a method.
 *
 */
public abstract class AbstractParameterBinder {

	protected final HashMap<Object, Object> paramKeyToValueMap;


	public AbstractParameterBinder() {
		paramKeyToValueMap = new HashMap<Object, Object>();
	}


	/**
	 * The first element of array {@code objs} must be the value object of the parameter. You could add supplemental objects to
	 * help generate the binding relationship.
	 *
	 * @param objs
	 * @return
	 */
	public AbstractParameterBinder add(Object... objs) {
		paramKeyToValueMap.put(bindParam(objs), objs[0]);
		return this;
	}



	/**
	 * This method determines how a parameter is bound. For example, in {@link ByClassParameterBinder}: <code><pre>
	 * public Object bindParam(Object... objs) {
	 * 	return objs[0].getClass();
	 * }
	 * </pre>
	 * </code> It binds a parameter value object ({@code objs[0]}) to its class.
	 * The first element of array {@code objs} must be the value object of the parameter. You could add supplemental objects to
	 * help generate the binding relationship.
	 * 
	 * @param objs
	 * @return
	 */
	protected abstract Object bindParam(Object... objs);


	/**
	 * Returns an array of Object containing the parameter values needed by the method.
	 * 
	 * @param m
	 * @return
	 */
	public abstract Object[] lookupParams(Method m);

}
