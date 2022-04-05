package API;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
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
	public static List<HashMap<String, String>> listTestLog;
	public static List<HashMap<String, String>> listTestCase;
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
			String TestcaseName = ctx.getCurrentXmlTest().getName();
			String methodName = result.getMethod().getMethodName();
			String TestcaseAuthor = ctx.getCurrentXmlTest().getParameter("author");
			String TestcaseStartTime = contains.timeDateReport(testLogs.getTest().getStartedTime());
			String TestcaseEndTime = contains.timeDateReport(testLogs.getTest().getEndedTime());
			String TestcaseDuration = testLogs.getTest().getRunDuration();
			String TestcaseStatus = testLogs.getTest().getStatus().toString();
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
			String uuid = UUID.randomUUID().toString();
			String testcaseUUID = TestUUID.toString();
			String stepName = testLogs.getTest().getLogList().get(i).getStepName();
			String details = testLogs.getTest().getLogList().get(i).getDetails();
			if(stepName.contains("<img")) {
				stepName = stepName.replaceAll("<img", "<img width='60%'");
				stepName = stepName.replaceAll("data-featherlight", "target='_blank' data-featherlight");
			}
			String TestStepTime =contains.timeDateReport(testLogs.getTest().getLogList().get(i).getTimestamp());
			String TestStepStatus = testLogs.getTest().getLogList().get(i).getLogStatus().toString();
			MapHashMap.testLogMap(uuid, testcaseUUID, stepName, details, stepName, details, TestStepTime, TestStepStatus);
		}
	}
}
