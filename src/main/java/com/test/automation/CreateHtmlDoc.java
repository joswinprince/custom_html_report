package com.test.automation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateHtmlDoc {
    public static void main(String[] args) {
        // Create a new HTML document
        Document doc = new Document("");

        // Create a table element
        Element table = doc.createElement("table");

        // Add table rows
        for (int i = 1; i <= 5; i++) {
            Element row = doc.createElement("tr");
            row.appendElement("td").text("Row " + i + ", Column 1");
            row.appendElement("td").text("Row " + i + ", Column 2");
            table.appendChild(row);
        }

        // Append the table to the document body
        doc.body().appendChild(table);

        // Write the document to a file
        try {
            FileWriter writer = new FileWriter(new File(".//reports//output.html"));
            writer.write(doc.outerHtml());
            writer.close();
            System.out.println("HTML file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
