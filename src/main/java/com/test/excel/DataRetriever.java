package com.test.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.test.automation.HtmlTemplateGenerator;

public final class DataRetriever {
	
	public static Sheet sheet;
	public static int index ;
	public static String[] columnNames;
	public static int columnCount;
	public String[] rowData;
	ExcelUtil excel = new ExcelUtil();
	
	public DataRetriever() throws Exception 
	{
		sheet = excel.setExcelFile("./src/test/resources/Data.xlsx","Test");
		Row firstRow = sheet.getRow(0);
		index = sheet.getPhysicalNumberOfRows();
		System.out.println(index);
		columnCount = firstRow.getLastCellNum();
		
		// Get First Row Data
				columnNames = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
				    Cell cell = firstRow.getCell(i);
				    if (cell != null) {
				        columnNames[i] = cell.getStringCellValue();
				        System.out.println(columnNames[i]);
				    } else {
				        columnNames[i] = ""; // or some default value if the cell is empty
				    }
				}
		
	}
	
	public String[] retriever(int rowDataIndex) throws Exception{
		
		Map<String, String> testData = new HashMap<>();
		
		
		
		
		/*
		 * // Print the column names to verify
		 * 
		 * for (String columnName : columnNames) { testData.put(columnName,
		 * excel.getCellData( rowDataIndex,columnName));
		 * 
		 * 
		 * // Get Row Data rowData = new String[columnCount]; Row dynamicRow =
		 * sheet.getRow(0); for (int i = 0; i < columnCount; i++) { Cell cell =
		 * dynamicRow.getCell(i); if (cell != null) { rowData[i] =
		 * excel.getCellData(rowDataIndex,columnName); System.out.println(rowData[i]); }
		 * else { rowData[i] = ""; // or some default value if the cell is empty } } }
		 */
		
		// Get Next Row Data
		
			Row dynamicRow = sheet.getRow(0);
				rowData = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
				    Cell cell = dynamicRow.getCell(i);
				    if (cell != null) {
				        columnNames[i] = cell.getStringCellValue();
				        //System.out.println(columnNames[i]);
				    } else {
				        columnNames[i] = ""; // or some default value if the cell is empty
				    }
				
	}
				System.out.println("RowData"+rowData);
		return rowData;
	}
	
	
}