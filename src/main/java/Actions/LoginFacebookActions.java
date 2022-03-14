package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFacebookActions {
	public boolean checkLoginPage(WebDriver driver) {
		boolean result =false;
		try {
			boolean facebookLogo = driver.findElements(By.cssSelector("//h1[@id='site-name']//a[contains(.,'Computer Admin')]")).size()>0;
			if (facebookLogo) {
				result = true;
			}
			else {
				boolean facebookLogo_2 = driver.findElements(By.cssSelector("//a[contains(.,'Computer Admin')]")).size()>0;
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
			WebElement txtUserName = driver.findElement(By.id("email"));
			txtUserName.clear();
			txtUserName.sendKeys(userName);
			Thread.sleep(3000);
			WebElement txtPassword = driver.findElement(By.id("pass"));
			txtPassword.clear();
			txtPassword.sendKeys(password);
			Thread.sleep(3000);
			WebElement btnLogin = driver.findElement(By.name("login"));
			btnLogin.click();
			Thread.sleep(3000);
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
			boolean resutlAlert = driver.findElements(By.xpath("//p[@class='errornote']")).size()>0;
			if(resutlAlert) {
				WebElement resutlEl = driver.findElement(By.xpath("//p[@class='errornote']"));
				System.out.println("Message hiện ra là " +resutlEl.getText() );
				System.out.println("Message file excel là " +message );
				if (resutlEl.getText().contains(message)) {
					result = true;
				}
			}
			else {
				Thread.sleep(3000);
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
