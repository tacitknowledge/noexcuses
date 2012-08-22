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
 * Contract that handles creation of instances of a given type
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public interface ClassTypeHandler
{
    /**
     * Creates instance of the given type
     * 
     * @param <T> type of the instance to be created
     * @param type class type of the to be created object
     * @return the corresponding object
     */
    <T> T createInstance(Class<T> type);
}
