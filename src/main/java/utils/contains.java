package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;

public class contains {
	public static String dataFolder = "data/";
	public static String fileExcelName = "";
	public static String sheetName = "";
	public static String testCaseName = "";
	public static String pass = "PASS";
	public static String fail = "FAIL";
	public static int TIME_WAITING = 30;
	public static String folderReprotLocation = "reports/" + getCurrentDateTime("yyyyMMdd-HHmmss") + "/";
	public static String folderImgReport = "imgReport/";
	public static String folderVideoReport = "videoReport/";
	// cấu hình url cho insert API
	public static String url = "https://locallhost:8080/";
	public static String ApiTestSuite = "api/testsuite/add";
	public static String ApiTestCase = "api/testcase/add";
	public static String ApiTestCases = "api/testcases/add";
	public static String ApiTestLog = "api/testlog/add";
	public static String ApiTestLogs = "api/testlogs/add";
	// cấu hình cho API thư viện OKHttp
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	public static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
	public static final MediaType MEDIA_TYPE_VIDEO = MediaType.parse("video/mp4");

	// Current time
	public static String getCurrentDateTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date timenow = new Date();
		String strtimenow = sdf.format(timenow);
		return strtimenow;
	}

	public static String getCurrentTime2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Date timenow = new Date();
		String strtimenow = sdf.format(timenow);
		return strtimenow;
	}

	public static String getCurrentDay2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date timenow = new Date();
		String strtimenow = sdf.format(timenow);
		return strtimenow;
	}

	public static int rowNumber(String no) {
		return Integer.valueOf(no);
	} 
	// sử dụng cho phần report
		public static String pattern = "yyyy-MM-dd HH:mm:ss";
		public static SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
		public static String patternMilliS = "SSSS";
		public static SimpleDateFormat formatMillisecond = new SimpleDateFormat(patternMilliS);
		public static SimpleDateFormat formateTimeStamp = new SimpleDateFormat("HH:mm:ss");
		public static String timeDateReport(Date date) {
			return formatDate.format(date);
		}
}
