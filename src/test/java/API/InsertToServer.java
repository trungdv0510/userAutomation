package API;

import org.apache.commons.lang3.StringUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import utils.contains;
import utils.convert;
import utils.setup;

import java.net.InetAddress;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InsertToServer extends setup{
	public static HashMap<String, String> testSuiteAPI;
	public static HashMap<String, String> testCaseAPI;
	public static HashMap<String, String> testLogAPI;
	public static HashMap<String,String> regressionApi;
	public static List<HashMap<String, String>> listTestLog = new ArrayList<>();
	public static List<HashMap<String, String>> listTestCase = new ArrayList<>();

	public static void testSuite() {
		try {
			String suiteUUID = SuiteUUID;
			String SuiteName = ctxLocal.getCurrentXmlTest().getSuite().getName();
			LocalDate date = LocalDate.now();
			String dateRun = date.toString();
			String runTimes = String.valueOf(runTime);
			String testcasePass = String.valueOf(totalPass);
			String testcaseFail = String.valueOf(totalFail);
			String testLogSum = String.valueOf(testlogSum);
			InetAddress ip = InetAddress.getLocalHost(); // Lấy IP
			String TestcaseHostname = ip.getHostName();
			String TestcaseIP = ip.getHostAddress();
			String result = contains.pass;
			if (totalFail>0) {
				result = contains.fail;
			}
			testSuiteAPI = MapHashMap.testSuiteMap(suiteUUID, SuiteName, dateRun, runTimes, 
					testcasePass, testcaseFail, testLogSum, TestcaseIP, TestcaseHostname,result);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static void testcase(ITestContext ctx, ITestResult result) {
		try {
			String TestcaseName = ctx.getCurrentXmlTest().getName()+" methods test: "+result.getMethod().getMethodName();
			String methodName = result.getMethod().getMethodName();
			String TestcaseAuthor = ctx.getCurrentXmlTest().getParameter("author");
			String TestcaseStartTime = contains.timeDateReport(testLogs.getTest().getStartedTime());
			String TestcaseEndTime = contains.timeDateReport(testLogs.getTest().getEndedTime());
			String TestcaseDuration = testLogs.getTest().getRunDuration();
			String TestcaseStatus = "skip";
			if (result.getStatus() == ITestResult.FAILURE) {
				TestcaseStatus = "error";
			}
			else if(result.getStatus() == ITestResult.SUCCESS){
				TestcaseStatus = "pass";
			}
			if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
				totalFail++;
			}
			else {
				totalPass++;
			}
			runTime += convert.convertTimeToInteger(TestcaseDuration);
			testCaseAPI =MapHashMap.testCaseMap(TestUUID.toString(), TestcaseName, methodName, 
					TestcaseAuthor, SuiteUUID.toString(), TestcaseStartTime, TestcaseEndTime, 
					TestcaseDuration, TestcaseStatus);
			listTestCase.add(testCaseAPI);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	public static void testLog() {
		for (int i = 0; i <  testLogs.getTest().getLogList().size(); i++) {
			if(!testLogs.getTest().getLogList().get(i).getStepName().contains("<img")) {
				String uuid = UUID.randomUUID().toString();
				String testcaseUUID = TestUUID.toString();
				String stepName = testLogs.getTest().getLogList().get(i).getStepName();
				String details = testLogs.getTest().getLogList().get(i).getDetails();
				String TestStepTime =contains.timeDateReport(testLogs.getTest().getLogList().get(i).getTimestamp());
				String TestStepStatus = testLogs.getTest().getLogList().get(i).getLogStatus().toString();
				testLogAPI =  MapHashMap.testLogMap(uuid, testcaseUUID, stepName, details, TestStepTime, TestStepStatus);
				listTestLog.add(testLogAPI);
			}
		}
	}
	public static void regressionTest(ITestContext ctx){
		System.out.println("regressionTest");
		String uuid = UUID.randomUUID().toString();
		String testcaseName = ctxLocal.getCurrentXmlTest().getSuite().getName();
		String author = ctx.getCurrentXmlTest().getParameter("author");
		String result = contains.pass;
		String errorDescription = "";
		if(contains.errorLog!=null){
			errorDescription =  contains.errorLog.toString();
		}
		String sprint = ctx.getCurrentXmlTest().getParameter("sprint");
		String evidence = contains.url+contains.ApiTestSuite+SuiteUUID;

		if (totalFail>0) {
			result = contains.fail;
		}
		regressionApi =  MapHashMap.regressionTestMap(uuid,testcaseName,evidence,result,author,errorDescription,sprint,SuiteUUID.toString());
	}
	public static void insertTestSuite() {
		if (okHttpApi.insert(testSuiteAPI, contains.url+contains.ApiTestSuite)) {
			System.out.println("Insert testSuite to server success");
		}
		else {
			System.out.println("Insert testSuite to server fail");
		}
		System.out.println("------End insert TestSuite log ----------");
    }
	public static void insertTestCase() {
		for (HashMap<String, String> hashMap : listTestCase) {
			if (okHttpApi.insert(hashMap, contains.url+contains.ApiTestCase)) {
				System.out.println("Insert testcase to server success");
			}else {
				System.out.println("Insert testcase to server fail");
			}
		}
		System.out.println("------End insert TestCase log ----------");
	}
	public static void insertTestLog() {
		for (HashMap<String, String> hashMap : listTestLog) {
			if (okHttpApi.insert(hashMap, contains.url+contains.ApiTestLog)) {
				System.out.println("Insert testLog to server success");
			}else {
				System.out.println("Insert testLog to server fail");
			}
		}
		System.out.println("------End insert test log ----------");
	}
	public static void insertRegression(){
		System.out.println(regressionApi);
		if (okHttpApi.insert(regressionApi, contains.url+contains.apiRegression)) {
			System.out.println("Insert regression to server success");
		}
		else {
			System.out.println("Insert regression to server fail");
		}
		System.out.println("------End insert Regression log ----------");
	}
}
