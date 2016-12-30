package com.example.testcase;

import junit.framework.TestSuite;

public class ExampleSuite extends TestSuite {
	
    public ExampleSuite() {
        addTestSuite( MathTest.class );
    }
}
