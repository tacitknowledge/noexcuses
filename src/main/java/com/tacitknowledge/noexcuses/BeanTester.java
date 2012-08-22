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
 * Performs tests on the given object.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public interface BeanTester
{
    /**
     * Performs test against the provided object.
     * 
     * @param testee test subject
     */
    void performTest(Object testee);

    /**
     * Performs silent testing, i.e. exceptions that might occur during
     * the testing phase will be swallowed or silently logged and not propagated further.
     * 
     * @param testee test subject
     */
    void performSilentTest(Object testee);
}
