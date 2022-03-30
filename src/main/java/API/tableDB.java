package API;

public class tableDB {
	enum testSuite{uuid,suiteName,dateRun,runTime,testcasePass,testcaseFail,methodPass,methodFail}
	enum testcase{uuid,testName,methodName,author,suiteUUID,startTime,endTime,timeDuration}
	enum testlog{uuid,testcaseuuid,stepName,detail,testLogTime,imgPath,videoPath}
}