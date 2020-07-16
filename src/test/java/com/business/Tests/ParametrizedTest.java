package com.business.Tests;

import com.business.Utilities.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class ParametrizedTest extends Base {
    private static Logger log = LogManager.getLogger(ParametrizedTest.class.getName());

    //Parameter means this is applicable for this parameter only, put same value in .xml file
    @Parameters({"Url", "Login", "Password"})
    @Test(enabled = false)
    public void ParametrizedTest(@Optional String url, String login, String password) throws InterruptedException {
        extentLogger = extentReports.createTest("Parametrized test in testNg.xml file");
        driver.get(url);
        log.info("navigating to :" + url);
        driver.findElement(By.id("txtUsername")).sendKeys(login);
        driver.findElement(By.id("txtPassword")).sendKeys(password + Keys.ENTER);
        Actions actions = new Actions(driver);
        WebElement leave = driver.findElement(By.xpath("//b[text()='Leave']"));
        actions.moveToElement(leave).build().perform();
        WebElement leaveList = driver.findElement(By.linkText("Leave List"));
        leaveList.click();
        WebElement fromDateEditbox = driver.findElement(By.id("calFromDate"));
        fromDateEditbox.click();
        WebElement fromMonthDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
        Select selectFromMonth = new Select(fromMonthDropdown);
        selectFromMonth.selectByVisibleText("Sep");
        Thread.sleep(1000);
        WebElement fromYearDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
        Select selectYearDropdown = new Select(fromYearDropdown);
        selectYearDropdown.selectByValue("2022");
        Thread.sleep(1000);
        List<WebElement> days = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td//a"));
        for (WebElement day : days) {
            if (day.getText().contains("23")) {
                day.click();
                break;
            }
        }
        Thread.sleep(1000);
        Assert.assertTrue(fromDateEditbox.getAttribute("value").contains("2022-09-23"));
        log.info("verified editBox contains given value");
    }
}
