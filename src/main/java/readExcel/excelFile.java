package readExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.contains;

public class excelFile {
	private static XSSFWorkbook workBook;
	private static XSSFSheet sheetName;
	private static XSSFRow row;
	private static XSSFCell cell;
	
	public static void getSetUp(String excelFilePath) {
		String[] excelSetUp = excelFilePath.split(">");
		contains.fileExcelName = excelSetUp[0].trim();
		contains.sheetName = excelSetUp[1].trim();
		contains.testCaseName = excelSetUp[2].trim();
	}
	public static void setExcelInfo(String file, String sheet) {
		try {
			//File fileExcel = new File(file);
			FileInputStream excelFile = new FileInputStream(file);
			workBook = new XSSFWorkbook(excelFile);
			sheetName = workBook.getSheet(sheet);
			cell = sheetName.getRow(0).getCell(0);
			//System.out.println(String.valueOf(cell.getStringCellValue()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String getCellData(int rowNum, int colNum) {
		String cellData = null;
		try {
			cell = sheetName.getRow(rowNum).getCell(colNum);
			if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				Object object = cell.getRawValue();
				cellData = String.valueOf(object);
			}
			else {
				cellData = cell.getStringCellValue();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
			cellData = "";
		}
		return cellData;
	}
	public static int getLastRowIndex() {
		return sheetName.getLastRowNum();
	}
	public static ArrayList<Integer> getRowByTCNameIndex(String tcName,int colIndex){
		int lastRow = getLastRowIndex();
		ArrayList<Integer> listRow = new ArrayList<Integer>();
		for (int i = 0; i <= lastRow; i++) {
			if (getCellData(i,colIndex).contains(tcName)) {
				listRow.add(i);
			}
		}
		return listRow;
	}
	public static String[][] getData(String testcase, int totalCol,int colIndex){
		String[][] data = null;
		ArrayList<Integer> allRows = getRowByTCNameIndex(testcase, colIndex);
		try {
			data = new String[allRows.size()][totalCol];
			for (int i = 0; i < allRows.size(); i++) {
				data[i][0] = allRows.get(i).toString();
				for(int j = 0; j< totalCol ; j++) {
					data[i][j] = getCellData(allRows.get(i), j);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return data;
	}
	public static void setCellDataResult(int colIndex,int rowIndex, String fileName, String result) {
		try {
			row = sheetName.getRow(rowIndex);
			cell = row.getCell(colIndex);
			CellStyle style = workBook.createCellStyle();
			XSSFFont font = workBook.createFont();
			font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
			font.setBold(true);
			font.setColor(IndexedColors.WHITE.getIndex());
			if (result.equals(contains.fail)) {
				style.setFillBackgroundColor(IndexedColors.RED1.getIndex());
				style.setFillPattern(FillPatternType.LESS_DOTS);
			}
			else if (result.equals(contains.pass)) {
				style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.LESS_DOTS);
			}
			style.setBorderBottom(BorderStyle.MEDIUM);
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			if (cell == null) {
				cell = row.createCell(colIndex);
				cell.setCellStyle(style);
				cell.setCellValue(result);
			}
			else {
				cell.setCellStyle(style);
				cell.setCellValue(result);
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			workBook.write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		
	}
	public static void setPass(int colPass, int row, int colFail) {
		int i = colPass;
		while (true) {
			System.out.println(i +"---------"+getCellData(row, i));
			if (!getCellData(row, i).contains(contains.pass) && !getCellData(row, i).contains(contains.fail)) {
				setCellDataResult(i,row,contains.dataFolder+contains.fileExcelName,contains.pass);
				break;
			}
			i++;
		}
	}
	public static void setFail(int colPass, int row, int colFail) {
		int i = colPass;
		while (true) {
			System.out.println(i +"---------"+getCellData(row, i));
			if (!getCellData(row, i).contains(contains.pass) && !getCellData(row, i).contains(contains.fail)) {
				setCellDataResult(i,row,contains.dataFolder+contains.fileExcelName,contains.fail);
				break;
			}
			i++;
		}
	}
}
