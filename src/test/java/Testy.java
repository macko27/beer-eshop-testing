import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Testy {

    @Test
    void loginSpravneUdaje() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/login");
        WebElement login = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        login.sendKeys("admin@gmail.com");
        password.sendKeys("admin123");

        driver.findElement(By.name("submit")).click();

        WebElement alert = driver.findElement(By.id("customAlert"));

        assertNotNull(alert);
        driver.quit();
    }

    @Test
    void loginNespravneHeslo() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/login");
        WebElement login = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        login.sendKeys("admin@gmail.com");
        password.sendKeys("admin123456");

        driver.findElement(By.name("submit")).click();

        WebElement info = driver.findElement(By.id("wrongInput"));

        assertEquals(info.getText(), "Nesprávne údaje");
        driver.quit();
    }

    @Test
    void loginBezAdresy() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/login");
        WebElement login = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        login.sendKeys("admin");
        password.sendKeys("admin123");

        driver.findElement(By.name("submit")).click();

        assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/login");
        driver.quit();
    }

    @Test
    void registraciaRovnakeMeno() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/register");
        driver.findElement(By.id("name")).sendKeys("skuska");
        driver.findElement(By.id("email")).sendKeys("skuskaaa@gmail.com");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("password_confirmation")).sendKeys("12345678");

        driver.findElement(By.id("register")).click();

        String text = driver.findElement(By.id("wrongInputName")).getText();

        assertEquals(text, "The name has already been taken.");

        driver.quit();
    }

    @Test
    void registraciaKratkeHeslo() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/register");
        driver.findElement(By.id("name")).sendKeys("skuska");
        driver.findElement(By.id("email")).sendKeys("skuska@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("password_confirmation")).sendKeys("123");

        driver.findElement(By.id("register")).click();

        String text = driver.findElement(By.id("wrongInputPassword")).getText();

        assertEquals(text, "The password field must be at least 8 characters.");
        driver.quit();
    }

    @Test
    void vyhladanieJednehoPiva() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/beers");
        WebElement search = driver.findElement(By.id("search-bar"));
        search.sendKeys("back in town");
        WebElement submit = driver.findElement(By.id("search-button"));
        submit.click();
        List<WebElement> vysledkyVyhladavania = driver.findElements(By.className("beer-card"));
        assertEquals(vysledkyVyhladavania.size(), 1);
        driver.quit();
    }

    @Test
    void kliknutieTlacidlaDomov() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8000/beers");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int i = 1;

        while (i <= 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("home")));
                element.click();
                break;
            } catch (Exception e) {
                i++;

            }
        }

        assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/");
        driver.quit();
    }

    @Test
    void kliknutieTlacidlaPredaj() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8000/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int i = 1;

        while (i <= 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("sell")));
                element.click();
                break;
            } catch (Exception e) {
                i++;

            }
        }

        assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/beers");
        driver.quit();
    }

    @Test
    void pridanieNovehoPiva() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://127.0.0.1:8000/login");
        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.name("submit")).click();

        driver.get("http://127.0.0.1:8000/beers/create");

        driver.findElement(By.id("name")).sendKeys("skuska");
        driver.findElement(By.id("style")).sendKeys("style");
        driver.findElement(By.id("type")).sendKeys("type");
        driver.findElement(By.id("price")).sendKeys("5");
        driver.findElement(By.id("degree")).sendKeys("5");
        driver.findElement(By.id("brewery")).sendKeys("brewery");
        driver.findElement(By.id("description")).sendKeys("description");
        driver.findElement(By.id("beerCreate")).click();
        driver.get("http://127.0.0.1:8000/beers");

        WebElement search = driver.findElement(By.id("search-bar"));
        search.sendKeys("skuska");

        WebElement vysledok = driver.findElement(By.className("beer-card"));
        assertNotNull(vysledok);

        driver.quit();
    }

    @Test
    void pridanieNovehoPivaRovnakeMeno() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://127.0.0.1:8000/login");
        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.name("submit")).click();

        driver.get("http://127.0.0.1:8000/beers/create");

        driver.findElement(By.id("name")).sendKeys("BACK IN TOWN");
        driver.findElement(By.id("style")).sendKeys("style");
        driver.findElement(By.id("type")).sendKeys("type");
        driver.findElement(By.id("price")).sendKeys("5");
        driver.findElement(By.id("degree")).sendKeys("5");
        driver.findElement(By.id("brewery")).sendKeys("brewery");
        driver.findElement(By.id("description")).sendKeys("description");
        driver.findElement(By.id("beerCreate")).click();

        WebElement error = driver.findElement(By.className("wrongInput"));

        assertEquals(error.getText(), "The name has already been taken.");

        driver.quit();
    }

    @Test
    void pridanieNovehoPivaZlaCena() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://127.0.0.1:8000/login");
        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.name("submit")).click();

        driver.get("http://127.0.0.1:8000/beers/create");

        driver.findElement(By.id("name")).sendKeys("skuskaaa");
        driver.findElement(By.id("style")).sendKeys("style");
        driver.findElement(By.id("type")).sendKeys("type");
        driver.findElement(By.id("price")).sendKeys("price");
        driver.findElement(By.id("degree")).sendKeys("5");
        driver.findElement(By.id("brewery")).sendKeys("brewery");
        driver.findElement(By.id("description")).sendKeys("description");
        driver.findElement(By.id("beerCreate")).click();

        WebElement error = driver.findElement(By.className("wrongInput"));

        assertEquals(error.getText(), "The price field must be a number.");

        driver.quit();
    }


    @Test
    void pridanieTohoIstehoDoKosiku() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/beers/1");
        driver.findElement(By.className("addToCart")).click();
        driver.get("http://127.0.0.1:8000/beers/1");
        driver.findElement(By.className("addToCart")).click();

        driver.get("http://127.0.0.1:8000/cart");
        List<WebElement> rows = driver.findElements(By.id("cartItem"));
        String mnozstvo = rows.get(0).findElement(By.className("beer-quantity-change")).getAttribute("value");

        assertEquals(mnozstvo, "2");

        driver.quit();
    }

    @Test
    void odstranenieZKosikuZmenouMnozstva() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/beers/1");
        driver.findElement(By.className("addToCart")).click();

        driver.get("http://127.0.0.1:8000/cart");
        WebElement input = driver.findElement(By.className("beer-quantity-change"));

        Actions actions = new Actions(driver);
        actions.sendKeys(input, Keys.ARROW_DOWN).build().perform();

        List<WebElement> piva = driver.findElements(By.id("cartItem"));
        assertEquals(piva.size(), 0);

        driver.quit();
    }

    @Test
    void odstranenieZKosiku() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://127.0.0.1:8000/beers/1");
        driver.findElement(By.className("addToCart")).click();
        driver.get("http://127.0.0.1:8000/beers/2");
        driver.findElement(By.className("addToCart")).click();

        driver.get("http://127.0.0.1:8000/cart");

        driver.findElement(By.className("cart-delete")).click();
        List<WebElement> piva = driver.findElements(By.id("cartItem"));

        assertEquals(piva.size(), 1);

        driver.quit();
    }
}
