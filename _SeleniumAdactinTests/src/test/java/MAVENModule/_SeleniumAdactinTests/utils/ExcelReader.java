
package MAVENModule._SeleniumAdactinTests.utils ;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public FileInputStream fis =null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	String path = null;

	//constructor
	public ExcelReader(String filePath) throws FileNotFoundException, IOException {
		//path = System.getProperty("user.dir") + filePath;
		fis = new FileInputStream(filePath);

		//points to the workbook objec
		workbook = new XSSFWorkbook(fis);

		//now point to the first workbook sheet
		sheet = workbook.getSheetAt(0);
	}

	//note: need to shift left after deleting rows or columns to get changes to take effect                  
	public int getSheetRows(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		return (sheet.getLastRowNum() + 1);
	}
	
	//provides the total number of columns in a sheet - test case
	public int getSheetColumns(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(0);
		return(row.getLastCellNum());
	}

	//Provide cell value - testdata
	public String getCellData(String sheetName, int colNum, int rowNum) throws Exception {
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		return(cell.getStringCellValue());
	}

	//Provide cell value - testdata
	public String getCellData(String sheetName, String colName, int rowNum){
		int colNum =-1;
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		for(int i=0; i<getSheetColumns(sheetName); i++){
			row = sheet.getRow(0);
			cell = row.getCell(i);
			if(cell.getStringCellValue().equals(colName)){
				colNum = cell.getColumnIndex();
				break;
			}
		}
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		return(cell.getStringCellValue());
	}

	public void setCellData(String sheetName, int colNum, int rowNum, String str){

		try {
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			
			row = sheet.getRow(rowNum);
			cell = row.createCell(colNum);//problem was here! NullPointerException
			
			cell.setCellValue(str);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		}  
		catch(NullPointerException e){
			e.getMessage();
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
