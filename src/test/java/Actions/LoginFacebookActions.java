package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFacebookActions {
	public boolean checkLoginPage(WebDriver driver) {
		boolean result =false;
		try {
			boolean facebookLogo = driver.findElements(By.cssSelector("img[class='fb_logo _8ilh img']")).size()>0;
			if (facebookLogo) {
				//result = true;
				return true;
			}
			else {
				boolean facebookLogo_2 = driver.findElements(By.cssSelector("img[class='_97vu img']")).size()>0;
				if(facebookLogo_2) {
					result = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		 return result;
		
	}
	
	public boolean checkLogin(WebDriver driver, String userName, String password) {
		boolean result = false;
		try {
			WebElement txtUserName = driver.findElement(By.cssSelector("input[name='email']"));
			txtUserName.clear();
			txtUserName.sendKeys(userName);
			WebElement txtPassword = driver.findElement(By.cssSelector("input[name='pass']"));
			txtPassword.clear();
			txtPassword.sendKeys(password);
			boolean checkButton = driver.findElements(By.cssSelector("button[id='loginbutton']")).size()>0;
			if (checkButton) {
				WebElement btnLogin = driver.findElement(By.cssSelector("button[id='loginbutton']"));
				btnLogin.click();
			}
			else {
				WebElement loginButton = driver.findElement(By.cssSelector("button[name='login']"));
				loginButton.click();
			}
			result= true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return result;
	}
	public boolean checkMessage(WebDriver driver, String message) {
		boolean result = false;
		try {
			boolean resutlAlert = driver.findElements(By.cssSelector("div[class='_9ay7']")).size()>0;
			if(resutlAlert) {
				WebElement resutlEl = driver.findElement(By.cssSelector("div[class='_9ay7']"));
				System.out.println("Message hiện ra là " +resutlEl.getText() );
				System.out.println("Message file excel là " +message );
				if (resutlEl.getText().contains(message)) {
					return true;
				}
			}
			else {
				boolean facebookLogo = driver.findElements(By.xpath("//a[@aria-label='Facebook']")).size()>0;
				if(facebookLogo) {
					result = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return result;
	}
}
