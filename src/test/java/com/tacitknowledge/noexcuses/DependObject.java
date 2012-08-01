package com.tacitknowledge.noexcuses;

/**
 *  Originally created: Oct 31, 2006
 */
public class DependObject {
    private DummyObject myTestObject;
    private MyEnum type;

    public DependObject(DummyObject myTestObject, MyEnum type) {
        this.myTestObject = myTestObject;
        this.type = type;
    }

    public DependObject(DummyObject myTestObject) {
        this.myTestObject = myTestObject;
    }

    public DummyObject getMyTestObject() {
        return myTestObject;
    }

    public MyEnum getType() {
        return type;
    }
}
