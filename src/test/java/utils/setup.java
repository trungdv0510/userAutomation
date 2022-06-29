package utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.Screen;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import API.InsertToServer;
import API.MapHashMap;
import API.okHttpApi;
import API.tableDB;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.github.bonigarcia.wdm.WebDriverManager;

public class setup {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest testLogs;
	public static AppiumDriver<MobileElement> appiumDriver;
	public static Screen screen;
	public String suiteName;
	public String testName;
	public static String videoName;
	public static String pathfileIMGSave;
	public static UUID SuiteUUID;
	public static UUID TestUUID;
	public static String pathVideoMp4;
	public static ITestContext ctxLocal;
	public static ITestResult resultLocal;
	public static int totalPass = 0, totalFail = 0, testlogSum = 0, runTime = 0;
	/*
	 * Method -> lấy tên các method run trong class
	 */
	public static Method methodLocal;
	public static String testnameLocal;
	public static String running;
	@Parameters({ "testname","chrome" })
	@BeforeMethod
	public void beforeMethod(ITestContext ctx, Method method, String testname,String chrome) {
		try {
			TestUUID = UUID.randomUUID();
			System.out.println("TestUUID" + TestUUID);
			testLogs = extent.startTest(testname + ": " + this.getClass().getName(),
					"đang test cho method: " + method.getName());
			testLogs.assignCategory(ctx.getCurrentXmlTest().getSuite().getName());

			ctxLocal = ctx;
			methodLocal = method;
			testnameLocal = testname;
			if (!chrome.contains("api")) {
				System.err.println("Video screen");
				videoName = randomName.VideoAvi(this.getClass().getName());
				pathVideoMp4 = randomName.pathVideoMp4(this.getClass().getName());
				ScreenshotAndVideo.startRecord(videoName);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}

	}

	@Parameters({ "type" })
	@AfterMethod
	public void afterMethod(ITestContext ctx, ITestResult result, String type) {
		try {
			resultLocal = result;
			System.err.println("AfterMethod");
			System.out.println(ctx.getClass().getName() + "<--->" + this.getClass().getName());
			pathfileIMGSave = randomName.pathImg(this.getClass().getName());
			String imgPath = "";
			if (type.contains("web")) {
				try {
					imgPath = ScreenshotAndVideo.screenShotBySelenium(driver, pathfileIMGSave);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("CaptureScreen by selenium error");
					try {
						imgPath = ScreenshotAndVideo.screenShotByJava(pathfileIMGSave);
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println("CaptureScreen by java error");
					}
				}
			} else {
				imgPath = ScreenshotAndVideo.screenShotByJava(pathfileIMGSave);
			}
			System.out.println(imgPath);
			File imgFile = new File(contains.folderReprotLocation + imgPath);
			if (imgFile.exists() && !imgFile.isDirectory()) {
				testLogs.log(LogStatus.INFO, testLogs.addScreenCapture(imgPath), "");
			}
			// Stop video
			System.err.println("Stop video");
			String pathVideoAvi = randomName.pathVideoAvi(videoName);
			ScreenshotAndVideo.stopRecord();
			// convert video to mp4
			convert.AviToMp4(pathVideoAvi, pathVideoMp4);
			// xóa video đuôi AVI
			if (fileUtils.deleteIfExists(contains.folderReprotLocation + pathVideoAvi)) {
				System.out.println("Delete file success");
			}
			extent.endTest(testLogs);
			extent.flush();
			//insert video and picture to server
			String pathImgFromServer =  okHttpApi.insertImg(contains.folderReprotLocation+imgPath, contains.url+contains.ApiImg, contains.MEDIA_TYPE_JPG);
			String pathVideoFromServer = okHttpApi.insertImg(contains.folderReprotLocation+pathVideoMp4, contains.url+contains.ApiVideo, contains.MEDIA_TYPE_VIDEO);
			testLogs.log(LogStatus.INFO, pathImgFromServer,"image");
			testLogs.log(LogStatus.INFO, pathVideoFromServer,"video");
			// Lấy thông số của bài test sau khi đã chạy xong
			InsertToServer.testcase(ctx, result);
			// lấy thông tin bài log của method
			InsertToServer.testLog();
			System.out.println(runTime);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}

	@Parameters({ "type", "bareURL", "chrome" })
	@BeforeTest
	public void beforeTest(ITestContext ctx, String type, String bareURL, String chrome) {
		System.err.println("BeforeTest");
		try {
			if (type.contains("web")) {
				if (chrome.contains("chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(ChromeCap("chrome"));
				} else if (chrome.contains("edge")) {
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
				} else if (chrome.contains("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				} else {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(ChromeCap("chrome"));
				}
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(contains.TIME_WAITING, TimeUnit.SECONDS);
				driver.get(bareURL);

			} else if (type.contains("app")) {
				// lấy những thông tin khi chạy 
				String deviceName =  ctx.getCurrentXmlTest().getParameter("deviceName");
				String udid =  ctx.getCurrentXmlTest().getParameter("udid");
				String platformName =  ctx.getCurrentXmlTest().getParameter("platformName");
				String platformVersion =  ctx.getCurrentXmlTest().getParameter("platformVersion");
				String appPackage =  ctx.getCurrentXmlTest().getParameter("appPackage");
				String appActivity =  ctx.getCurrentXmlTest().getParameter("appActivity");
				URL url = new URL("http://127.0.0.1:4723/wd/hub");
				appiumDriver = new AppiumDriver<MobileElement>(url, configAppCapabilities(deviceName, udid,
						platformName, platformVersion, appPackage, appActivity));
			} else if (type.contains("sikuli")) {
				screen = new Screen();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	@Parameters("type")
	@AfterTest
	public void afterTest(String type) {
		try {
			System.err.println("AfterTest");
			if (type.contains("web")) {
				if (driver != null) {
					driver.quit();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@BeforeSuite
	public void BeforeSuite(ITestContext itest) {
		System.err.println("BeforeSuite");
		SuiteUUID = UUID.randomUUID();
		suiteName = itest.getCurrentXmlTest().getSuite().getName();
		extent = null;
		System.out.println("Suite name " + suiteName);
		String systemURL = System.getProperty("user.dir");
		System.out.println("Đường dẫn hiện tại là " + systemURL);
		// System.setProperty("webdriver.chrome.driver",systemURL+"/lib/chromedriver.exe");
		SuiteUUID = UUID.randomUUID();
		PropertyConfigurator.configure("log/log4j.properties");
		extent = ScreenshotAndVideo.Instance(extent, suiteName);
		extent.loadConfig(new File("reports/report-config.xml"));
	}

	@AfterSuite
	public void afterSuite(ITestContext itest) {
		try {
			// cấu hình cho testsuite
			InsertToServer.testSuite();
			extent.close();
			
			InsertToServer.insertTestSuite();
			InsertToServer.insertTestCase();
			InsertToServer.insertTestLog();
			System.err.println("AfterSuite");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// config Chorme
	public static DesiredCapabilities ChromeCap(String browser) throws Exception {
//		System.setProperty("webdriver.chrome.driver", "libs/driver/chromedriver.exe");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", txtFolderSave);
		// disable save password pop-up
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("safebrowsing.enabled", "true");

		// setup path to folder 'data/downloads' when downloads file
		chromePrefs.put("download.default_directory",
				System.getProperty("user.dir") + File.separator + "data" + File.separator + "downloads");

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);

		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("--disable-print-preview");
		options.addArguments("disable-infobars");
		if (browser.equals("ghost")) {
			options.addArguments("--headless");
			options.addArguments("window-size=1920,1080");
		}

//		options.addArguments("--disable-extensions");
//		options.addArguments("test-type");
		options.addArguments("--disable-gpu", "start-maximized", "--log-level=3");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

		return cap;
	}

	// configAppium
	public static DesiredCapabilities configAppCapabilities(String deviceName, String udid, String platformName,
			String platformVersion, String appPackage, String appActivity) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("udid", udid);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("unicodeKeyboard", "true");
		capabilities.setCapability("resetKeyboard", "true");
		return capabilities;
	}
}