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

/**
 * Builds object instances to be used as parameters in method and constructor invocations.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public interface ParamBuilder
{

    /**
     * Creates object instances, given class types. This method is useful when calling methods or
     * constructors via reflection, because method invocation requires array of objects to pass as
     * parameters if any.
     * 
     * @param paramTypes
     *            array of types to be instantiated
     * @return corresponding array of object instances
     */
    Object[] createParams(Class<?>[] paramTypes);
}
