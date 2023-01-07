package SeleniumIDE.ActionEvent;

import SeleniumIDE.SeleniumObject.Command;
import SeleniumIDE.SeleniumObject.TestCase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import static utils.FunctionUtils.getElementWithTarget;

public class ActionUtils {
    public boolean runAllTest(ExtentTest testLog, TestCase testCase, WebDriver driver){
        int count = 0;
        List<Command>  listAction = testCase.getTests().get(0).getCommands();
        for (Command action: listAction) {
           count += SetActionForAllTest(testLog,action,driver);
        }
        return count <= 0;
    }
    public Integer SetActionForAllTest(ExtentTest log, Command action,WebDriver driver){
        int count = 0;
        switch (action.getCommand()){
            case EventSeleniumIde.click:
               if(!clickButton(log, action, driver)){
                   count++;
               }
                break;
            case  EventSeleniumIde.type:
               if(!inputText(log, action, driver)){
                   count++;
               }
                break;
            case EventSeleniumIde.open:
                if(!getUrl(log, action, driver)){
                    count++;
                }
                break;
            case EventSeleniumIde.setWindowSize:
                if (!setWindowSize(log,action.getTarget(),driver)){
                    count++;
                }
                break;
            case EventSeleniumIde.selectFrame:
                if (!selectFrame(log,action,driver)){
                    count ++;
                }
            case EventSeleniumIde.sendKeys:
                if (!sendKeys(log,action,driver)){
                    count++;
                }
            case EventSeleniumIde.selectWindow:
                if (!selectWindowHandle(log,action,driver)){
                    count++;
                }
                break;
            case EventSeleniumIde.runScript:
                if (!runScript(log,action,driver)){
                    count++;
                }
                break;
            default:
                if(!validateElements(log, action, driver)){
                    count++;
                }
                break;
        }
        return count;
    }
    public boolean getUrl(ExtentTest log, Command action,WebDriver driver){
        boolean result = false;
        try{
            String url = driver.getCurrentUrl();
            String path = url+action.getTarget();
            driver.get(path);
            System.out.printf("url => "+path);
            log.log(LogStatus.PASS,"Get url "+ url+" success","");
            result = true;
        }catch (Exception e){
            log.log(LogStatus.FAIL,"Error with step get url "+ e.getMessage(), "");
        }
        return result;
    }
    public boolean inputText(ExtentTest log, Command action,WebDriver driver){
        boolean result = false;
        try {
            WebElement inputTextField = getElementWithTarget(action.getTarget(),driver);
            inputTextField.clear();
            inputTextField.sendKeys(action.getValue());
            System.out.printf("input text field " +action.getValue());
            log.log(LogStatus.PASS,"Input "+action.getValue()+" to "+action.getTarget()+" success","");
            result = true;
        }catch (Exception e){
            log.log(LogStatus.FAIL,"Error with step input text "+ e.getMessage(), "");
        }
        return result;
    }
    public boolean clickButton(ExtentTest log, Command action,WebDriver driver){
        boolean result = false;
        try {
            WebElement clickButton = getElementWithTarget(action.getTarget(),driver);
            clickButton.click();
            System.out.println("click button success");
            log.log(LogStatus.PASS,"Click button success","");
            result = true;
        }catch (Exception e){
            log.log(LogStatus.FAIL,"Error with step click button  "+ e.getMessage(), "");
        }
        return result;
    }
    public boolean validateElements(ExtentTest log, Command action,WebDriver driver){
        boolean result = false;
        try {
            getElementWithTarget(action.getTarget(),driver);
            System.out.println("find success");
            log.log(LogStatus.PASS,"Can find elements success","");
            result = true;
        }catch (Exception e){
            log.log(LogStatus.FAIL,"Error with step validate elements "+ e.getMessage(), "");
        }
        return result;
    }
    public boolean setWindowSize(ExtentTest log, String target,WebDriver driver){
        boolean result = false;
        try{
            String[] size = target.split("x");
            int width = Integer.parseInt(size[0]);
            int height = Integer.parseInt(size[1]);
            Dimension dimension = new Dimension(width,height);
            driver.manage().window().setSize(dimension);
            log.log(LogStatus.PASS,"Success with step set size window ","");
            result = true;
        }catch (Exception e){
            log.log(LogStatus.FAIL,"Error with step set size window "+ e.getMessage(), "");
        }
        return result;
    }
    public boolean selectFrame(ExtentTest log, Command action ,WebDriver driver) {
        boolean result = false;
        try {
            Set<String> listWindow = driver.getWindowHandles();
            String window = driver.getWindowHandle();
            if(!action.getTarget().contains("root")){
                listWindow.remove(window);
                if (listWindow.size()>0){
                    driver.switchTo().window(listWindow.toArray()[0].toString());
                }
            }
            log.log(LogStatus.PASS, "Select window " + action.getTarget() + " success ", "");
        } catch (Exception e) {
            log.log(LogStatus.FAIL, "Select window " + action.getTarget() + " false: " + e.getMessage(), "");
        }
        return result;
    }
    public boolean sendKeys(ExtentTest log, Command action ,WebDriver driver){
        boolean result = false;
        try {
            switch (action.getValue().replace("${","").replace("}","")){
                case "KEY_ENTER":
                    WebElement element = getElementWithTarget(action.getTarget(),driver);
                    element.sendKeys(Keys.ENTER);
                    break;
                case "KEY_TAB":
                    WebElement elementTab = getElementWithTarget(action.getTarget(),driver);
                    elementTab.sendKeys(Keys.TAB);
                    break;
                case"KEY_SPACE":
                    WebElement elementSpace = getElementWithTarget(action.getTarget(),driver);
                    elementSpace.sendKeys(Keys.SPACE);
                    break;
            }
            log.log(LogStatus.PASS, "Click key board  " + action.getValue() + " success ", "");
        }catch (Exception e){
            log.log(LogStatus.FAIL, "Click  " + action.getValue() + " false: " + e.getMessage(), "");
        }
        return result;
    }
    public boolean runScript(ExtentTest log, Command action ,WebDriver driver){
        boolean result = false;
        try {
            String scriptAction = action.getTarget();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(scriptAction, "");
            log.log(LogStatus.PASS, "run Script  " + scriptAction + " success ", "");
        }catch (Exception e){
            log.log(LogStatus.FAIL, "Run script   " + action.getTarget() + " false: " + e.getMessage(), "");
        }
        return result;
    }
    public boolean selectWindowHandle(ExtentTest log, Command action ,WebDriver driver){
        boolean result = false;
        try{
            Thread.sleep(4000);
            Robot rb = null;
            try {
                rb = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            // Copy File path vào Clipboard
            StringSelection str = new StringSelection(action.getTarget());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

            Thread.sleep(1000);

            // Nhấn Control+V để dán
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);

            // Xác nhận Control V trên
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_V);

            Thread.sleep(1000);

            // Nhấn Enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(4000);
            log.log(LogStatus.PASS, "Select window  " + action.getTarget() + " success ", "");
        }catch (Exception e){
            log.log(LogStatus.FAIL, "Select window   " + action.getTarget() + " false: " + e.getMessage(), "");
        }
        return result;
    }
}
