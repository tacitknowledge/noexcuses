package com.tacitknowledge.noexcuses;

/**
 *  Originally created: Oct 31, 2006
 */
public class DummyObject
{
    private String something;

    private String another;

    private boolean trythis;

    private EmptyEnum emptyEnum;

    private SampleEnum sampleEnum;

    private SampleClass sampleClass;

    public DummyObject(String something, String another)
    {
        this.something = something;
        this.another = another;
    }

    public DummyObject(String something, String another, boolean trythis)
    {
        this.something = something;
        this.another = another;
        this.trythis = trythis;
    }

    public DummyObject(EmptyEnum emptyEnum, SampleEnum sampleEnum, SampleClass sampleClass)
    {
        this.emptyEnum = emptyEnum;
        this.sampleEnum = sampleEnum;
        this.sampleClass = sampleClass;
    }

    public String getSomething()
    {
        return something;
    }

    public void setSomething(String something)
    {
        this.something = something;
    }

    public String getAnother()
    {
        return another;
    }

    public boolean isTrythis()
    {
        return trythis;
    }

    public String attempt(String test)
    {
        return test.substring(3);
    }

    public void throwAnException()
    {
        throw new RuntimeException("sample runtime exception");
    }

    enum EmptyEnum
    {}

    enum SampleEnum
    {
        FIRST, SECOND
    }

    class SampleClass
    {}
}
