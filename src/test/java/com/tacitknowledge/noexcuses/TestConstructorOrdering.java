package com.tacitknowledge.noexcuses;

import junit.framework.TestCase;

import java.lang.reflect.Constructor;

/*
 *
 * Originally created: Nov 16, 2006
 */

public class TestConstructorOrdering extends TestCase {

	public void testConstructorSignatureSortOrder() {
		Class[] classes = new Class[] {
			MyStaticInnerClass.class,
			MyInstanceInnerClass.class,
			Pojo.class,
			ExtendedPojo.class
		};

		for(Class aclass : classes) {
			examineConstructor(aclass);
		}
	}

	private void examineConstructor(Class aclass) {
		Constructor[] constructors = aclass.getConstructors();
		System.out.print(aclass.getSimpleName() + " has "+ constructors.length +" public constructors with param lengths: ");

		for (Constructor constructor : constructors) {
			System.out.print(constructor.getParameterTypes().length + " ");
		}
		System.out.print("\n");

		constructors = aclass.getDeclaredConstructors();
		System.out.print(aclass.getSimpleName() + " has "+ constructors.length +" *DECLARED* constructors with param lengths: ");

		for (Constructor constructor : constructors) {
			System.out.print(constructor.getParameterTypes().length + " ");
		}
		System.out.print("\n");
	}


	private static class MyStaticInnerClass {
		public MyStaticInnerClass(Object o1, String str, long number) {}
		public MyStaticInnerClass() {}
		public MyStaticInnerClass(Object o1, String str) {}
		private MyStaticInnerClass(long num) {}
		protected MyStaticInnerClass(int num) {}
	}


	private class MyInstanceInnerClass {
		public MyInstanceInnerClass(Object o1, String str, long number) {}
		public MyInstanceInnerClass() {}
		public MyInstanceInnerClass(Object o1, String str) {}
		private MyInstanceInnerClass(long num) {}
		protected MyInstanceInnerClass(int num) {}
	}
}
