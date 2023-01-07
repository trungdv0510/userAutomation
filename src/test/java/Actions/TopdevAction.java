package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumUtils.EventInPage;
import utils.UtilsActions;
import utils.contains;

public class TopdevAction {
    private EventInPage eventInPage = new EventInPage();
    public boolean removePopUp(WebDriver driver){
        boolean result = false;
        try{
            if (eventInPage.clickButton(driver, By.id("iDontCareEventTopDev"))){
                result = true;
            }
        }catch (Exception e){
            contains.errorLog.append(e.getMessage());
        }

        return result;
    }
}
