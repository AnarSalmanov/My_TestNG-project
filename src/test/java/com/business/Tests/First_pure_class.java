package com.business.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class First_pure_class {

    @Test
    public void verifyGoogle() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        if (title.equals("Google")) {
            System.out.println("Title is as expected Google");
        } else {
            System.out.println("Title of Google is wrong : " + title);
        }
        driver.get("https://www.milli.az/");
        System.out.println(driver.getTitle());
        driver.navigate().back();
        if (driver.getTitle().contains("Google")) {
            System.out.println("Google title successfully verified");
        }
        driver.close();
        driver.quit();


    }
}