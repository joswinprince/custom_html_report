package com.test.automation;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
       // new HtmlTemplateGenerator();
        ClassicTemplate myReport =  new ClassicTemplate();
        myReport.setTitle("Report_sandbox");
        myReport.generateReport();
      
    }
}
