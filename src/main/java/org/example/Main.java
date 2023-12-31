package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

    }




    public static void prodanieNovehoPrispevku() {
        try {
            WebDriver driver = new FirefoxDriver();
            driver.get("http://127.0.0.1:8000/beers");
            WebElement pridatPivo = driver.findElement(By.id("BtnAddBeer"));
            pridatPivo.click();
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
            if (nazov == "skuska") {
                System.out.println("Test prodanieNovehoPrispevku bol");
            } else {
                System.out.println("Test prodanieNovehoPrispevku nebol uspesny - pivo nebolo najdene");
            }
            driver.quit();

        } catch (Exception e) {
            System.out.println("Test prodanieNovehoPrispevku nebol uspesny.");
        }
    }

}