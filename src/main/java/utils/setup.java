package utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
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
	public static UUID StepUUID;
	public static String pathVideoMp4;
	private static HashMap<String, String> testSuiteAPI;
	private static HashMap<String, String> testCaseAPI;
	private static List<HashMap<String, String>>  testLogsAPI;
	
	@Parameters({ "testname" })
	@BeforeMethod
	public void beforeMethod(ITestContext ctx, Method method, String testname) {
		testCaseAPI = new HashMap<String, String>();
		System.err.println("BeforeMethod");
		TestUUID = UUID.randomUUID();
		System.err.println("BeforeMethod");
		System.out.println("TestUUID" + TestUUID);
		testLogs = extent.startTest(testname+ ": " +this.getClass().getName(), "đang test cho method: " + method.getName());
		testLogs.assignCategory(ctx.getCurrentXmlTest().getSuite().getName());

	}

	@Parameters({ "type" })
	@AfterMethod
	public void afterMethod(ITestContext ctx, ITestResult result, String type) {
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
		File imgFile = new File(contains.folderReprotLocation+imgPath);
		if(imgFile.exists() && !imgFile.isDirectory()) {
			testLogs.log(LogStatus.INFO, testLogs.addScreenCapture(imgPath),"");
		}
		extent.endTest(testLogs);
		extent.flush();
	}

	@Parameters({ "type", "bareURL", "chrome", "deviceName", "udid", "platformName", "platformVersion", "appPackage",
			"appActivity" })
	@BeforeTest
	public void beforeTest(ITestContext ctx, String type, String bareURL, String chrome, String deviceName, String udid,
			String platformName, String platformVersion, String appPackage, String appActivity) {
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
				URL url = new URL("http://127.0.0.1:4723/wd/hub");
				appiumDriver = new AppiumDriver<MobileElement>(url, configAppCapabilities(deviceName, udid,
						platformName, platformVersion, appPackage, appActivity));
			} else if (type.contains("sikuli")) {
				screen = new Screen();
			}
			System.err.println("Video screen");
			videoName = randomName.VideoAvi(this.getClass().getName());
			pathVideoMp4 = randomName.pathVideoMp4(this.getClass().getName());
			ScreenshotAndVideo.startRecord(videoName);
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
			// tắt những bài test chạy bằng Web
			System.err.println("Stop video");
			String pathVideoAvi = randomName.pathVideoAvi(videoName);
			ScreenshotAndVideo.stopRecord();
			//convert video to mp4
			convert.AviToMp4(pathVideoAvi, pathVideoMp4);
			//xóa video đuôi AVI
			if (fileUtils.deleteIfExists(contains.folderReprotLocation +pathVideoAvi)) {
				System.out.println("Delete file success");
			}
			if (type.contains("web")) {
				if (driver != null) {
					driver.quit();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	  @BeforeClass 
	  public void beforeClass(ITestContext ctx, ITestResult result) throws Exception { 
		 System.err.println("BeforeClass"); pathVideoSave = randomName.pathVideo(ctx.getClass().getName());
		 ScreenshotAndVideo.startRecord(pathVideoSave); 
	  }
	  
	 @AfterClass 
	 public void afterClass() throws Exception {
		 System.err.println("@AfterClass");
		 ScreenshotAndVideo.stopRecord(); 
		 }
	 */
	@BeforeSuite
	public void BeforeSuite(ITestContext itest) {
		System.err.println("BeforeSuite");
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
		System.err.println("AfterSuite");
		extent.close();
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
