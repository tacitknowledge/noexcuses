package com.tacitknowledge.noexcuses;

/**
 *  Originally created: Oct 31, 2006
 *  
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public interface BeanTester
{
    /**
     * 
     * @param testee
     */
    void performTest(Object testee);

    /**
     * 
     * @param testee
     */
    void performSilentTest(Object testee);
}
