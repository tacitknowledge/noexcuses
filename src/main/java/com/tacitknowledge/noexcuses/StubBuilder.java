package com.tacitknowledge.noexcuses;

import org.mockito.Mockito;
import org.mockito.internal.creation.ClassNameFinder;
import org.mockito.internal.invocation.Invocation;
import org.mockito.internal.util.MockName;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.*;

import javax.script.Invocable;

/** @author mshort */
@SuppressWarnings("PMD")
public class StubBuilder extends AbstractParamBuilder implements ParamBuilder
{
    /** The strategy used for returning custom empty values for stubbed methods. */
    private ReturnOnesAnswerStrategy customEmptyValues;

    /** Default constructor */
    public StubBuilder()
    {
        customEmptyValues = new ReturnOnesAnswerStrategy();
    }

    /**
     * Creates an object of a given type initialized with a default value.
     *
     * @param paramType the class of the object to create.
     * @param <T>       the type of the passed object.
     * @return the primitive type object with a custom default value or a mock of the passed type
     */
    @SuppressWarnings("unchecked")
    protected <T> T createObject(final Class<T> paramType)
    {
        if (paramType.isPrimitive() || isFinal(paramType))
        {
            return (T) customEmptyValues.returnValueFor(paramType); //unchecked cast
        }
        return Mockito.mock(paramType, customEmptyValues);
    }

    protected Constructor getDefaultConstructor(Class paramType)
    {
        Constructor[] constructors = paramType.getConstructors();
        for (Constructor constructor : constructors)
        {
            if (constructor.getParameterTypes().length == 0)
            {
                return constructor;
            }
        }
        return null;
    }

    protected Constructor getSmallestConstructor(Class paramType)
    {
        List<Constructor> constructors = Arrays.asList(paramType.getDeclaredConstructors());
        Comparator comparator = new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                Constructor one = (Constructor) o1;
                Constructor two = (Constructor) o2;
                return new Integer(one.getParameterTypes().length).compareTo(two
                        .getParameterTypes().length);
            }
        };

        Collections.sort(constructors, comparator);
        return constructors.get(0);
    }

    protected boolean isFinal(Class paramType)
    {
        return Modifier.isFinal(paramType.getModifiers());
    }

    protected String defaultMockNameForType(Class mockedType)
    {
        return customEmptyValues.valueForToString(mockedType);
    }

    /** A strategy for returning empty objects for mockito. */
    private static class ReturnOnesAnswerStrategy implements Answer<Object>, Serializable
    {
        /** Serial id */
        private static final long serialVersionUID = 989764447988489023L;
        
        private ObjectMethodsGuru methodsGuru = new ObjectMethodsGuru();
        
        /** A map of default values */
        private Map<Class<?>, Object> myInstances = new HashMap<Class<?>, Object>();

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
         * @param mock the mock
         * @return toString value for a mock
         */
        public String valueForToString(final Object mock)
        {
            MockName name = new MockUtil().getMockName(mock);
            if (name.isSurrogate())
            {
                return "Mock for " + ClassNameFinder.classNameForMock(mock) + ", hashCode: "
                        + mock.hashCode();
            }
            else
            {
                return name.toString();
            }
        }

        /**
         * Gets a default return value for a stubbed method return type
         *
         * @param type the type for which we need the default value
         * @return the default value
         */
        public Object returnValueFor(final Class<?> type)
        {
            Object result = null;
            if (myInstances.containsKey(type))
            {
                result = myInstances.get(type);
            }
            //new instances are used instead of Collections.emptyList(), etc.
            //to avoid UnsupportedOperationException if code under test modifies returned collection
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
            //Let's not care about the rest of collections.
            return result;
        }
    }
}
