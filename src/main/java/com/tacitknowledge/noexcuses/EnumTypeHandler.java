package com.tacitknowledge.noexcuses;

import org.apache.commons.lang.ArrayUtils;

/**
 * Originally created: Nov 1, 2006
 */
public class EnumTypeHandler implements ClassTypeHandler {

	@SuppressWarnings("unchecked")
	public <T> T createInstance(Class<T> type) {
		T instance = null;
		if (type.isEnum()) {
			Object[] enums = type.getEnumConstants();
			if (ArrayUtils.isNotEmpty(enums)) {
				instance = (T) enums[0];
			}
		}
		return instance;
	}
}
