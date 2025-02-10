package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String path = ".\\testData\\LoginData.xlsx";
		ExcelUtilities xlutil = new ExcelUtilities(path);
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int colNums = xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][colNums];
		
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<colNums;j++) {
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}

}
