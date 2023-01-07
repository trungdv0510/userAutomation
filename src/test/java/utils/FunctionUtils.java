package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FunctionUtils {
    public static WebElement getElementWithTarget(String target, WebDriver driver){
        WebElement elementName;
        By by = null;
        if (target.startsWith("css=")){
            String elementCss = target.replace("css=.","");
            by = By.cssSelector(elementCss);
        } else if (target.startsWith("xpath=")){
            String elementXpath = target.replace("xpath=","");
            by = By.xpath(elementXpath);
        } else if (target.startsWith("linkText=")) {
            String elementLinkText = target.replace("linkText=","");
            by = By.linkText(elementLinkText);
        } else if (target.startsWith("id=")) {
            String elementId = target.replace("id=","");
            by = By.linkText(elementId);
        } else if (target.startsWith("name=")) {
            String elementNa = target.replace("name=","");
            by = By.linkText(elementNa);
        }
        elementName = driver.findElement(by);
        return elementName;
    }
}
