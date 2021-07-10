import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTestClass {

    protected WebDriver driver;
    protected WebDriverWait wait;
    final private String PATHTODRIVER = "C:\\Users\\leel\\AppData\\Local\\Microsoft\\WindowsApps\\chromedriver.exe";
    final protected String WEBSITE = "https://odiva.ru";
    final protected String CATALOG = "https://odiva.ru/catalog/";
    final protected String PERSONAL = "https://odiva.ru/personal/";
    final protected int MENUSECTION = 4;

    @Before
    public void start() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", PATHTODRIVER);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get(WEBSITE);


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

