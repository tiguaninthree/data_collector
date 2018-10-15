package grabber.common;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class UiUtils {

    /**
     * Check exist of web-element wrapper.
     */
    public static boolean isExists(SelenideElement element) {
        if (element.exists() && element.is(Condition.visible)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check on exists {@link this.isExists()} before extracting data.
     */
    public static String checkAndExtractText(SelenideElement element) {
        if (isExists(element)) {
            return element.getText();
        } else {
            return null;
        }
    }
}
