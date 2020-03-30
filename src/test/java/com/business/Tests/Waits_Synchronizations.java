package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Waits_Synchronizations extends Base {


    @Test              // wait until visible
    public void testExplicitWait() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement editbox = driver.findElement(By.xpath("//form[@id='input-example']//input"));
        //verify by default editbox is disabled
        Assert.assertFalse(editbox.isEnabled());
        //locate dynamic enable button
        WebElement enable = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
        enable.click();
        //After clicking the button we must wait till the editbox to be enabled.
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(editbox));
        // validate the editbox got enabled
        Assert.assertTrue(editbox.isEnabled());
        editbox.sendKeys("Hi World");
        // Click the disable button and wait until it gets disabled
        WebElement disable = driver.findElement(By.xpath("//button[contains(text(),'Disable')]"));
        disable.click();
        wait.until(ExpectedConditions.visibilityOf(enable));
        //verify edit-box is disabled as per Enable button got active
        Assert.assertTrue(enable.isDisplayed());
    }

    @Test
    public void testExplicitWait2() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebElement startBbtn = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
        startBbtn.click();
        WebElement helloWorld = driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]"));
        BrowserUtils.waitFor_Visibility(helloWorld);
        Assert.assertTrue(helloWorld.isDisplayed());
    }

    @Test         // wait untill invisible
    public void waitUntilElementDisappears() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebElement startBbtn = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
        startBbtn.click();
        WebElement loading = driver.findElement(By.xpath("//div[@id='loading']"));
        BrowserUtils.waitFor_In_Visible(loading);
        Assert.assertFalse(loading.isDisplayed());

    }




}
