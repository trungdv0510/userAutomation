package API;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.testng.ITestContext;
import org.testng.ITestResult;

import utils.contains;
import utils.convert;
import utils.setup;

public class InsertToServer extends setup{
	public static HashMap<String, String> testSuiteAPI;
	public static HashMap<String, String> testCaseAPI;
	public static HashMap<String, String> testLogAPI;
	public static List<HashMap<String, String>> listTestLog = new ArrayList<HashMap<String,String>>();
	public static List<HashMap<String, String>> listTestCase = new ArrayList<HashMap<String,String>>();
	public static void testSuite() {
		try {
			String suiteUUID = SuiteUUID.toString();
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
			String TestcaseStatus = "";
			System.out.println(testLogs.getTest().getStatus().toString());
			System.out.println(result.getStatus());
			if (result.getStatus() == ITestResult.FAILURE) {
				TestcaseStatus = "error";
			}
			else if(result.getStatus() == ITestResult.SUCCESS){
				TestcaseStatus = "pass";
			}
			else{
				TestcaseStatus = "skip";
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
	
	public static void insertTestSuite() {
		if (okHttpApi.insert(testSuiteAPI, contains.url+contains.ApiTestSuite)) {
			System.out.println("Insert testSuite to server sucess");
		}
		else {
			System.out.println("Insert testSuite to server fail");
		};
	}
	public static void insertTestCase() {
		for (HashMap<String, String> hashMap : listTestCase) {
			if (okHttpApi.insert(hashMap, contains.url+contains.ApiTestCase)) {
				System.out.println("Insert testcase to server sucess");
			}else {
				System.out.println("Insert testcase to server fail");
			}
		}
	}
	public static void insertTestLog() {
		for (HashMap<String, String> hashMap : listTestLog) {
			if (okHttpApi.insert(hashMap, contains.url+contains.ApiTestLog)) {
				System.out.println("Insert testLog to server sucess");
			}else {
				System.out.println("Insert testLog to server fail");
			}
		}
	}
}
