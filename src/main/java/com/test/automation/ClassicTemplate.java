package com.test.automation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.test.excel.DataRetriever;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClassicTemplate {
	private Document doc;
	private Element head; 


	public static void main(String[] args) throws Exception {
		 ClassicTemplate myReport =  new ClassicTemplate("Report_sandbox","Data");
	        myReport.generateReport();
		
	}
	
	

	public static String getStyleFromFile() throws IOException
	{
		String style="";
		
		BufferedReader br = new BufferedReader(new FileReader("./src/test/resources/htmlTemplate/style.css"));
		String line;
		while((line=br.readLine())!=null)
		{
			style += line+"\r\n";
		}
		System.out.println(style);
		return style;
	}
	private static Element[] appendRowTR(Element parentTable) {
	        Element row[] = new Element[4];
	        for(int i=0;i<4; i++)
	        {
	        	row[i] = parentTable.appendElement("tr");
	        }
	        return row;
	    }
	/*
	 * This method gets tr element as input and adds td element 
	 */
	private static Element[] appendRowTD(Element[] tableRows, String columnData[]) {
		
		Element col[]= new Element[4];
		for (int j =0; j<tableRows.length;j++)
		{
			for(int i=0;i<columnData.length; i++)
			{
				col[j] = tableRows[j].appendElement("td").text(columnData[i]);
				if(i==2 ) {
					if(columnData[i].toLowerCase().equalsIgnoreCase("pass"))
					{
						addPassField(col[j]);
					}else {
						addFailField(col[j]);
						
					}
				}
			}
		}
		return col;
	}
	private static void addPassField(Element td) {	
			td
			.html("<i class=\"fa fa-check-circle-o green\"></i><span class=\"ms-1\">Pass</span>");
	}
	private static void addFailField(Element td) {	
		td
		.html("<i class=\"fa fa-dot-circle-o text-danger\"></i><span class=\"ms-1\">Fail</span>");
	}
    public ClassicTemplate(String title, String reportAbout) throws Exception {
        // Create a new HTML document
         doc = new Document("");

        // Create HTML structure
        Element html = doc.appendElement("html");
        head = html.appendElement("head");
        head.appendElement("meta").attr("charset", "UTF-8");
        head.appendElement("meta").attr("name", "viewport").attr("content", "width=device-width, initial-scale=1.0");
        head.appendElement("title").text(title);
        head.appendElement("link").attr("rel","stylesheet").attr("href","https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css");
        head.appendElement("link").attr("rel","stylesheet").attr("href","https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css");
        head.appendElement("link").attr("rel","stylesheet").attr("src","style.css");
        // Add CSS style
        //Element style = head.appendElement("style");
        //style.appendText(getStyleFromFile());

        // Create body
        Element body = html.appendElement("body");
        body.appendElement("script").attr("src","https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js");
        body.appendElement("script").attr("src","https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js");
        body.appendElement("h1").text(title);
        
        Element containerDiv = body.appendElement("div").attr("class","container mt-5 px-2");
        Element justifyDiv = containerDiv.appendElement("div").attr("class","mb-2 d-flex justify-content-between align-items-center");
        Element positionRelativeDiv= justifyDiv.appendElement("div").attr("class","position-relative");
              
        positionRelativeDiv.appendElement("span").attr("class","position-absolute search").html("<i class=\"fa fa-search\"></i>\r\n");
        positionRelativeDiv.appendElement("input").attr("class","form-control w-100").attr("placeholder","Search by order#, name...\">");
        
        Element positionRelativeDiv2 = justifyDiv.appendElement("div").attr("class", "px-2");
         positionRelativeDiv2.appendElement("span").attr("class", "px-2")
        		.html(" <span>Filters <i class=\"fa fa-angle-down\"></i></span>\r\n"
        				+ "            <i class=\"fa fa-ellipsis-h ms-3\"></i>");
        
        //Element table = mainDiv.appendElement("table");
        //Retrieve Data from excel
        
        String columnNames[] = {"# Step No","Date","Status","Req","Evidence"};
        String tableHeadings = "";
        String rowDatas = "";
        
        Element tableDiv = containerDiv.appendElement("div").attr("class", "table-responsive");
        Element table = tableDiv.appendElement("table").attr("class", "table table-responsive table-borderless");
        
        Element tableRowData[] = new Element[columnNames.length] ;
        
        for(int names=0;names<columnNames.length; names++)
        {
        	tableHeadings +="<th scope=\"col\" width=\"15%\">"+columnNames[names]+"</th>"; 
        	
        	
        }
        
        
        String columnData[] = {"col-1","col-2","Pass","col-4","col-5"};
//        
//        for(int names=0;names<columnNames.length; names++)
//        {
//        	rowDatas +="<td scope=\"row\" width=\"15%\">"+columnData[names]+"</th>"; 
//        	tableRowData[names]=table.appendTo(tbody).html("<td scope=\"row\" width=\"15%\">"+columnData[names]+"</th>");
//        }
//        
//        
//        appendRow(table, columnNames);
        
        
        
		table.appendElement("thead").html("<tr class=\"bg-light\">"+tableHeadings+"</tr>");
		
        table.appendElement("tbody").html(reportAbout);
        
        Element tableRows[] = appendRowTR(table);
        
        Element tableRowsData[] = appendRowTD(tableRows, columnData);
        // Write the document to a file
       
    }
    public void generateReport()
    {
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
