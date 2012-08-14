package com.tacitknowledge.noexcuses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

/**
 * User: mshort Date: Dec 21, 2006 Time: 9:21:40 AM
 */
public class StubBuilderTest
{

    @Test
    public void testCreateParamsInterface() throws Exception
    {
        StubBuilder stubBuilder = new StubBuilder();
        Class clazz = ParamBuilder.class;
        Object[] objects = stubBuilder.createParams(new Class[] { clazz });
        assertTrue(ParamBuilder.class.isAssignableFrom(objects[0].getClass()));
    }

    @Test
    public void testCreateParamsObjectDefaultConstructor()
    {
        StubBuilder stubBuilder = new StubBuilder();
        Class clazz = Object.class;
        Object[] objects = stubBuilder.createParams(new Class[] { clazz });
        assertTrue(Object.class.isAssignableFrom(objects[0].getClass()));
    }

    @Test
    public void testCreateParamsObjectNoDefaultConstructor()
    {
        StubBuilder stubBuilder = new StubBuilder();
        Class clazz = DependObject.class;
        Object[] objects = stubBuilder.createParams(new Class[] { clazz });
        assertTrue(DependObject.class.isAssignableFrom(objects[0].getClass()));
    }

    @Test
    public void testSmallestConstructor() throws Exception
    {
        StubBuilder stubBuilder = new StubBuilder();
        Constructor<DummyObject> constructor = stubBuilder.getSmallestConstructor(DummyObject.class);

        assertEquals(2, constructor.getParameterTypes().length);
    }

    @Test
    public void testGetDefaultContructor() throws Exception
    {
        StubBuilder stubBuilder = new StubBuilder();
        assertNull(stubBuilder.getDefaultConstructor(DummyObject.class));
        assertNotNull(stubBuilder.getDefaultConstructor(ExtendedPojo.class));
    }

    @Test
    public void testDefaultInstancesInReturnOnesAnswerStrategy() throws Exception
    {
        StubBuilder stubBuilder = new StubBuilder();
        MockObject mockObject = stubBuilder.createObject(MockObject.class);

        assertEquals(1, mockObject.getLong());
        assertEquals(LinkedList.class, mockObject.getCollection().getClass());
        assertEquals(HashSet.class, mockObject.getSet().getClass());
        assertEquals(HashSet.class, mockObject.getHashSet().getClass());
        assertEquals(TreeSet.class, mockObject.getSortedSet().getClass());
        assertEquals(TreeSet.class, mockObject.getTreeSet().getClass());
        assertEquals(LinkedHashSet.class, mockObject.getLinkedHashSet().getClass());
        assertEquals(LinkedList.class, mockObject.getList().getClass());
        assertEquals(LinkedList.class, mockObject.getLinkedList().getClass());
        assertEquals(ArrayList.class, mockObject.getArrayList().getClass());
        assertEquals(HashMap.class, mockObject.getMap().getClass());
        assertEquals(HashMap.class, mockObject.getHashMap().getClass());
        assertEquals(TreeMap.class, mockObject.getSortedMap().getClass());
        assertEquals(TreeMap.class, mockObject.getTreeMap().getClass());
        assertEquals(LinkedHashMap.class, mockObject.getLinkedHashMap().getClass());
    }

    @SuppressWarnings("rawtypes")
    private class MockObject
    {
        public long getLong()
        {
            return 0;
        }

        public Collection getCollection()
        {
            return null;
        }

        public Set getSet()
        {
            return null;
        }

        public HashSet getHashSet()
        {
            return null;
        }

        public SortedSet getSortedSet()
        {
            return null;
        }

        public TreeSet getTreeSet()
        {
            return null;
        }

        public LinkedHashSet getLinkedHashSet()
        {
            return null;
        }

        public List getList()
        {
            return null;
        }

        public LinkedList getLinkedList()
        {
            return null;
        }

        public ArrayList getArrayList()
        {
            return null;
        }

        public Map getMap()
        {
            return null;
        }

        public HashMap getHashMap()
        {
            return null;
        }

        public SortedMap getSortedMap()
        {
            return null;
        }

        public TreeMap getTreeMap()
        {
            return null;
        }

        public LinkedHashMap getLinkedHashMap()
        {
            return null;
        }
    }
}
