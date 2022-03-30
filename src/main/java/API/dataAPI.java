package API;

import java.util.HashMap;

public class dataAPI {
	public static HashMap<String, String> testSuiteAPI(String UUID, String SuiteName, 
			String dateRun, String runTime, String testcasePass, String testcaseFail,
			String methodPass, String methodFail){
		HashMap<String, String> testsuite = new HashMap<String, String>();
		try {
			testsuite.put(tableDB.testSuite.uuid.toString(), UUID);
			testsuite.put(tableDB.testSuite.suiteName.toString(), dateRun);
			testsuite.put(tableDB.testSuite.dateRun.toString(), methodFail);
			testsuite.put(tableDB.testSuite.runTime.toString(), runTime);
			testsuite.put(tableDB.testSuite.testcasePass.toString(), testcasePass);
			testsuite.put(tableDB.testSuite.testcaseFail.toString(), testcaseFail);
			testsuite.put(tableDB.testSuite.methodPass.toString(), methodPass);
			testsuite.put(tableDB.testSuite.methodFail.toString(), methodFail);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testsuite;
	}
	public static HashMap<String, String> testcaseAPI(String UUID,String testName,String methodName, 
			String author, String suiteUUID, String startTime, String endTime, String timeDuration){
		HashMap<String, String> testcase = new HashMap<String, String>();
		try {
			testcase.put(tableDB.testcase.uuid.toString(), UUID);
			testcase.put(tableDB.testcase.testName.toString(), testName);
			testcase.put(tableDB.testcase.methodName.toString(), methodName);
			testcase.put(tableDB.testcase.author.toString(), author);
			testcase.put(tableDB.testcase.suiteUUID.toString(), suiteUUID);
			testcase.put(tableDB.testcase.startTime.toString(), startTime);
			testcase.put(tableDB.testcase.endTime.toString(), endTime);
			testcase.put(tableDB.testcase.timeDuration.toString(), timeDuration);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testcase;
	}
	public static HashMap<String, String> testlogAPI(String UUID, String testcaseUUID, 
			String stepName, String detail, String testLogTime, String imgPath,
			String videoPath){
		HashMap<String, String> testlog = new HashMap<String, String>();
		try {
			testlog.put(tableDB.testlog.uuid.toString(), UUID);
			testlog.put(tableDB.testlog.testcaseuuid.toString(), testcaseUUID);
			testlog.put(tableDB.testlog.stepName.toString(), stepName);
			testlog.put(tableDB.testlog.detail.toString(), detail);
			testlog.put(tableDB.testlog.testLogTime.toString(), testLogTime);
			testlog.put(tableDB.testlog.imgPath.toString(), imgPath);
			testlog.put(tableDB.testlog.videoPath.toString(), videoPath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return testlog;
	}
}
