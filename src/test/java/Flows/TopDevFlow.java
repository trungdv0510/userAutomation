package Flows;

import Actions.TopdevAction;
import SeleniumIDE.ActionEvent.ActionUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import utils.UtilsActions;
import utils.setup;

import static org.testng.Assert.assertTrue;

public class TopDevFlow extends setup {
    ActionUtils actionUtils = new ActionUtils();
    TopdevAction topdevAction = new TopdevAction();
    @Test
    public void testForLoginPage() {
        try {
            assertTrue(topdevAction.removePopUp(driver));
            testLogs.log(LogStatus.PASS, "Remove pupop success", "");
            actionUtils.runAllTest(testLogs,testCase,driver);
            testLogs.log(LogStatus.PASS, "Run all test is successfully", "");
        } catch (AssertionError e) {
            // TODO: handle exception
            UtilsActions.saveErrorToLog(e.getMessage());
            assertTrue(false);
        }
    }
}
