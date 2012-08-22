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
 * A super class to build object instances, given class types. Returned object instances are used as
 * method or constructor parameters.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public abstract class AbstractParamBuilder implements ParamBuilder
{
    @Override
    public Object[] createParams(Class<?>[] paramTypes)
    {
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < params.length; i++)
        {
            params[i] = createObject(paramTypes[i]);
        }
        return params;
    }

    /**
     * Creates an object of a given type initialized with a default value.
     * 
     * @param <T>
     *            the type of the passed object.
     * @param type
     *            the class of the object to create.
     * @return the primitive type object with a custom default value or a mock of the passed type
     */
    protected abstract <T> T createObject(Class<T> type);
}
