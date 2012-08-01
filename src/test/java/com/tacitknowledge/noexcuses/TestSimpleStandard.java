package com.tacitknowledge.noexcuses;

import junit.framework.TestCase;

public class TestSimpleStandard extends TestCase {


    public void testTestStandard() {
		SimpleStandard ss = new SimpleStandard();
		assertNotNull(ss.getState());
		ss.setState("new state");
		assertNotNull(ss.getNbResets());
		ss.reset();
		assertNotNull(ss.getNbChanges());

	}


}