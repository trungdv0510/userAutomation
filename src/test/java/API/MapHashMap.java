package API;

import java.util.HashMap;

public class MapHashMap {
	public static HashMap<String, String> testSuiteMap(String uuid,String suiteName,
			String dateRun,String runTime,String testcasePass,String testcaseFail,String testlogSum,
			String IpName,String hostName, String result){
		HashMap<String, String> testSuite = new HashMap<>();
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
		HashMap<String, String> testCase = new HashMap<>();
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
		HashMap<String, String> testLog = new HashMap<>();
		try {
			testLog.put(tableDB.testLog.uuid.toString(), uuid);
			testLog.put(tableDB.testLog.testcaseUUID.toString(), testcaseuuid);
			testLog.put(tableDB.testLog.stepName.toString(), stepName);
			testLog.put(tableDB.testLog.detail.toString(), detail);
			testLog.put(tableDB.testLog.testLogTime.toString(), testLogTime);
			testLog.put(tableDB.testLog.result.toString(), result);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return testLog;
	}

	public static HashMap<String,String> regressionTestMap(String testcaseName,String evidenceLink, String result, String author, String ErrorDescription, String sprint, String testsuiteUuid){
		HashMap<String,String> regression = new HashMap<>();
		try {
			regression.put(tableDB.regressionTest.testcaseName.toString(),testcaseName);
			regression.put(tableDB.regressionTest.evidenceLink.toString(),evidenceLink);
			regression.put(tableDB.regressionTest.result.toString(),result);
			regression.put(tableDB.regressionTest.author.toString(),author);
			regression.put(tableDB.regressionTest.errorDescription.toString(),ErrorDescription);
			regression.put(tableDB.regressionTest.sprint.toString(),sprint);
			regression.put(tableDB.regressionTest.testsuiteUuid.toString(),testsuiteUuid);
		}catch (Exception e){
			System.err.println(e.getMessage());
		}
		return regression;
	}
}
