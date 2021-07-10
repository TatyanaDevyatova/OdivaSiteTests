import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

public class PositiveAuthenticationTests extends BaseTestClass {

    @Before
    @Override
    public void start() throws InterruptedException {
        super.start();
        openSignForm();
        enterLogin();
        enterPassword();
        signConfirm();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@role='document']"))));
    }

    @Test
    public void authenticationTest() {
        WebElement profileName = driver.findElement(By.xpath("//div[@class='profile__name']"));
        String actualText = profileName.getText();
        String expectedText = "Tester";
        assertEquals(expectedText, actualText);
    }

    @Test
    public void primaryDiscount() {
        WebElement profileDiscount = driver.findElement(By.xpath("//span[@class='num']"));
        String text = profileDiscount.getAttribute("innerText");
        int actualDiscount = Integer.parseInt(text.substring(0, (text.length() - 1)));
        int expectedDiscount = 5;
        assertEquals(expectedDiscount, actualDiscount);
    }

}
