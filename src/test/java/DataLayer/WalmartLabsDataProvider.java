package DataLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.DataProvider;

public class WalmartLabsDataProvider {

	
	@DataProvider(name = "WalMart e-commerce Transaction")
	public static Object[][] walmartTestDataProvider() {

		// read data from excel
		
		Logger log = Logger.getLogger(WalmartLabsDataProvider.class);

		log.info("starting WalMart e-commerce end to end Transaction");

		String filePath = ClassLoader.getSystemResource("WalmartLabsTestdata.xls").getFile();

		File file = new File(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.error("file not found error is  " + e.getMessage());
			return new Object[][] {};
		}

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(fis);
		} catch (IOException e) {
			log.error("Unable to create work book  " + e.getMessage());
			return new Object[][] {};
		}

		HSSFSheet sheet = wb.getSheet("Sheet1");

		int totalNoOfRecords = sheet.getLastRowNum();

		// exclude heading
		Object[][] obj = new Object[totalNoOfRecords][3];

		for (int i = 1; i <= totalNoOfRecords; i++) {
			HSSFRow row = sheet.getRow(i);

			obj[i - 1][0] = row.getCell(0).getStringCellValue();
			obj[i - 1][1] = row.getCell(1).getStringCellValue();
			obj[i - 1][2] = row.getCell(2).getStringCellValue();
		}

		try {
			fis.close();
			wb.close();
		} catch (IOException e) {
			log.error("unable to close file input stream");
		}
		return obj;
	}	
}
