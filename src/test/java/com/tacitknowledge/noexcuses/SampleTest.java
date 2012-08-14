package com.tacitknowledge.noexcuses;

import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Test;

/**
 * mshort Date: May 3, 2011 Time: 9:39:57 AM
 */
@SuppressWarnings("unchecked")
public class SampleTest
{
    @Test
    public void testAllMethods() throws Exception
    {
        DummyObject object = new DummyObject("Something", "another", true);
        MethodTester methodTester = new MethodTester(ExceptionHandler.SILENT_ON_EXCEPTION);
        methodTester.performTest(object);
    }

    @Test
    public void testGetterAndSetterConvenienceMethod() throws Exception
    {
        DummyObject object = new DummyObject("Something", "another", true);
        MethodTester methodTester = new MethodTester();
        methodTester.performGettersAndSetters(object);
    }

    @Test
    public void testGetterAndSetterConvenienceMethodWithExclusions() throws Exception
    {
        DummyObject object = new DummyObject("Something", "another", true);
        MethodTester methodTester = new MethodTester();
        methodTester.performGettersAndSetters(object, new String[] { "getSomething" });

    }

    @Test
    public void testAllMethodsExceptAttemptAndThrowAnException() throws Exception
    {
        DummyObject object = new DummyObject("Something", "another", true);
        MethodTester methodTester = new MethodTester("", new String[] { "attempt", "throwAnException" }, null,
                ExceptionHandler.FAIL_ON_EXCEPTION);
        methodTester.performTest(object);
    }

    @Test
    public void testAllMethodsWithGetPrefix() throws Exception
    {
        DummyObject object = new DummyObject("Something", "another", true);
        MethodTester methodTester = new MethodTester("get");
        methodTester.performTest(object);
    }

    @Test
    public void testConstructors() throws Exception
    {
        TestManager.testConstruction(DummyObject.class);
    }

    @Test
    public void testNoExceptionOnPassingNullConstructor() throws Exception
    {
        DummyObjectBuilder dummyObjectBuilder = new DummyObjectBuilder(Collections.EMPTY_MAP);
        assertNull(dummyObjectBuilder.createInstance(null));
    }

    @Test
    public void testCreateObjectWithNoConstructors() throws Exception
    {
        DummyObjectBuilder dummyObjectBuilder = new DummyObjectBuilder(Collections.EMPTY_MAP);
        assertNull(dummyObjectBuilder.createObject(EmptyInterface.class));
    }

    interface EmptyInterface
    {}
}
