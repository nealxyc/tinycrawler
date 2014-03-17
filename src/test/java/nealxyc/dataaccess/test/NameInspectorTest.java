package nealxyc.dataaccess.test;

import static org.junit.Assert.*;
import nealxyc.dataaccess.AccessPointName;
import nealxyc.dataaccess.servlet.NameInspector;

import org.junit.Test;

public class NameInspectorTest {

	@Test
	public void testInspect(){
		NameInspector ni = new NameInspector("/MyWebApp");
		AccessPointName apn = ni.inspect("/MyWebApp/com.MyClass/myMethod");
		assertEquals("", "com.MyClass", apn.moduleName);
		assertEquals("", "myMethod", apn.accessPoint);
	
		apn = ni.inspect("/MyWebApp2/com.MyClass/myMethod");
		assertEquals("", "", apn.moduleName);
		assertEquals("", "", apn.accessPoint);
		
		NameInspector ni2 = new NameInspector("/MyWebApp/");
		apn = ni2.inspect("/MyWebApp/com.package.MyClass/myMethod");
		assertEquals("", "com.package.MyClass", apn.moduleName);
		assertEquals("", "myMethod", apn.accessPoint);
	
		apn = ni2.inspect("/MyWebApp2/com.MyClass/myMethod");
		assertEquals("", "", apn.moduleName);
		assertEquals("", "", apn.accessPoint);
		
		NameInspector ni3 = new NameInspector("MyWebApp");
		apn = ni3.inspect("/MyWebApp/com.package.MyClass/myMethod");
		assertEquals("", "com.package.MyClass", apn.moduleName);
		assertEquals("", "myMethod", apn.accessPoint);
	
		apn = ni3.inspect("/MyWebApp2/com.MyClass/myMethod");
		assertEquals("", "", apn.moduleName);
		assertEquals("", "", apn.accessPoint);
	}
	
	
}
