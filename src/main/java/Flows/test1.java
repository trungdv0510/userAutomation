package Flows;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Actions.test1Actions;
import readExcel.dataMap;
import readExcel.excelFile;
import utils.contains;
import utils.setup;

public class test1 extends setup{
	private test1Actions test1 = new test1Actions();
	@Test(dataProvider = "datatest1", priority = 1)
	public void test1s(String No,String Testname, String search) {
		int row = Integer.parseInt(No);
		try {
			//driver.manage().window().fullscreen();
			assertTrue(test1.Login(driver, search));
			excelFile.setPass(dataMap.test1.Result.ordinal(), row, dataMap.test1.Error.ordinal());
		} catch (AssertionError e) {
			// TODO: handle exception
			System.err.println("Lỗi");
			excelFile.setFail(dataMap.test1.Result.ordinal(), row, dataMap.test1.Error.ordinal());
			assertTrue(false);
		}
	}
	
	@DataProvider(name = "datatest1")
	public static Object[][] getData(ITestContext context) {
		Map<String, String> testParams = context.getCurrentXmlTest().getLocalParameters();
		String testData = testParams.get("exelData");
		System.out.println(testData);
		excelFile.getSetUp(testData);

		excelFile.setExcelInfo(contains.dataFolder + contains.fileExcelName, contains.sheetName);
		Object[][] reArr = excelFile.getData(contains.testCaseName,
				dataMap.test1.Result.ordinal(),
				dataMap.test1.Testname.ordinal());
		return (reArr);
		}
	}