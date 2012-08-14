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
