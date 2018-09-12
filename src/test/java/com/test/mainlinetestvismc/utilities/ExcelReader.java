package com.test.mainlinetestvismc.utilities;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	String path;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Row row;
	private Cell cell;
	private FileInputStream fis;
	private FileOutputStream fos;

	public ExcelReader(String path) throws IOException {
		this.path = path;
		try {
			fis = new FileInputStream(new File(path));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
			fos.close();
		}
	}

	public int getRowCount(String SheetName) {

		if (!isSheetExist(SheetName)) {
			return 0;
		}
		sheet = workbook.getSheet(SheetName);
		int RowNumber = sheet.getLastRowNum() + 1;
		return RowNumber;

	}

	public int getColumnCount(String SheetName) {
		if (!isSheetExist(SheetName)) {
			return 0;
		}
		sheet = workbook.getSheet(SheetName);
		row = sheet.getRow(0);
		if (row == null) {
			return 0;
		}
		int ColumnNumber = row.getLastCellNum();
		return ColumnNumber;
	}

	public boolean isSheetExist(String SheetName) {

		int index = workbook.getSheetIndex(SheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(SheetName.toUpperCase());
			if (index == -1) {
				return false;
			} else {
				return true;
			}
		}
		return true;

	}

	public String getCellData(String SheetName, int ro, String Col) {
		int column = -1;
		int index = workbook.getSheetIndex(SheetName);
		if (index == -1) {
			return "";
		}
		System.out.println(index);
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(0);
		if (row == null) {
			return "";
		}
		System.out.println(row.getLastCellNum());
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(Col.trim())) {
				column = i;
			}

		}
		System.out.println(column);
		if (column == -1) {
			return "";
		}
		sheet = workbook.getSheet(SheetName);
		row = sheet.getRow(ro);
		cell = row.getCell(column);
		if (cell == null) {
			return "";
		}
		if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			String str = String.valueOf(cell.getNumericCellValue());
			if (sheet.getRow(0).getCell(1).getStringCellValue().trim().equals("ZIP")) {
				str = str.substring(0, 5);
			}
			return str;
		}
		return String.valueOf(cell.getStringCellValue());
	}

	// Pass sheet name and row and column to access the data.
	@SuppressWarnings("static-access")
	public String getCellData(String SheetName, int ro, int column) {

		int index = workbook.getSheetIndex(SheetName);

		if (index == -1) {
			return "";
		}
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(ro);

		if (row == null) {
			return "";
		}
		cell = row.getCell(column);
		if (cell == null) {
			return "";
		}
		if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			String str = String.valueOf(cell.getNumericCellValue());
			System.out.println(str);
			if (sheet.getRow(0).getCell(1).getStringCellValue().trim().equals("ZIP")) {
				str = str.substring(0, 5);
			}
			return str;
		}
		return cell.getStringCellValue();
	}

	public void writeCellData(String SheetName, int ro, int column, String value) {

		int index = workbook.getSheetIndex(SheetName);

		if (index == -1) {
			return ;
		}
		sheet = workbook.getSheetAt(index);

		sheet.getRow(ro).createCell(column).setCellValue(value);
		try {
			fos = new FileOutputStream(new File(path));
			workbook.write(fos);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
