package com.tacitknowledge.noexcuses;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: mshort
 * Date: 7/6/12
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MethodTesterException extends RuntimeException {

	private static final long serialVersionUID = 1987474748242613605L;
	
	public static final String DEFAULT_PREFIX = "Exception occurred while running executing target method [";
    public static final String DEFAULT_POSTFIX = "] See root cause.";

    public MethodTesterException(Method method) {
        super(DEFAULT_PREFIX + method.getName() + DEFAULT_POSTFIX);
    }

    public MethodTesterException(String s) {
        super(s);
    }

    public MethodTesterException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MethodTesterException(Method method,Throwable throwable) {
        super(DEFAULT_PREFIX + method.getName() + DEFAULT_POSTFIX,throwable);
    }
}
