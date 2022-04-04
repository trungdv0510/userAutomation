package API;

import java.util.HashMap;

public class MapHashMap {
	public static HashMap<String, String> testSuiteMap(String uuid,String suiteName,
			String dateRun,String runTime,String testcasePass,String testcaseFail,String testlogSum,
			String IpName,String hostName){
		HashMap<String, String> testSuite = new HashMap<String, String>();
		try {
			testSuite.put(tableDB.testSuite.uuid.toString(), uuid);
			testSuite.put(tableDB.testSuite.suiteName.toString(), suiteName);
			testSuite.put(tableDB.testSuite.dateRun.toString(), dateRun);
			testSuite.put(tableDB.testSuite.runTime.toString(), runTime);
			testSuite.put(tableDB.testSuite.testcasePass.toString(), testcasePass);
			testSuite.put(tableDB.testSuite.testcaseFail.toString(), testcaseFail);
			testSuite.put(tableDB.testSuite.testlogSum.toString(), testlogSum);
			testSuite.put(tableDB.testSuite.IpName.toString(), IpName);
			testSuite.put(tableDB.testSuite.hostName.toString(), hostName);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testSuite;
	}
	
	public static HashMap<String, String> testCaseMap(String uuid,String testName,String methodName,String author,
			String suiteUUID,String startTime,String endTime,String timeDuration){
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
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testCase;
	}
	
	public static HashMap<String, String> testLogMap(String uuid,String testcaseuuid,String stepName,String detail,
			String testLogTime,String imgPath,String videoPath){
		HashMap<String, String> testCase = new HashMap<String, String>();
		try {
			testCase.put(tableDB.testlog.uuid.toString(), uuid);
			testCase.put(tableDB.testlog.testcaseuuid.toString(), testcaseuuid);
			testCase.put(tableDB.testlog.stepName.toString(), stepName);
			testCase.put(tableDB.testlog.detail.toString(), detail);
			testCase.put(tableDB.testlog.testLogTime.toString(), testLogTime);
			testCase.put(tableDB.testlog.imgPath.toString(), imgPath);
			testCase.put(tableDB.testlog.videoPath.toString(), videoPath);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testCase;
	}
}
