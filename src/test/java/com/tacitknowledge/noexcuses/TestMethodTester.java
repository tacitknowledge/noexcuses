package com.tacitknowledge.noexcuses;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *  Originally created: Oct 31, 2006
 */
public class TestMethodTester extends TestCase {

    public static final String THROW_AN_EXCEPTION = "throwAnException";

    public void testSimpleMethod() throws IllegalAccessException, InvocationTargetException {
        DummyObject ob = new DummyObject("one", "two", true);
        MethodTester tester = new MethodTester("get");
        tester.performTest(ob);

        //getClass is in there
        assertEquals(2, tester.getTestedMethods().size());
    }

    public void testAntoherSimpleMethod() throws IllegalAccessException, InvocationTargetException {
        DummyObject ob = new DummyObject("one", "two", true);
        MethodTester tester = new MethodTester("is");
        tester.performTest(ob);

        assertEquals(1, tester.getTestedMethods().size());
    }

    public void testSilentSimpleMethod() throws IllegalAccessException, InvocationTargetException {
        DummyObject ob = new DummyObject("one", "two", true);
        MethodTester tester = new MethodTester("is");
        tester.performSilentTest(ob);

        assertEquals(1, tester.getTestedMethods().size());
    }

    public void testAllMethod() throws IllegalAccessException, InvocationTargetException {
        DummyObject ob = new DummyObject("one", "two", true);
        MethodTester tester = new MethodTester("",new String[]{"NEVER"},null,ExceptionHandler.SILENT_ON_EXCEPTION);
        tester.performSilentTest(ob);

        assertEquals(4, tester.getTestedMethods().size());
        //tester.printTestedMethods();
    }

    public void testManager(){
        TestManager.testConstruction(DummyObject.class);
        MethodTester tester = TestManager.testMethods(new DummyObject("one", "two", true));
        //tester.printTestedMethods();
    }

    public void testFailOnException() throws InvocationTargetException, IllegalAccessException {
        DummyObject ob = new DummyObject("one", "two", true);
        MethodTester tester = new MethodTester(THROW_AN_EXCEPTION,new String[]{"NEVER"},null,ExceptionHandler.FAIL_ON_EXCEPTION);

        //check deprecated method still passes
        tester.performSilentTest(ob);

        tester = new MethodTester(THROW_AN_EXCEPTION,new String[]{"NEVER"},null,ExceptionHandler.FAIL_ON_EXCEPTION);
        try {
            tester.performTest(ob);
            fail("should have failed");
        } catch (RuntimeException e) {
          assertEquals(MethodTesterException.DEFAULT_PREFIX + THROW_AN_EXCEPTION
                  + MethodTesterException.DEFAULT_POSTFIX,e.getMessage());
        }



    }

    public void testSwallowException() throws InvocationTargetException, IllegalAccessException {
        DummyObject ob = new DummyObject("one", "two", true);
        MethodTester tester = new MethodTester(THROW_AN_EXCEPTION,new String[]{"NEVER"},null,ExceptionHandler.SILENT_ON_EXCEPTION);
        tester.performSilentTest(ob);

        tester = new MethodTester(THROW_AN_EXCEPTION,new String[]{"NEVER"},null,ExceptionHandler.PRINT_ON_EXCEPTION);
        tester.performTest(ob);

    }
    public void testGetterAndSetterConvenience() {
        DummyObject ob = new DummyObject("one", "two", true);
        MethodTester tester = new MethodTester();
        tester.performGettersAndSetters(ob);
        Collection<String> testedMethods = tester.getTestedMethods();
        assertTrue(testedMethods.contains("setSomething"));
        assertTrue(testedMethods.contains("getSomething"));
        assertTrue(testedMethods.contains("getAnother"));

        assertEquals(3,testedMethods.size());

    }

    public void testCreatingDummy(){
        Map myInstances = new HashMap();
        myInstances.put(long.class, 2);
        myInstances.put(int.class, 2);
        myInstances.put(String.class, "2");
        myInstances.put(float.class, 2);
        myInstances.put(double.class, 2);
        myInstances.put(short.class, 2);
        myInstances.put(byte.class, 0);
        myInstances.put(boolean.class, false);

        Collection instances = TestManager.testConstruction(DependObject.class, myInstances);

        assertEquals(2, instances.size());
        boolean oneWasNull = false;
        for (Object o : instances) {
            assertTrue(o instanceof DependObject);
            DependObject dependObject = (DependObject)o;
            assertEquals("2", dependObject.getMyTestObject().getAnother());

            if(dependObject.getType() == null){
               oneWasNull = true;
            }
            else{
               assertEquals(MyEnum.Whatever, dependObject.getType());
            }
        }

       assertTrue("One of the objects did not have a null type", oneWasNull);
    }
}
