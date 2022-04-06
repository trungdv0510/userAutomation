package utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import API.okHttpApi;

public class UtilsActions {
	public void captureScreenJava() throws Exception, IOException, URISyntaxException {
		setup.pathfileIMGSave = randomName.pathImg(this.getClass().getName());
		String imgPath = "";
		int countImg = 0;
		imgPath = ScreenshotAndVideo.screenShotByJava(setup.pathfileIMGSave);
		File img = new File(contains.folderReprotLocation+imgPath);
		if(img.exists() && !img.isDirectory()) {
			setup.testLogs.log(LogStatus.INFO, setup.testLogs.addScreenCapture(imgPath),"");
			countImg = countImg+1;
		}
		if(contains.sendToServer == 1) {
			if(countImg>0) {
				String pathImgOnServer = okHttpApi.insertImg(contains.folderReprotLocation+imgPath, contains.url+contains.ApiImg,contains.MEDIA_TYPE_JPG);
				setup.testLogs.log(LogStatus.INFO, pathImgOnServer,"image");
				System.out.println("pathImgOnServer: " +pathImgOnServer);
			}
		}
	}
	public void captureScreenSelenium(WebDriver driver) throws Exception, IOException, URISyntaxException {
		setup.pathfileIMGSave = randomName.pathImg(this.getClass().getName());
		String imgPath = "";
		int countImg = 0;
		imgPath = ScreenshotAndVideo.screenShotBySelenium(driver,setup.pathfileIMGSave);
		File img = new File(contains.folderReprotLocation+imgPath);
		if(img.exists() && !img.isDirectory()) {
			setup.testLogs.log(LogStatus.INFO, setup.testLogs.addScreenCapture(imgPath),"");
			countImg = countImg+1;
		}
		if(contains.sendToServer == 1) {
			if(countImg>0) {
				String pathImgOnServer = okHttpApi.insertImg(contains.folderReprotLocation+imgPath, contains.url+contains.ApiImg,contains.MEDIA_TYPE_JPG);
				setup.testLogs.log(LogStatus.INFO, pathImgOnServer,"image");
				System.out.println("pathImgOnServer: " +pathImgOnServer);
			}
		}
	}
}
