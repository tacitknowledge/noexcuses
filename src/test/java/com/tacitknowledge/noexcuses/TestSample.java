package com.tacitknowledge.noexcuses;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: mshort
 * Date: May 3, 2011
 * Time: 9:39:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSample extends TestCase {


    public void testAllMethods() throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester(ExceptionHandler.SILENT_ON_EXCEPTION);
        methodTester.performTest(object);
    }

    public void testGetterAndSetterConvenienceMethod () throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester();
        methodTester.performGettersAndSetters(object);
    }

    public void testGetterAndSetterConvenienceMethodWithExclusions() throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester();
        methodTester.performGettersAndSetters(object,new String[]{"getSomething"});

    }

    public void testAllMethodsExceptAttemptAndThrowAnException() throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester("",new String[] {"attempt","throwAnException"},null,
                ExceptionHandler.FAIL_ON_EXCEPTION);
        methodTester.performTest(object);
    }
    public void testAllMethodsWithGetPrefix() throws Exception {
        DummyObject object = new DummyObject("Something","another",true);
        MethodTester methodTester = new MethodTester("get");
        methodTester.performTest(object);
    }
    public void testConstructors() throws Exception {
        TestManager.testConstruction(DummyObject.class);
    }

}
