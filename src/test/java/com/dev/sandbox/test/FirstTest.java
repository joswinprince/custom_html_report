package com.dev.sandbox.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dev.sandbox.MainTest;

public class FirstTest extends MainTest  {
	@Test
	@Parameters({"TestCase_ID"})
public void runFirstTest(String id) {
	System.out.println("__Executing "+id);
}

}
