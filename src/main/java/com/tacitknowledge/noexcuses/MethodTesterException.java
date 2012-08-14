package com.tacitknowledge.noexcuses;

import java.lang.reflect.Method;

/**
 * Exception used as a wrapper for any other exceptions that occur during
 * the testing phases.
 * 
 * @see ExceptionHandler for exception handling flow details
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
@SuppressWarnings("serial")
public class MethodTesterException extends RuntimeException
{
    /**
     * Identifies default prefix for method tester exception
     */
    public static final String DEFAULT_PREFIX = "Exception occurred while running executing target method [";

    /**
     * Identifies default postfix for method tester exception
     */
    public static final String DEFAULT_POSTFIX = "] See root cause.";

    /**
     * Parameterized constructor
     * @param method {@link Method} entity that triggered exception
     * @param throwable instance of {@link Throwable}
     */
    public MethodTesterException(Method method, Throwable throwable)
    {
        super(DEFAULT_PREFIX + method.getName() + DEFAULT_POSTFIX, throwable);
    }
}
