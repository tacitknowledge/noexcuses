package com.tacitknowledge.noexcuses;

import com.tacitknowledge.noexcuses.DependObject;
import junit.framework.TestCase;


/**
 * Created by IntelliJ IDEA.
 * User: mshort
 * Date: Dec 21, 2006
 * Time: 9:21:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestStubBuilder extends TestCase {



      public void testCreateParamsInterface() throws Exception {
          StubBuilder stubBuilder = new StubBuilder();
          Class clazz = ParamBuilder.class;
          Object[] objects = stubBuilder.createParams(new Class[] {
             clazz
          });
          assertTrue(ParamBuilder.class.isAssignableFrom(objects[0].getClass()));
      }

      public void testCreateParamsObjectDefaultConstructor() {
          StubBuilder stubBuilder = new StubBuilder();
          Class clazz = Object.class;
          Object[] objects = stubBuilder.createParams(new Class[] {
             clazz
          });
          assertTrue(Object.class.isAssignableFrom(objects[0].getClass()));

      }

    public void testCreateParamsObjectNoDefaultConstructor() {
        StubBuilder stubBuilder = new StubBuilder();
        Class clazz = DependObject.class;
        Object[] objects = stubBuilder.createParams(new Class[] {
           clazz
        });
        assertTrue(DependObject.class.isAssignableFrom(objects[0].getClass()));
    }



}
