import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class TestWithoutAuthentication extends BaseTestClass {

    @Test
    public void cookieReceiveTest() {
        Set<Cookie> receivedCookieCount = driver.manage().getCookies();
        assertFalse(receivedCookieCount.isEmpty());
    }

    @Test
    public void pagePartCountTest() {
        List<WebElement> listOfPageParts = new ArrayList<>();
        listOfPageParts.add(driver.findElement(By.id("header")));
        listOfPageParts.add(driver.findElement(By.id("page-content-wrap")));
        listOfPageParts.add(driver.findElement(By.id("footer")));
        int expectedPagePartCount = 3;
        assertEquals(expectedPagePartCount, listOfPageParts.size());
    }

    @Test
    public void menuPartCountTest() {
        List<WebElement> listOfMenuParts = driver.findElements(By.xpath("//nav//ul[contains(@class,'menu-items_lvl1')]/li"));
        int expectedMenuPartCount = 11;
        assertEquals(expectedMenuPartCount, listOfMenuParts.size());
    }

    @Test
    public void menuSectionTest() {
        chooseMenuSection();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = CATALOG + "nogti/";
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void categoryMenuTest() {
        Actions redirectToCategory = new Actions(driver);
        redirectToCategory
                .moveToElement(driver.findElement(By.xpath("//span[text()='Волосы']")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.xpath("//span[text()='Уход и лечение']")))
                .click()
                .build()
                .perform();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = CATALOG + "uhod_dlya_volos/";
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void searchTest() {
        WebElement searchInput = driver.findElement(By.xpath("//input[@class='input']"));
        searchInput.sendKeys("staleks", Keys.ENTER);
        List<WebElement> listOfResults = driver.findElements(By.xpath("//div[@class='results__items']//a"));
        assertFalse(listOfResults.isEmpty());
    }

    @Test
    public void sideCategoryTest() {
        chooseMenuSection();
        WebElement category = driver.findElement(By.xpath("//div[contains(@class,'categories')]//ul[@data-lvl='1']//li[3]//a"));
        category.click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = CATALOG + "geli/";
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void addToBasketTest() {
        fillBasket();
        int actualProductCount = Integer.parseInt(driver.findElement(By.xpath("//div[@data-type='basket']//span[@class='num products-count']")).getAttribute("innerText"));
        int expectedProductCount = 2;
        assertEquals(expectedProductCount, actualProductCount);
    }

    @Test
    public void removeFromBasketTest() throws InterruptedException {
        fillBasket();
        driver.get(PERSONAL + "cart/");
        WebElement remove = driver.findElement(By.xpath("(//div[@class='product-list']//div[@class='delete-wrap'])[1]"));
        remove.click();
        Thread.sleep(1000);
        String actualText = driver.findElement(By.xpath("(//div[@data-tab='incart'])[1]")).getText();
        String expectedText = "В КОРЗИНЕ (1 )";
        assertEquals(expectedText, actualText);
    }

    @Test
    public void addToFavoriteTest() {
        chooseMenuSection();
        Actions addProductToFavorite = new Actions(driver);
        addProductToFavorite
                .moveToElement(driver.findElement(By.xpath("//div[@class='catalog__items products']/div[1]")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.xpath("//div[@class='catalog__items products']/div[1]//span[contains(@class,'btn__text')][1]")))
                .click()
                .pause(Duration.ofSeconds(1))
                .build()
                .perform();
        int actualFavoriteCount = Integer.parseInt(driver.findElement(By.xpath("//div[@data-type='fav']//span[@class='num products-count']")).getAttribute("innerText"));
        int expectedFavoriteCount = 1;
        assertEquals(expectedFavoriteCount, actualFavoriteCount);
    }

    private void chooseMenuSection() {
        WebElement menuSection = driver.findElement(By.xpath("(//nav//ul[contains(@class,'menu-items_lvl1')]/li)[" + MENUSECTION + "]"));
        menuSection.click();
    }

    private void fillBasket() {
        chooseMenuSection();
        Actions addProductToBasket = new Actions(driver);
        addProductToBasket
                .moveToElement(driver.findElement(By.xpath("(//div[@class='catalog__items products']/div)[1]")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.xpath("(//div[@class='catalog__items products']/div)[1]//button[@class='btn buy-btn']")))
                .click()
                .moveToElement(driver.findElement(By.xpath("(//div[@class='catalog__items products']/div)[2]")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.xpath("(//div[@class='catalog__items products']/div)[2]//button[@class='btn buy-btn']")))
                .click()
                .pause(Duration.ofSeconds(1))
                .build()
                .perform();
    }
}