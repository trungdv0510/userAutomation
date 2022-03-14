package Flows;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Actions.regesterUserActions;
import readExcel.dataMap;
import readExcel.excelFile;
import utils.contains;
import utils.setup;

public class regesterUser extends setup{
	private regesterUserActions regester = new regesterUserActions();
	@Test(dataProvider = "regester", priority = 1)
	public void regesterUser(String No, String Testname, String username,
							String phoneNumber,String address,String password,
							String repeatPassword,String resultMessage) {
		int row = Integer.parseInt(No);
		try {
			assertTrue(regester.checkRegesterPage(driver));
			assertTrue(regester.createAnAccount(driver,username,phoneNumber,address,password,repeatPassword));
			assertTrue(regester.checkResult(driver,resultMessage));
			excelFile.setPass(dataMap.regester.Result.ordinal(), row, dataMap.regester.Error.ordinal());
		} catch (AssertionError e) {
			// TODO: handle exception
			excelFile.setFail(dataMap.regester.Result.ordinal(), row, dataMap.regester.Error.ordinal());
			assertTrue(false);
		}
	}
	@DataProvider(name = "regester")
	public static Object[][] getData(ITestContext context){
		Map<String, String> testParams = context.getCurrentXmlTest().getLocalParameters();
		String testData = testParams.get("exelData");
		System.out.println(testData);
		excelFile.getSetUp(testData);

		excelFile.setExcelInfo(contains.dataFolder + contains.fileExcelName, contains.sheetName);
		Object[][] reArr = excelFile.getData(contains.testCaseName,
				dataMap.regester.Result.ordinal(),
				dataMap.regester.Testname.ordinal());
		return (reArr);
	}
}
