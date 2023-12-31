import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Testy {

    @Test
    void loginTestSpravneUdaje() {
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
    void loginTestNespravneHeslo() {
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
    void loginTestBezAdresy() {
        
    }

    @Test
    void vyhladanieJednehoPiva() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/beers");
        WebElement search = driver.findElement(By.id("search-bar"));
        search.sendKeys("back in town");
        WebElement submit = driver.findElement(By.id("search-button"));
        submit.click();
        ArrayList<WebElement> vysledkyVyhladavania = new ArrayList<>();
        vysledkyVyhladavania.add(driver.findElement(By.className("beer-card")));
        assertEquals(vysledkyVyhladavania.size(), 1);
        driver.quit();
    }

    @Test
    void vyhladaniePivaPodla() {

    }

    @Test
    void pridanieNovehoPrispevku() {        //treba dorobit prihlasenie
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/beers");
        WebElement pridatPivo = driver.findElement(By.id("BtnAddBeer"));
        pridatPivo.click();

        //TODO
        if (!driver.getCurrentUrl().equals("http://127.0.0.1:8000/beers/create")) {
            System.out.println("Test prodanieNovehoPrispevku nebol uspesny - zla otvorena stranka.");
            driver.quit();
            return;
        }
        driver.findElement(By.id("name")).sendKeys("skuska");
        driver.findElement(By.id("style")).sendKeys("style");
        driver.findElement(By.id("type")).sendKeys("type");
        driver.findElement(By.id("price")).sendKeys("price");
        driver.findElement(By.id("degree")).sendKeys("degree");
        driver.findElement(By.id("brewery")).sendKeys("brewery");
        driver.findElement(By.id("description")).sendKeys("description");
        WebElement vytvor = driver.findElement(By.id("beerCreate"));
        vytvor.click();
        driver.get("http://127.0.0.1:8000/beers");
        WebElement pivo = driver.findElement(By.tagName("h4"));
        String nazov = "";
        nazov = pivo.getText();

        assertEquals(nazov, "skuska");
        driver.quit();
    }

    @Test
    void pridanieDoKosiku() {

    }

    @Test
    void pridanieTohoIstehoDoKosiku() {

    }

    @Test
    void odstranenieKosiku() {

    }
}
