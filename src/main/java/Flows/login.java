package Flows;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Actions.loginActions;
import readExcel.dataMap;
import readExcel.excelFile;
import utils.contains;
import utils.setup;

public class login extends setup{
	private loginActions loginA = new loginActions();
	
	@Test(dataProvider = "loginA", priority = 1)
	public void regesterUser(String No, String Testname, String Username,String Password,String Alert) {
		int row = Integer.parseInt(No);
		try {
			assertTrue(loginA.login(driver,Username,Password));
			testLogs.log(LogStatus.PASS, "Pass","");
			excelFile.setPass(dataMap.login.Result.ordinal(), row, dataMap.login.Error.ordinal());
		} catch (AssertionError e) {
			// TODO: handle exception
			excelFile.setFail(dataMap.login.Result.ordinal(), row, dataMap.login.Error.ordinal());
			testLogs.log(LogStatus.ERROR, "regesterUser fail","");
			assertTrue(false);
		}
	}
	@DataProvider(name = "loginA")
	public static Object[][] getData(ITestContext context){
		Map<String, String> testParams = context.getCurrentXmlTest().getLocalParameters();
		String testData = testParams.get("exelData");
		System.out.println(testData);
		excelFile.getSetUp(testData);

		excelFile.setExcelInfo(contains.dataFolder + contains.fileExcelName, contains.sheetName);
		Object[][] reArr = excelFile.getData(contains.testCaseName,
				dataMap.login.Result.ordinal(),
				dataMap.login.Testname.ordinal());
		return (reArr);
	}
}
