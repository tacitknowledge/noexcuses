package com.tacitknowledge.noexcuses;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Simplify some testing of methods so that Emma will pass
 * <p/>
 * IMPORTANT!!! This ONLY exists because as of yet, Emma doesn't pay attention to annotations (allowing us to codify
 * which methods are safely ignored by the test framework).  Once these are in place, this and it's "tests" should be
 * removed.
 * <p/>
 *  Originally created: Oct 31, 2006
 */
public class MethodTester implements BeanTester {
    /**
     * prefix for methods to be included in tester
     */
    private String methodSig;
    /**
     * array of method prefixes to exclude
     */
    private String[] exclusionSig;
    private Collection<String> testedMethods;
    private ParamBuilder paramBuilder;
    /**
     * Strategy for handling exceptions when thrown
     * defaults to FAIL_ON_EXCEPTION
     * @see ExceptionHandler
     */
    private ExceptionHandler exceptionHandler = ExceptionHandler.FAIL_ON_EXCEPTION;


	/**
	 * Test everything...
	 */
	public MethodTester() {
		this("", new String[]{}, new StubBuilder(), ExceptionHandler.FAIL_ON_EXCEPTION);
	}

	public MethodTester(String methodSig) {
        this(methodSig,new String[]{},null,ExceptionHandler.FAIL_ON_EXCEPTION);
    }

    public MethodTester(ExceptionHandler exceptionHandler) {
        this("", new String[]{}, new StubBuilder(), exceptionHandler);
    }


    public MethodTester(String methodSig,ExceptionHandler exceptionHandler) {
        this(methodSig, new String[]{}, new StubBuilder(), exceptionHandler);
    }
    public MethodTester(String methodSig, String[] exclusionSig, ExceptionHandler exceptionHandler) {
        this(methodSig, exclusionSig, new StubBuilder(), exceptionHandler);
    }

    public MethodTester(String methodSig, ParamBuilder paramBuilder) {
        this(methodSig,new String[]{},paramBuilder,ExceptionHandler.FAIL_ON_EXCEPTION);

    }
    public MethodTester(String methodSig, String[] exclusionSig, ParamBuilder paramBuilder, ExceptionHandler exceptionHandler) {
        this.methodSig = methodSig;
        this.exclusionSig = exclusionSig;
        this.paramBuilder = paramBuilder;
        this.exceptionHandler = exceptionHandler;
    }

    public void performTest(Object testee) {
        performTest(testee,methodSig,exclusionSig);
    }
    
    public void performTest(Object testee, String methodSignature, String[] exclusionSignatures) {
        Class<?> testClass = testee.getClass();
        Collection<Method> methods = pruneMethods(testClass);
        for (Method method : methods) {
            if (passesFilters(method,methodSignature,exclusionSignatures)) {
                runMethod( method, testee);
            }
        }
    }

    /**
     * uses the constructor exclusion signatures
     * @param testee
     */
    public void performGettersAndSetters(Object testee) {
        performGettersAndSetters(testee,exclusionSig);
    }

    /**
     * overrides the constructor exclusion signatures
     * @param testee
     * @param exclusionSignatures
     */
    public void performGettersAndSetters(Object testee, String[] exclusionSignatures) {
        performTest(testee,"get",exclusionSignatures);
        performTest(testee,"set",exclusionSignatures);
    }

    private boolean passesFilters(Method method, String methodSignature, String[] exclusionSignatures) {
        if (!method.getName().startsWith(methodSignature))
            return false;
        for(String exclusion : exclusionSignatures) {
            if (method.getName().startsWith(exclusion))
                return false;
        }
        return true;
    }
    private Collection<Method> pruneMethods(Class<?> testClass) {
        Method[] methods = testClass.getMethods();
        Method[] objMethods = Object.class.getMethods();
        Collection<Method> results = new ArrayList<Method>(Arrays.asList(methods));
        results.removeAll(new ArrayList<Method>(Arrays.asList(objMethods)));
        return results;
    }

    private void runMethod(Method method, Object testee) {
        if (paramBuilder == null) {
            paramBuilder = new DummyObjectBuilder(new HashMap<Class<?>, Object>(),
                                                        ClassTypeHandlerChain.defaultTypeChain());
        }
        if (testedMethods == null) {
            testedMethods = new ArrayList<String>();
        }
        try {
            method.invoke(testee, paramBuilder.createParams(method.getParameterTypes()));
            testedMethods.add(method.getName());
        } catch (Exception e) {
            exceptionHandler.handleException(e,method);
        }
    }

    /**
     * This has been deprecated by the ExceptionHandler enum.
     * Running this temporarily overrides any ExceptionHandler specified at construction with SILENT_ON_EXCEPTION
     *
     * @see ExceptionHandler
     * @deprecated
     * @param testee
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void performSilentTest(Object testee) {
        ExceptionHandler temp = this.exceptionHandler;
        this.exceptionHandler = ExceptionHandler.SILENT_ON_EXCEPTION;
        performTest(testee);
        this.exceptionHandler = temp;
    }

    public Collection<String> getTestedMethods() {
        return testedMethods;
    }
}
