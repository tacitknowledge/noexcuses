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

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

import org.mockito.Mockito;
import org.mockito.internal.creation.ClassNameFinder;
import org.mockito.internal.util.MockName;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * This implementation builds mockito stubs or default values for primitives.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
@SuppressWarnings("unchecked")
public class StubBuilder extends AbstractParamBuilder implements ParamBuilder
{
    /** The strategy used for returning custom empty values for stubbed methods. */
    private final ReturnOnesAnswerStrategy customEmptyValues;

    /** Default constructor */
    public StubBuilder()
    {
        customEmptyValues = new ReturnOnesAnswerStrategy();
    }

    @Override
    protected <T> T createObject(final Class<T> paramType)
    {
        if (paramType.isPrimitive() || isFinal(paramType))
        {
            return (T) customEmptyValues.returnValueFor(paramType);
        }
        return Mockito.mock(paramType, customEmptyValues);
    }

    /**
     * Gets default constructor (with no parameters) for the type class
     * 
     * @param <T>
     *            type of class
     * @param paramType
     *            {@link Class} instance
     * @return default {@link Constructor} instance
     */
    protected <T> Constructor<T> getDefaultConstructor(Class<T> paramType)
    {
        Constructor<T>[] constructors = (Constructor<T>[]) paramType.getConstructors();
        for (Constructor<T> constructor : constructors)
        {
            if (constructor.getParameterTypes().length == 0)
            {
                return constructor;
            }
        }
        return null;
    }

    /**
     * Returns constructor with smallest number of parameters.
     * 
     * @param <T>
     *            type of class
     * @param paramType
     *            {@link Class} instance
     * @return default {@link Constructor} instance
     */
    protected <T> Constructor<T> getSmallestConstructor(Class<T> paramType)
    {
        List<Constructor<T>> constructors = Arrays.asList(((Constructor<T>[]) paramType.getDeclaredConstructors()));

        Comparator<Constructor<T>> comparator = new Comparator<Constructor<T>>()
        {
            @Override
            public int compare(Constructor<T> one, Constructor<T> two)
            {
                return new Integer(one.getParameterTypes().length).compareTo(two.getParameterTypes().length);
            }
        };

        Collections.sort(constructors, comparator);
        return constructors.get(0);
    }

    /**
     * Returns whether class is final
     * @param paramType
     *            {@link Class} instance
     * @return <code>true</code> if the class is final, <code>false</code> otherwise
     */
    protected boolean isFinal(Class<?> paramType)
    {
        return Modifier.isFinal(paramType.getModifiers());
    }

    /** A strategy for returning empty objects for mockito. */
    private static class ReturnOnesAnswerStrategy implements Answer<Object>, Serializable
    {
        /** Serial id */
        private static final long serialVersionUID = 989764447988489023L;

        private final ObjectMethodsGuru methodsGuru = new ObjectMethodsGuru();

        /** A map of default values */
        private final Map<Class<?>, Object> myInstances = new HashMap<Class<?>, Object>();

        /** Default constructor */
        public ReturnOnesAnswerStrategy()
        {
            initialize();
        }

        /** Initializes the types map with default values for primitive types. */
        public void initialize()
        {
            myInstances.put(long.class, 1);
            myInstances.put(Long.class, (long) 1);
            myInstances.put(int.class, 1);
            myInstances.put(Integer.class, 1);
            myInstances.put(String.class, "1");
            myInstances.put(float.class, 1);
            myInstances.put(Float.class, (float) 1);
            myInstances.put(double.class, 1);
            myInstances.put(Double.class, (double) 1);
            myInstances.put(short.class, 1);
            myInstances.put(Short.class, (short) 1);
            myInstances.put(byte.class, 1);
            myInstances.put(Byte.class, (byte) 1);
            myInstances.put(boolean.class, true);
            myInstances.put(Boolean.class, Boolean.TRUE);
        }

        /** {@inheritDoc} */
        @Override
        public Object answer(final InvocationOnMock invocation) throws Throwable
        {
            if (methodsGuru.isToString(invocation.getMethod()))
            {
                return valueForToString(invocation.getMock());
            }

            return returnValueFor(invocation.getMethod().getReturnType());
        }

        /**
         * Gets the toString value for a mock
         * 
         * @param mock
         *            the mock
         * @return toString value for a mock
         */
        public String valueForToString(final Object mock)
        {
            MockName name = new MockUtil().getMockName(mock);
            if (name.isSurrogate())
            {
                return "Mock for " + ClassNameFinder.classNameForMock(mock) + ", hashCode: " + mock.hashCode();
            }
            else
            {
                return name.toString();
            }
        }

        /**
         * Gets a default return value for a stubbed method return type
         * 
         * @param type
         *            the type for which we need the default value
         * @return the default value
         */
        public Object returnValueFor(final Class<?> type)
        {
            Object result = null;
            if (myInstances.containsKey(type))
            {
                result = myInstances.get(type);
            }
            // new instances are used instead of Collections.emptyList(), etc.
            // to avoid UnsupportedOperationException if code under test modifies returned
            // collection
            else if (type == Collection.class)
            {
                result = new LinkedList<Object>();
            }
            else if (type == Set.class)
            {
                result = new HashSet<Object>();
            }
            else if (type == HashSet.class)
            {
                result = new HashSet<Object>();
            }
            else if (type == SortedSet.class)
            {
                result = new TreeSet<Object>();
            }
            else if (type == TreeSet.class)
            {
                result = new TreeSet<Object>();
            }
            else if (type == LinkedHashSet.class)
            {
                result = new LinkedHashSet<Object>();
            }
            else if (type == List.class)
            {
                result = new LinkedList<Object>();
            }
            else if (type == LinkedList.class)
            {
                result = new LinkedList<Object>();
            }
            else if (type == ArrayList.class)
            {
                result = new ArrayList<Object>();
            }
            else if (type == Map.class)
            {
                result = new HashMap<Object, Object>();
            }
            else if (type == HashMap.class)
            {
                result = new HashMap<Object, Object>();
            }
            else if (type == SortedMap.class)
            {
                result = new TreeMap<Object, Object>();
            }
            else if (type == TreeMap.class)
            {
                result = new TreeMap<Object, Object>();
            }
            else if (type == LinkedHashMap.class)
            {
                result = new LinkedHashMap<Object, Object>();
            }
            // Let's not care about the rest of collections.
            return result;
        }
    }
}
