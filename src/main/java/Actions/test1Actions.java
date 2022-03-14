package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class test1Actions {
	public boolean Login(WebDriver driver, String search) {
		boolean result = false;
		try {
			WebElement txtSearch = driver.findElement(By.id("js-search"));
			txtSearch.sendKeys(search);
			WebElement btnTimKiem = driver.findElement(By.className("submit-search"));
			btnTimKiem.click();
			result = false;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return result;
	}
}
