package com.test.mainlinetestvismc.utilities;

import java.io.IOException;
import java.util.Random;

public class GlobalMethods {
	
	public static Integer randomNumberGenerate() {
		Random rand = new Random();
		int value = rand.nextInt(20);
		return value;
	}
	
	@SuppressWarnings("null")
	public static String randomNameGenerate(int sizeofString) {
		char a[]= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		Random rand = new Random();
		
		
		StringBuilder s = new StringBuilder(sizeofString);
		  for (int i=0;i<sizeofString;i++) {
		    s.append(a[rand.nextInt(26)]);
		  }
		  return s.toString();
		

	}
	
	public static void updateName() throws IOException {
		ExcelReader excelReader = new ExcelReader(
				"C:/Software/Selenium_workspace/GladStoneWebDriverTest/src/test/java/com/test/mainlinetestvismc/repository/Test.xlsx");
	
		int num=randomNumberGenerate();
		excelReader.writeCellData("PatientDetail", 1, 0, randomNameGenerate(num));
		excelReader.writeCellData("PatientDetail", 1, 2, randomNameGenerate(num));
		excelReader.writeCellData("PatientDetail", 1, 4, "'01/01/19"+String.valueOf(num));
		excelReader.writeCellData("PatientDetail", 1, 22, "learntdevops+"+String.valueOf(num)+"@gmail.com");
		excelReader.writeCellData("PatientDetail", 1, 23, "learntdevops+"+String.valueOf(num+1)+"@gmail.com");
	}

}
