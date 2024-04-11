package com.dev.sandbox;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

@FunctionalInterface
interface TestableInstruction {
    void execute();
}
public class MainTest {
	
	protected PlayWrightFactory pf;
	protected  Properties prop;
	protected  Page page;
	@BeforeSuite
	public void preSuiteExecution() {
		
        System.out.println("@BeforeSuite setup method executed");
	}
	@BeforeTest
	
	public void preTest() {
		 // This method will be executed before any test method in the <test> tag
        // Perform setup tasks here for all <test> tags configured in xml
		
		// Initialise Playwright 
		pf = new PlayWrightFactory();
		prop = pf.init_prop();
		page = pf.initDriver(prop);
		
        System.out.println("BeforeTest setup method executed");
	}
	@BeforeClass
	public void beforeClass() {
		// This method will be executed before any test method in this class
        // Perform setup tasks here regardless of <test> tags in xml
        System.out.println("BeforeClass setup method executed");
	}
	@BeforeMethod
	public void beforeMethod() {
		// Initialize test data for Method
		 System.out.println("Before Method Executed");
	}
	
	@Test
	public void myTest() {
		String url = "https://app.smartsheet.com/b/form/663839d2b612461786a937715cecea4d";
		page.navigate(url);
		page.waitForURL(url);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.waitForTimeout(3500);
		//assert 2 + 2 == 5 : "Sum is not equal to 5";
		executeTest(() -> Assert.assertEquals(2 + 2, 5, "Sum is not equal to 5"));
		System.out.println("Test method  executed");
		 
		 
	}
	
	@AfterMethod
	public void afterMethod()
	{
		 System.out.println("After method  executed");
	}
	
	@AfterClass
	public void afterClass() {
		 System.out.println("After class executed");
	}
	@AfterTest
	public void postTest() {
		page.context().browser().close();
		page.close();
		 System.out.println("After Test execution");
	}
	
	
	@AfterSuite
	public void postSuiteExecution() {
		
	}
	
	public void executeTest(TestableInstruction instruction) {
        try {
            // Execute the testable instruction
            instruction.execute();
        } catch (AssertionError e) {
            // Capture the assertion error here
            System.out.println("Assertion failed: " + e.getMessage());
            // Handle the assertion failure as needed
        }
    }
	
	public void tearDown() {
		page.context().browser().close();
		page.close();
	}
	  public void testMethod() {
	        try {
	            Assert.assertEquals(2 + 2, 5, "Sum is not equal to 5");
	        } catch (AssertionError e) {
	            // Capture the assertion error here
	            System.out.println("Assertion failed: " + e.getMessage());
	        }
	    }
	
}
