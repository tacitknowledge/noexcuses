package com.tacitknowledge.noexcuses;

import org.junit.Test;


/**
 * Created by IntelliJ IDEA.
 * User: mshort
 * Date: May 3, 2011
 * Time: 9:39:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class SampleTest {
	@Test
    public void testAllMethods() throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester(ExceptionHandler.SILENT_ON_EXCEPTION);
        methodTester.performTest(object);
    }

	@Test
    public void testGetterAndSetterConvenienceMethod () throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester();
        methodTester.performGettersAndSetters(object);
    }

	@Test
    public void testGetterAndSetterConvenienceMethodWithExclusions() throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester();
        methodTester.performGettersAndSetters(object,new String[]{"getSomething"});

    }

	@Test
    public void testAllMethodsExceptAttemptAndThrowAnException() throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester("",new String[] {"attempt","throwAnException"},null,
                ExceptionHandler.FAIL_ON_EXCEPTION);
        methodTester.performTest(object);
    }
	
	@Test
    public void testAllMethodsWithGetPrefix() throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester("get");
        methodTester.performTest(object);
    }
	@Test
    public void testConstructors() throws Exception {
        TestManager.testConstruction(DummyObject.class);
    }

}
