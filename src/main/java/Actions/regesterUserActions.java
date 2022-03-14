package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class regesterUserActions {
	public boolean checkRegesterPage(WebDriver driver) {
		boolean result = false;
		try {
			boolean checkPage =  driver.findElements(By.xpath("//h1[contains(.,'Create an Account!')]")).size()>0;
			if(checkPage) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public boolean createAnAccount(WebDriver driver, String username, String phoneNumber, String address, String password, String repeatPassword) {
		boolean result = false;
		try {
			WebElement txtUsername = driver.findElement(By.id("username"));
			txtUsername.sendKeys(username);
			Thread.sleep(2000);
			WebElement txtPhoneNumber = driver.findElement(By.id("phone"));
			txtPhoneNumber.sendKeys(phoneNumber);
			Thread.sleep(2000);
			WebElement txtAddress = driver.findElement(By.id("address"));
			txtAddress.sendKeys(address);
			Thread.sleep(2000);
			WebElement txtPassword = driver.findElement(By.id("password"));
			txtPassword.sendKeys(password);
			Thread.sleep(2000);
			WebElement txtRepeatPassword = driver.findElement(By.id("RepeatPassword"));
			txtRepeatPassword.sendKeys(repeatPassword);
			Thread.sleep(2000);
			WebElement btnSubmit = driver.findElement(By.id("submit"));
			btnSubmit.click();
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return result;
	}
	public boolean checkResult(WebDriver driver, String resultMessage) {
		boolean result = false;
		try {
			boolean message = driver.findElements(By.cssSelector("div[class='alert alert-success']")).size()>0;
			if (message) {
				WebElement messgae = driver.findElement(By.cssSelector("div[class='alert alert-success']"));
				if (messgae.getText().contains(resultMessage)) {
					result=true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return result;
	}
}
