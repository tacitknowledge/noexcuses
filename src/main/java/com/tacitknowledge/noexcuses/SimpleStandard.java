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
 * Simple definition of a standard MBean, named "SimpleStandard".
 *
 * This MBean has two attributes and one operation exposed
 * for management by a JMX agent:
 *      - the read/write "State" attribute,
 *      - the read only "NbChanges" attribute,
 * - the "reset()" operation.
 *
 * This object also has one property and one method not exposed
 * for management by a JMX agent:
 * - the "NbResets" property,
 * - the "getNbResets()" method.
 * 
 *  @author Matthew Short (mshort@tacitknowledge.com)
 */

public class SimpleStandard implements SimpleStandardMBean
{
    /*
     * -----------------------------------------------------
     * ATTRIBUTES ACCESSIBLE FOR MANAGEMENT BY A JMX AGENT
     * -----------------------------------------------------
     */

    private String state = "initial state";

    private int nbChanges = 0;

    /*
     * -----------------------------------------------------
     * PROPERTY NOT ACCESSIBLE FOR MANAGEMENT BY A JMX AGENT
     * -----------------------------------------------------
     */

    private int nbResets = 0;

    /*
     * -----------------------------------------------------
     * CONSTRUCTORS
     * -----------------------------------------------------
     */

    /* "SimpleStandard" does not provide any specific constructors.
     * However, "SimpleStandard" is JMX compliant with regards to
     * contructors because the default contructor SimpleStandard()
     * provided by the Java compiler is public.
     */

    /*
     * -----------------------------------------------------
     * IMPLEMENTATION OF THE SimpleStandardMBean INTERFACE
     * -----------------------------------------------------
     */

    /**
     * Getter: get the "State" attribute of the "SimpleStandard" standard MBean.
     *
     * @return the current value of the "State" attribute.
     */
    @Override
    public String getState()
    {
        return state;
    }

    /** 
     * Setter: set the "State" attribute of the "SimpleStandard" standard MBean.
     *
     * @param s  the new value of the "State" attribute.
     */
    @Override
    public void setState(String s)
    {
        state = s;
        nbChanges++;
    }

    /**
     * Getter: get the "NbChanges" attribute of the "SimpleStandard" standard
     * MBean.
     *
     * @return the current value of the "NbChanges" attribute.
     */
    @Override
    public int getNbChanges()
    {
        return nbChanges;
    }

    /**
     * Operation: reset to their initial values the "State" and "NbChanges"
     * attributes of the "SimpleStandard" standard MBean.
     */
    @Override
    public void reset()
    {
        state = "initial state";
        nbChanges = 0;
        nbResets++;
    }

    /*
     * -----------------------------------------------------
     * METHOD NOT EXPOSED FOR MANAGEMENT BY A JMX AGENT
     * -----------------------------------------------------
     */

    /**
     * Return the "NbResets" property.
     * This method is not a Getter in the JMX sense because it
     * is not exposed in the "SimpleStandardMBean" interface.
     *
     * @return the current value of the "NbResets" property.
     */
    public int getNbResets()
    {
        return nbResets;
    }
}