package com.tacitknowledge.noexcuses;

/**
 * Created by IntelliJ IDEA.
 * User: mshort
 * Date: Dec 21, 2006
 * Time: 9:22:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ParamBuilder
{
    Object[] createParams(Class<?>[] paramTypes);
}
