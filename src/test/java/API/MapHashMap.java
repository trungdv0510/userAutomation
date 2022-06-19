package API;

import java.util.HashMap;

public class MapHashMap {
	public static HashMap<String, String> testSuiteMap(String uuid,String suiteName,
			String dateRun,String runTime,String testcasePass,String testcaseFail,String testlogSum,
			String IpName,String hostName, String result){
		HashMap<String, String> testSuite = new HashMap<String, String>();
		try {
			testSuite.put(tableDB.testSuite.uuid.toString(), uuid);
			testSuite.put(tableDB.testSuite.suiteName.toString(), suiteName);
			testSuite.put(tableDB.testSuite.dateRun.toString(), dateRun);
			testSuite.put(tableDB.testSuite.runTime.toString(), runTime);
			testSuite.put(tableDB.testSuite.testcasePass.toString(), testcasePass);
			testSuite.put(tableDB.testSuite.testcaseFail.toString(), testcaseFail);
			testSuite.put(tableDB.testSuite.testlogSum.toString(), testlogSum);
			testSuite.put(tableDB.testSuite.ipName.toString(), IpName);
			testSuite.put(tableDB.testSuite.hostName.toString(), hostName);
			testSuite.put(tableDB.testSuite.result.toString(), result);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testSuite;
	}
	
	public static HashMap<String, String> testCaseMap(String uuid,String testName,String methodName,String author,
			String suiteUUID,String startTime,String endTime,String timeDuration, String result){
		HashMap<String, String> testCase = new HashMap<String, String>();
		try {
			testCase.put(tableDB.testcase.uuid.toString(), uuid);
			testCase.put(tableDB.testcase.testName.toString(), testName);
			testCase.put(tableDB.testcase.methodName.toString(), methodName);
			testCase.put(tableDB.testcase.author.toString(), author);
			testCase.put(tableDB.testcase.suiteUUID.toString(), suiteUUID);
			testCase.put(tableDB.testcase.startTime.toString(), startTime);
			testCase.put(tableDB.testcase.endTime.toString(), endTime);
			testCase.put(tableDB.testcase.timeDuration.toString(), timeDuration);
			testCase.put(tableDB.testcase.result.toString(), result);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testCase;
	}
	
	public static HashMap<String, String> testLogMap(String uuid,String testcaseuuid,String stepName,String detail,
			String testLogTime, String result){
		HashMap<String, String> testLog = new HashMap<String, String>();
		try {
			testLog.put(tableDB.testlog.uuid.toString(), uuid);
			testLog.put(tableDB.testlog.testcaseUUID.toString(), testcaseuuid);
			testLog.put(tableDB.testlog.stepName.toString(), stepName);
			testLog.put(tableDB.testlog.detail.toString(), detail);
			testLog.put(tableDB.testlog.testLogTime.toString(), testLogTime);
			testLog.put(tableDB.testlog.result.toString(), result);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testLog;
	}
}
