package com.tacitknowledge.noexcuses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

/**
 * Simplify some testing of methods so that Emma will pass
 * <p/>
 * IMPORTANT!!! This ONLY exists because as of yet, Emma doesn't pay attention to annotations (allowing us to codify
 * which methods are safely ignored by the test framework).  Once these are in place, this and it's "tests" should be
 * removed.
 * <p/>
 *  Originally created: Oct 31, 2006
 *  
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public class MethodTester implements BeanTester
{
    /**
     * prefix for methods to be included in tester
     */
    private final String methodSig;

    /**
     * array of method prefixes to exclude
     */
    private final String[] exclusionSig;

    /**
     * Collection containing names of the methods that had already 
     * passed through verification process
     */
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
    public MethodTester()
    {
        this("", new String[] {}, new StubBuilder(), ExceptionHandler.FAIL_ON_EXCEPTION);
    }

    /**
     * Creating instance to filter methods that comply with provided <code>methodSig</code>
     * prefix. Exception during testing process results in a failure.
     * 
     * @param methodSig prefix for methods to be included in tester
     */
    public MethodTester(String methodSig)
    {
        this(methodSig, new String[] {}, null, ExceptionHandler.FAIL_ON_EXCEPTION);
    }

    /**
     * Hereby we mean to test all of the methods and, additionally,
     * handle exceptions with provided <code>exceptionHandler<code>.
     * 
     * @param exceptionHandler {@link ExceptionHandler} to handle exceptions
     *  during testing
     */
    public MethodTester(ExceptionHandler exceptionHandler)
    {
        this(StringUtils.EMPTY, new String[] {}, new StubBuilder(), exceptionHandler);
    }

    /**
     * Hereby we mean to test methods whose names are prefixed with provided <code>methodSig</code>,
     * additionally, handle exceptions with provided <code>exceptionHandler<code>.
     * 
     * @param methodSig prefix for methods to be included in tester
     * @param exceptionHandler {@link ExceptionHandler} to handle exceptions
     *  during testing
     */
    public MethodTester(String methodSig, ExceptionHandler exceptionHandler)
    {
        this(methodSig, new String[] {}, new StubBuilder(), exceptionHandler);
    }

    /**
     * 
     * @param methodSig prefix for methods to be included in tester
     * @param exclusionSig array of method prefixes to exclude
     * @param exceptionHandler {@link ExceptionHandler} to handle exceptions
     *  during testing
     */
    public MethodTester(String methodSig, String[] exclusionSig, ExceptionHandler exceptionHandler)
    {
        this(methodSig, exclusionSig, new StubBuilder(), exceptionHandler);
    }

    /**
     * 
     * @param methodSig prefix for methods to be included in tester
     * @param paramBuilder {@link ParamBuilder} instance used for required objects
     *  creation
     */
    public MethodTester(String methodSig, ParamBuilder paramBuilder)
    {
        this(methodSig, new String[] {}, paramBuilder, ExceptionHandler.FAIL_ON_EXCEPTION);
    }

    /**
     * 
     * @param methodSig prefix for methods to be included in tester
     * @param exclusionSig array of method prefixes to exclude
     * @param paramBuilder {@link ParamBuilder} instance used for required objects
     *  creation
     * @param exceptionHandler {@link ExceptionHandler} to handle exceptions
     *  during testing
     */
    public MethodTester(String methodSig, String[] exclusionSig, ParamBuilder paramBuilder,
            ExceptionHandler exceptionHandler)
    {
        this.methodSig = methodSig;
        this.exclusionSig = exclusionSig;
        this.paramBuilder = paramBuilder;
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * 
     */
    @Override
    public void performTest(Object testee)
    {
        performTest(testee, methodSig, exclusionSig);
    }

    /**
     * 
     * @param testee
     * @param methodSignature
     * @param exclusionSignatures
     */
    public void performTest(Object testee, String methodSignature, String[] exclusionSignatures)
    {
        Class<?> testClass = testee.getClass();
        Collection<Method> methods = pruneMethods(testClass);
        for (Method method : methods)
        {
            if (passesFilters(method, methodSignature, exclusionSignatures))
            {
                runMethod(method, testee);
            }
        }
    }

    /**
     * uses the constructor exclusion signatures
     * @param testee
     */
    public void performGettersAndSetters(Object testee)
    {
        performGettersAndSetters(testee, exclusionSig);
    }

    /**
     * Overrides the constructor exclusion signatures
     * 
     * @param testee subject of test procedure
     * @param exclusionSignatures array of the method name signatures to be excluded
     *  from the methods-to-be-tested list
     */
    public void performGettersAndSetters(Object testee, String[] exclusionSignatures)
    {
        performTest(testee, "get", exclusionSignatures);
        performTest(testee, "set", exclusionSignatures);
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
    @Deprecated
    @Override
    public void performSilentTest(Object testee)
    {
        ExceptionHandler temp = this.exceptionHandler;
        this.exceptionHandler = ExceptionHandler.SILENT_ON_EXCEPTION;
        performTest(testee);
        this.exceptionHandler = temp;
    }

    /**
     * @return collection whose values represent names of the methods
     *  that had already been tested
     */
    public Collection<String> getTestedMethods()
    {
        return testedMethods;
    }

    /**
     * Determines whether provided <code>method</code> complies
     * with the naming filtering, i.e. whether method's name matches to the
     * <code>methodSignature</code> prefix, and at the same time doesn't fall
     * under none of the prefix matching from <code>exclusionSignatures</code> array
     * 
     * @param method {@link Method} instance to be filtered
     * @param methodSignature method name prefix to be included
     * @param exclusionSignatures array of method prefixes to exclude
     * @return <code>true</code> in case <code>method</code>'s name passes
     *  filtering, <code>false</code> - otherwise
     */
    private boolean passesFilters(Method method, String methodSignature, String[] exclusionSignatures)
    {
        if (!method.getName().startsWith(methodSignature))
        {
            return false;
        }
        for (String exclusion : exclusionSignatures)
        {
            if (method.getName().startsWith(exclusion))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Pruning {@link Object} defined methods from the list of 
     * the provided <code>testClass</code> methods. This way we're
     * assuring that we end up with the list of methods that are pertinent
     * to at least one level beyond that of <code>Object</code> class.
     * 
     * @param testClass {@link Class} whose methods to prune
     * @return collection of {@link Method}s that contain all of the <code>testClass</code>
     *  defined methods with the exclusion of those defined on {@link Object} level.
     */
    private Collection<Method> pruneMethods(Class<?> testClass)
    {
        Method[] methods = testClass.getMethods();
        Method[] objMethods = Object.class.getMethods();
        Collection<Method> results = new ArrayList<Method>(Arrays.asList(methods));
        results.removeAll(new ArrayList<Method>(Arrays.asList(objMethods)));
        return results;
    }

    /**
     * 
     * @param method {@link Method} instance to be run
     * @param testee object method to be invoked against
     */
    private void runMethod(Method method, Object testee)
    {
        if (paramBuilder == null)
        {
            paramBuilder = new DummyObjectBuilder(new HashMap<Class<?>, Object>(),
                    ClassTypeHandlerChain.defaultTypeChain());
        }
        if (testedMethods == null)
        {
            testedMethods = new ArrayList<String>();
        }
        try
        {
            method.invoke(testee, paramBuilder.createParams(method.getParameterTypes()));
            testedMethods.add(method.getName());
        }
        catch (Exception e)
        {
            exceptionHandler.handleException(e, method);
        }
    }
}
