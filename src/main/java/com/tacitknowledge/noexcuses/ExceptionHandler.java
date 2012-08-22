/* Copyright 2012 Tacit Knowledge
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.tacitknowledge.noexcuses;

import java.lang.reflect.Method;

/**
 * Controls how exceptions are handled by the method tester.
 * Defaults to FAIL_ON_EXCEPTION.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 * */
public enum ExceptionHandler
{
    /**
     * Prints exception stack trace but does not rethrow
     */
    PRINT_ON_EXCEPTION
    {
        @Override
        public void handleException(Exception e, Method target)
        {
            new MethodTesterException(target, e).printStackTrace();
        }
    },

    /**
     * Throws the exception wrapped in a MethodTesterException
     */
    FAIL_ON_EXCEPTION
    {
        @Override
        public void handleException(Exception e, Method target)
        {
            throw new MethodTesterException(target, e);
        }
    },

    /**
     * Prints the exception message only. No Stack trace
     */
    SILENT_ON_EXCEPTION
    {
        @Override
        public void handleException(Exception e, Method target)
        {
            System.out.println("Exception invoking method '" + target.getName() + "': " + e.getMessage());
        }
    };

    /**
     * Handles exception thrown from within <code>target</code> method.
     * 
     * @param e instance of {@link Exception}
     * @param target {@link Method} entity that triggered exception
     */
    public abstract void handleException(Exception e, Method target);
}
