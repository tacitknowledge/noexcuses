package com.tacitknowledge.noexcuses;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SimpleStandardTest
{
    @Test
    public void testTestStandard()
    {
        SimpleStandard ss = new SimpleStandard();
        assertNotNull(ss.getState());
        ss.setState("new state");
        assertNotNull(ss.getNbResets());
        ss.reset();
        assertNotNull(ss.getNbChanges());

    }

}