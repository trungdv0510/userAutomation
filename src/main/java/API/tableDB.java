package API;

public class tableDB {
	public enum testSuite{uuid,suiteName,dateRun,runTime,testcasePass,testcaseFail,testlogSum,IpName,hostName}
	public enum testcase{uuid,testName,methodName,author,suiteUUID,startTime,endTime,timeDuration}
	public enum testlog{uuid,testcaseuuid,stepName,detail,testLogTime,imgPath,videoPath}
}