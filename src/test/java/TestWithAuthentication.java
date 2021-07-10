import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static junit.framework.TestCase.assertEquals;

public class TestWithAuthentication extends BaseTestClass {

    @Before
    @Override
    public void start() throws InterruptedException {
        super.start();
        driver.findElement(By.xpath("//span[@class='auth__link sign-in']")).click();
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("tester.qualitative@yandex.ru");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("test_password");
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@role='document']"))));
    }

    @Test
    public void positiveAuth() {
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
