# noexcuses [![Build Status](https://secure.travis-ci.org/tacitknowledge/noexcuses.png?branch=master)](http://travis-ci.org/tacitknowledge/noexcuses)

noexcuses is a mockito extension created to make unit testing simple getters, setters, and constructors less tedious. 

## Why noexcuses?

Purists would say we need 100% coverage and all tests need to be solid.
On the other end of the spectrum, many teams settle for 70-80% coverage because writing tests for accessors/mutators and
constructors seem a waste of time. They consider them a waste of time because those portions of the codebase are extremely low risk
in terms of potential bugs. noexcuses is a way of finding a middle ground with an extra added benefit.
noexcuses works in combination with coverage tools like emma or cobertura.

Basically, using it filters out coverage gaps on low risk methods so that the only methods that appear on coverage reports are high risk methods.
This helps guide us in our craft. 

## Samples - use mockito and reflection to cover low risk methods
Getters and Setters:

    public void testGetterAndSetterConvenienceMethodWithExclusions() throws Exception {
        DummyObject object = new DummyObject();
        MethodTester methodTester = new MethodTester();
	//exclude certain methods with an exclusion array.
        methodTester.performGettersAndSetters(object,new String[]{"getSomething"});

    }

Methods:
```
    public void testAllMethodsWithGetPrefix() throws Exception {
        DummyObject object = new DummyObject();
        MethodTester methodTester = new MethodTester("get");
        methodTester.performTest(object);
    }

```
Constructors:
```
    public void testConstructors() throws Exception {
	//tries all consutrctors on DummyObject
        TestManager.testConstruction(DummyObject.class);
    }

```

## Start Using noexcuses

You can include noexcuses in your Maven project via:

```
<dependency>
	<groupId>com.tacitknowledge</groupId>
	<artifactId>noexcuses</artifactId>
	<version>1.1.0</version>
</dependency>
```

## Licensing

This framework is released under Apache 2.0 Public License.<br/>
The text of the license you can find at http://www.apache.org/licenses/LICENSE-2.0.txt.

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Added some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request
