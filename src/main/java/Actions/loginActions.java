package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginActions {
	public boolean login(WebDriver driver, String username, String password) {
		boolean result = false;
		try {
			WebElement txtUsername = driver.findElement(By.id("id_username"));
			txtUsername.clear();
			txtUsername.sendKeys(username);
			WebElement txtPassword = driver.findElement(By.id("id_password"));
			txtPassword.clear();
			txtPassword.sendKeys(password);
			WebElement btnLogin = driver.findElement(By.cssSelector("input[type='submit']"));
			btnLogin.click();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return result;
	}
}
