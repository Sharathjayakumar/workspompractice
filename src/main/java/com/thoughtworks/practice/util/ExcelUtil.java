package com.thoughtworks.practice.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {


	public static Object[][] getTestData(int sheetindex) throws Throwable {
		
		Object[][] data;
		FileInputStream file = new FileInputStream("/Users/sds-sarath.kj/Documents/eclipse-workspace/ThoughtWorksPracticeProject/"
				+ "src/main/java/com/thoughtworks/practice/testdata/TestDataSheet.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		
		data = new Object[sheet.getLastRowNum()+1][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<=lastRowNum;i++) {
			int lastCellNum = sheet.getRow(i).getLastCellNum();
			//data = new Object[sheet.getLastRowNum()][sheet.getRow(i).getLastCellNum()];
			for (int j=0;j<lastCellNum;j++) {
				int cellType = sheet.getRow(i).getCell(j).getCellType();
				if(cellType==0) {
					double b = sheet.getRow(i).getCell(j).getNumericCellValue();
					String s = b+"";
					int indexOfDot = s.indexOf(".");
					//String ss = s.substring(0, indexOfDot);
					data[i][j] = s.substring(0, indexOfDot);
				}
				else if(cellType==1) {
					sheet.getRow(i).getCell(j).getStringCellValue();
					data[i][j] =sheet.getRow(i).getCell(j).getStringCellValue();
				}
			}	
		}
		return data;
	}

	public static void main(String[] args) throws Throwable {
/*
		FileInputStream file = new FileInputStream("/Users/sds-sarath.kj/Documents/eclipse-workspace/ThoughtWorksPracticeProject/"
				+ "src/main/java/com/thoughtworks/practice/testdata/TestDataSheet.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();

		for(int i=0;i<=lastRowNum;i++) {

			int lastCellNum = sheet.getRow(i).getLastCellNum();

			for (int j=0;j<lastCellNum;j++) {

				int cellType = sheet.getRow(i).getCell(j).getCellType();

				if(cellType==0) {
					double b = sheet.getRow(i).getCell(j).getNumericCellValue();
					String s = b+"";
					int indexOfDot = s.indexOf(".");
					//String ss = s.substring(0, indexOfDot);
					System.out.println(s.substring(0, indexOfDot));
				}

				else if(cellType==1) {

					sheet.getRow(i).getCell(j).getStringCellValue();
					System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
				}


			}	
		}*/
		
		Object[][] data = getTestData(0);
		//System.out.println(data.toString());
		String s = Arrays.deepToString(data);
		System.out.println(s);

	}
}
