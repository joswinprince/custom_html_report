package com.dev.sandbox;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import java.awt.Dimension;
import java.util.List;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class PlayWrightFactory {

	protected Browser browser;
	protected static BrowserContext context;
	protected static Page page;
	protected static Page pageOne;

	protected static Properties prop;
    
	protected static ThreadLocal<Page> threadLocalDriver = new ThreadLocal<>(); //For Parallel execution
	protected static ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();

    //Launches Browser as set by user in config file
	protected Page initDriver(Properties prop) {
        BrowserType browserType = null;
        boolean headless = false;
        String browserName = prop.getProperty("browser").trim();
        
        switch (browserName.toLowerCase()) {
            case "firefox":
                browserType = Playwright.create().firefox();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browserType = Playwright.create().chromium();
                browser = browserType.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
    

                break;
            case "webkit":
                browserType = Playwright.create().webkit();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
                
            default:
            	System.out.println("Please pass the reight browser name ..............");
            	break;
        }
        if (browserType == null) throw new IllegalArgumentException("Could not Launch Browser for type" + browserType);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));

        //Below line is used to start the trace file
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
        page = context.newPage();
        threadLocalDriver.set(page);
        threadLocalContext.set(context);
        return page;
    }

	protected static synchronized Page getPage() {
        return threadLocalDriver.get(); // Will return Initialized Thread Local Driver
    }

    protected static synchronized BrowserContext getContext() {
        return threadLocalContext.get(); // Will return Initialized Thread Local Context
    } 
    
    protected Page switch_browser() {
    	
    	List<Page> pages = context.pages();
    	for (Page pageOne : pages) {
    		
    	}
    	return pageOne;
    }
    
    /**
     * this method is to initialize the properties from the config file
     */
    protected Properties init_prop() {
    	
    	try {
    		FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
    		prop = new Properties();
    		prop.load(ip);
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return prop;
    }
}