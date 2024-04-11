package com.test.automation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.test.excel.DataRetriever;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlTemplateGenerator {
	
	 private static void appendRow(Element parent, String columnData[]) {
	        Element row = parent.appendElement("tr");
	        for(int i=0;i<columnData.length; i++)
	        {
	        	row.appendElement("td").text(columnData[i]);
	        }
	    }
    public HtmlTemplateGenerator() throws Exception {
        // Create a new HTML document
        Document doc = new Document("");

        // Create HTML structure
        Element html = doc.appendElement("html");
        Element head = html.appendElement("head");
        head.appendElement("meta").attr("charset", "UTF-8");
        head.appendElement("meta").attr("name", "viewport").attr("content", "width=device-width, initial-scale=1.0");
        head.appendElement("title").text("{{title}}");

        // Add CSS style
        Element style = head.appendElement("style");
        style.appendText("body { font-family: Arial, sans-serif; }" +
                         "table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }" +
                         "th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }" +
                         "th { background-color: #f2f2f2; }" +
                         "tr:nth-child(even) { background-color: #f2f2f2; }" +
                         "tr:hover { background-color: #ddd; }");

        // Create body
        Element body = html.appendElement("body");
        body.appendElement("h1").text("{{reportTitle}}");
        Element table = body.appendElement("table");
        //Retrieve Data from excel
        DataRetriever dataRetriever = new DataRetriever();
        dataRetriever.retriever(1);
        
        String columnNames[] = DataRetriever.columnNames;;
        String tableHeadings="";
        for(int names=0;names<columnNames.length; names++)
        {
        	tableHeadings +="<th>"+columnNames[names]+"</th>"; 
        }
        
        int front = 1;
        int rear = DataRetriever.index;
			// Add Data
        	for (front =1;front <= rear; front++)
        	{
	        	try {
	        		// Add Row Data
					appendRow(table, dataRetriever.retriever(1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        
		table.appendElement("thead").html("<tr>"+tableHeadings+"</tr>");
        table.appendElement("tbody").html("{{tableRows}}");

        // Write the document to a file
        try {
        	  FileWriter writer = new FileWriter(new File(".//reports//template.html"));
              writer.write(doc.outerHtml());
              writer.close();

            System.out.println("HTML template file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
