/**
 * Copyright (c) 2014 Neal Xiong. All Rights Reserved.
 */
package nealxyc.dataaccess;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nxiong
 * @created May 23, 2013
 * @vcsid $Id: AccessPoint.java 238873 2013-08-02 20:33:36Z nxiong $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.FIELD })
public @interface AccessPoint {
	public String value();
}
