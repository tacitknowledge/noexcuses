package com.tacitknowledge.noexcuses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple chain for invoking each handler and returning the first to succeed in creating an instance.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public class ClassTypeHandlerChain
{
    private final List<ClassTypeHandler> handlList;

    /**
     * Constructor that registers a list of {@link ClassTypeHandler}s.
     * 
     * @param handlList list of {@link ClassTypeHandler} instances
     */
    public ClassTypeHandlerChain(List<ClassTypeHandler> handlList)
    {
        this.handlList = handlList;
    }

    /**
     * Goes through the registered list of {@link ClassTypeHandler}s 
     * trying to create an instance for the provided <code>type</code>.
     * Stops on the first handler that can successfully create an object.
     * May return <code>null</code> if none of the registered type handlers
     * are capable to handle given <code>type</code>.
     * 
     * @param <T> type of the instance that should be created
     * @param type class type of the to be created object
     * @return the corresponding created object in case one of the registered handlers
     *  is able to create one successfully, <code>null</code> - otherwise 
     */
    public <T> T processChain(Class<T> type)
    {
        T result = null;
        for (Object aHandlList : handlList)
        {
            ClassTypeHandler classTypeHandler = (ClassTypeHandler) aHandlList;
            result = classTypeHandler.createInstance(type);
            if (result != null)
            {
                break;
            }
        }
        return result;
    }

    /**
     * Provide the default handler chain, basically for running checks on what to do if we've got an Enum, etc.
     * @return populated default handler chain
     */
    public static ClassTypeHandlerChain defaultTypeChain()
    {
        return new ClassTypeHandlerChain(new ArrayList<ClassTypeHandler>(
                Arrays.asList(new ClassTypeHandler[] { new EnumTypeHandler() })));
    }

}
