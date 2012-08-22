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
 * This is the management interface explicitly defined for the
 * "SimpleStandard" standard MBean.
 *
 * The "SimpleStandard" standard MBean implements this interface
 * in order to be manageable through a JMX agent.
 *
 * The "SimpleStandardMBean" interface shows how to expose for management:
 * - a read/write attribute (named "State") through its getter and setter
 *   methods,
 * - a read-only attribute (named "NbChanges") through its getter method,
 * - an operation (named "reset").
 * 
 *  @author Matthew Short (mshort@tacitknowledge.com)
 */
public interface SimpleStandardMBean 
{

    /**
     * Getter: set the "State" attribute of the "SimpleStandard" standard
     * MBean.
     *
     * @return the current value of the "State" attribute.
     */
    public String getState();

    /**
     * Setter: set the "State" attribute of the "SimpleStandard" standard
     * MBean.
     *
     * @param s the new value of the "State" attribute.
     */
    public void setState(String s);

    /**
     * Getter: get the "NbChanges" attribute of the "SimpleStandard" standard
     * MBean.
     *
     * @return the current value of the "NbChanges" attribute.
     */
    public int getNbChanges();

    /**
     * Operation: reset to their initial values the "State" and "NbChanges"
     * attributes of the "SimpleStandard" standard MBean.
     */
    public void reset();
}