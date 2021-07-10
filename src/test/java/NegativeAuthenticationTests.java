import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static junit.framework.Assert.assertNotNull;

public class NegativeAuthenticationTests extends BaseTestClass {
    @Test
    public void withWrongPasswordTest() {
        openSignForm();
        enterLogin();
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("password_wrong");
        signConfirm();
        assertNotNull(getAuthenticationErrorWarning());
    }

    @Test
    public void withoutPasswordTest() {
        openSignForm();
        enterLogin();
        signConfirm();
        WebElement disabledButton = driver.findElement(By.xpath("//button[contains(@class,'btn--disabled')]"));
        assertNotNull(disabledButton);
    }

    @Test
    public void withWrongLoginTest() {
        openSignForm();
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("login_wrong");
        enterPassword();
        signConfirm();
        assertNotNull(getAuthenticationErrorWarning());
    }

    @Test
    public void withoutLoginTest() {
        openSignForm();
        enterPassword();
        signConfirm();
        assertNotNull(getAuthenticationErrorWarning());
    }

    private WebElement getAuthenticationErrorWarning() {
        return driver.findElement(By.xpath("//form[@id='sign-in-form']//div[contains(@class,'message--error')]"));
    }
}
