package com.tacitknowledge.noexcuses;

import com.tacitknowledge.noexcuses.MyEnum;
import com.tacitknowledge.noexcuses.DummyObject;

/*
 *
 * Originally created: Nov 16, 2006
 */
public class Pojo {
    private DummyObject myTestObject;
    private MyEnum type;

    public Pojo(DummyObject myTestObject, MyEnum type) {
        this.myTestObject = myTestObject;
        this.type = type;
    }

    public Pojo(DummyObject myTestObject) {
        this.myTestObject = myTestObject;
    }

	public Pojo() {}

	private Pojo(int priv) {}

	protected Pojo(long priv) {}

	public DummyObject getMyTestObject() {
        return myTestObject;
    }

    public MyEnum getType() {
        return type;
    }
}
