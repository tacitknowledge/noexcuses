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
