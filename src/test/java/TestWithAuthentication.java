import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

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
    }

    @Test
    public void positiveAuth() throws InterruptedException {
        Thread.sleep(3000);
        String actualText = driver.findElement(By.xpath("//div[@class='profile__name']")).getText();
        String expectedText = "Tester";
        assertEquals(expectedText, actualText);
    }

    @Test
    public void primaryDiscount() throws InterruptedException {
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath("//span[@class='num']")).getAttribute("innerText");
        int actualDiscount = Integer.parseInt(text.substring(0, (text.length() - 1)));
        int expectedDiscount = 5;
        assertEquals(expectedDiscount, actualDiscount);
    }


}
