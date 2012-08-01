package com.tacitknowledge.noexcuses;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple chain for invoking each handler and returning the first to succeed in creating an instance.
 * 
 *  Originally created: Nov 1, 2006
 */
public class ClassTypeHandlerChain {
    private List<ClassTypeHandler> handlList;

    public ClassTypeHandlerChain(List<ClassTypeHandler> handlList) {
        this.handlList = handlList;
    }

    public void addHandler(ClassTypeHandler handler){
        if(handlList == null){
            handlList = new ArrayList<ClassTypeHandler>();
        }
    }

    public <T> T processChain(Class<T> type){
        T result = null;
        for (Object aHandlList : handlList) {
            ClassTypeHandler classTypeHandler = (ClassTypeHandler) aHandlList;
            result = classTypeHandler.createInstance(type);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * Provide the default handler chain, basically for running checks on what to do if we've got an Enum, etc.
     * @return populated default handler chain
     */
    public static ClassTypeHandlerChain defaultTypeChain(){
        return  new ClassTypeHandlerChain(
                new ArrayList<ClassTypeHandler>(Arrays.asList(new ClassTypeHandler[]{new EnumTypeHandler()})));
    }

}
