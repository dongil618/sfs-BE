package com.example.sfs.util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SeleniumUtil {

    /**
     * 창이 2개 일때 나머지 창으로 이동 0, 1
     *
     * @param driver
     * @return
     */
    public WebDriver switchWindow(WebDriver driver, int windowNum) {
        List<String> windowList = new ArrayList<String>(driver.getWindowHandles());
        return driver.switchTo().window(windowList.get(windowNum));
    }

    public String getCurrentWindow(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public WebElement findElementByXpath(WebDriver driver, String xpath) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        return element;
    }

    public WebElement findElementById(WebDriver driver, String id) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));
        return element;
    }

    public WebElement findElementByXpath(WebDriver driver, String xpath, long timeOutInSeconds) {
        WebElement element = new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        return element;
    }

    public WebElement findElementById(WebDriver driver, String id, long timeOutInSeconds) {
        WebElement element = new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));
        return element;
    }

    public void elementClickByXpath(WebDriver driver, String xpath) {
        WebElement clickableElement = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        clickableElement.click();
    }

    public void elementClickById(WebDriver driver, String id) {
        WebElement clickableElement = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(id)));
        clickableElement.click();
    }

    public void elementClickByCssSelector(WebDriver driver, String cssSelector) {
        WebElement clickableElement = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        clickableElement.click();
    }

    public WebElement findVisibilityElementByXpath(WebDriver driver, String xpath) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element;
    }

    public void executeJsByXpath(WebDriver driver, String xpath) throws InterruptedException {
        WebElement element = getPresenceOfElementByXpath(driver, xpath);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        timeSleep(2);
    }

    public void executeClickJsBySelector(WebDriver driver, String cssSelector) throws InterruptedException {
        WebElement element = getPresenceOfElementBySelector(driver, cssSelector);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        timeSleep(2);
    }


    public void scrollTo(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollBy(" + x + "," + y + ")";
        js.executeScript(script, "");
    }

    public void scrollTo(WebDriver driver, int x) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollBy(0," + x + ")";
        js.executeScript(script, "");
    }

    public void setInputDataById(WebDriver driver, String id, String inputData) {
        clipboardCopyString(inputData);
        elementClickById(driver, id);
        timeSleep(driver, 10);
        performCtrlV(driver);
    }

    public void setInputDataByXpath(WebDriver driver, String xpath, String inputData) {
        clipboardCopyString(inputData);
        elementClickByXpath(driver, xpath);
        timeSleep(driver, 10);
        performCtrlV(driver);
    }

    public void uploadImageByXpath(WebDriver driver, String xpath, String imageFilePath) {
        WebElement element = getPresenceOfElementByXpath(driver, xpath);
        element.sendKeys(imageFilePath);
    }

    public void uploadImageListByXpath(WebDriver driver, String imageXpath, String myImageXpath, List<String> detailImageFilePathList) {
        for(String detailImageFilePath : detailImageFilePathList) {
            elementClickByXpath(driver, imageXpath); // 사진 버튼 클릭
            WebElement element = getPresenceOfElementByXpath(driver, myImageXpath);
            element.sendKeys(detailImageFilePath);
        }

    }

    public WebElement getPresenceOfElementByXpath(WebDriver driver, String xpath) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return element;
    }

    public WebElement getPresenceOfElementBySelector(WebDriver driver, String cssSelector) {
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        return  element;
    }

    public void clipboardCopyString(String copyString) {
        System.setProperty("java.awt.headless", "false");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if(copyString != null)
        {
            StringSelection contents = new StringSelection(copyString);
            clipboard.setContents(contents, null);
        }
    }

    public void performCtrlV(WebDriver driver) {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("mac")) {
            new Actions(driver)
                    .keyDown(Keys.COMMAND)
                    .sendKeys("v")
                    .keyUp(Keys.COMMAND)
                    .perform();
        } else {
            new Actions(driver)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("v")
                    .keyUp(Keys.CONTROL)
                    .perform();
        }

    }

    /**
     * 페이지가 로딩될 때 까지 기다리는 time sleep
     * @param driver
     * @param seconds
     */
    public void timeSleep(WebDriver driver, Integer seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * 단순히 몇초 동안 time sleep
     * @param seconds
     * @throws InterruptedException
     */
    public void timeSleep(long seconds) throws InterruptedException {
        long millis = seconds * 1000;
        Thread.sleep(millis);
    }
}
