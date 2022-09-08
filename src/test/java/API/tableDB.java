package API;

public class tableDB {
	public enum testSuite{uuid,suiteName,dateRun,runTime,testcasePass,testcaseFail,testlogSum,ipName,hostName,result}
	public enum testcase{uuid,testName,methodName,author,suiteUUID,startTime,endTime,timeDuration, result}
	public enum testLog{uuid,testcaseUUID,stepName,detail,testLogTime,result}
	public enum regressionTest{uuid,testcaseName,evidenceLink,result,author,ErrorDescription,sprint,testsuiteUuid}
}