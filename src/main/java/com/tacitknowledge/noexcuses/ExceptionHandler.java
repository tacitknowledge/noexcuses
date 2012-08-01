package com.tacitknowledge.noexcuses;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: mshort
 * Date: 7/6/12
 * Time: 1:49 PM
 * Controls how exceptions are handled by the method tester
 * Defaults to FAIL_ON_EXCEPTION
 * */
public enum ExceptionHandler {
    /**
     * Prints exception stack trace but does not rethrow
     */
    PRINT_ON_EXCEPTION {
        @Override
        public void handleException(Exception e, Method target) {
            new MethodTesterException(target,e).printStackTrace();
        }
    },
    /**
     * Throws the exception wrapped in a MethodTesterException
     *
     */
    FAIL_ON_EXCEPTION {
        @Override
        public void handleException(Exception e, Method target) {
            throw new MethodTesterException(target,e);
       }
    },
    /**
     * Prints the exception message only. No Stack trace
     */
    SILENT_ON_EXCEPTION {
        @Override
        public void handleException(Exception e, Method target) {
            System.out.println("Exception invoking method '"+ target.getName()+"': " + e.getMessage());
        }
    };

    abstract public void handleException(Exception e, Method target);
}
