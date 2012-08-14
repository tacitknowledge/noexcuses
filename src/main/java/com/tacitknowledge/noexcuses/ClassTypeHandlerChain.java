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
    /**
     * 
     */
    private final List<ClassTypeHandler> handlList;

    /**
     * 
     * @param handlList
     */
    public ClassTypeHandlerChain(List<ClassTypeHandler> handlList)
    {
        this.handlList = handlList;
    }

    /**
     * 
     * @param type
     * @return
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
