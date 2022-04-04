package API;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.testng.ITestContext;
import org.testng.ITestResult;

import utils.setup;

public class InsertToServer extends setup{
	public static String pattern = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
	public static String patternMilliS = "SSSS";
	public static SimpleDateFormat formatMillisecond = new SimpleDateFormat(
			patternMilliS);
	public static SimpleDateFormat formateTimeStamp = new SimpleDateFormat(
			"HH:mm:ss");
	// ------------------------
	public static String timeDateReport(Date date) {
		return formatDate.format(date);
	}
	public static boolean instetToHashMap(ITestResult Iresult,ITestContext ctx, UUID testSuite, UUID testcase,UUID testLog, String imgPath, String videoPath) {
		boolean result = false;
		try {
			
			String SuiteName = ctx.getCurrentXmlTest().getSuite().getName();
			String TestcaseName = ctx.getCurrentXmlTest().getName();
			String TestcaseDescription = "đang test cho method: "
					+ Iresult.getMethod().getMethodName();
			String TestcaseStartTime = timeDateReport(testLogs
					.getTest().getStartedTime());
			String TestcaseEndTime = timeDateReport(testLogs
					.getTest().getEndedTime());
			String TestcaseDuration = testLogs.getTest().getRunDuration();
			String TestcaseStatus = testLogs.getTest().getStatus().toString();
			// String TestcaseAuthor =
			// testLogs.getTest().getAuthorsList().get(0).getName();
			String TestcaseAuthor = ctx.getCurrentXmlTest().getParameter("author");
			InetAddress ip = InetAddress.getLocalHost(); // Lấy IP
			String TestcaseHostname = ip.getHostName();
			String TestcaseIP = ip.getHostAddress();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return result;
	}
}
