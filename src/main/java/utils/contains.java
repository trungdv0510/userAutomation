package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class contains {
	public static String dataFolder ="data/";
	public static String fileExcelName = "";
	public static String sheetName = "";
	public static String testCaseName = "";
	public static String pass = "PASS";
	public static String fail = "FAIL";
	public static int TIME_WAITING = 30;
	public static String folderReprotLocation = "reports/"+getCurrentDateTime("yyyyMMdd-HHmmss")+ "/";
	public static String folderImgReport="imgReport/";
	public static String folderVideoReport= "videoReport/";
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
}
