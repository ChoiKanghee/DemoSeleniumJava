package org.example.keywords;

import org.example.constants.ConstantGlobal;
import org.example.driver.DriverManager;
import org.example.helpers.CaptureHelpers;
import org.example.helpers.PropertiesHelpers;
import org.example.helpers.SystemHelpers;
import org.example.reports.AllureManager;
import org.example.reports.ExtentTestManager;
import org.example.utils.LogUtils;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class WebUI {

    private final static long EXPLICIT_TIMEOUT = ConstantGlobal.EXPLICIT_TIMEOUT;
    private final static long STEP_TIME = ConstantGlobal.STEP_TIME;
    private final static long PAGE_LOAD_TIMEOUT = ConstantGlobal.PAGE_LOAD_TIMEOUT;

    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    @Step("Verify Equals: {0} and {1}")
    public static void verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        LogUtils.info("Verify equals: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "Verify equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, "Fail. Not match. '" + actual.toString() + "' != '" + expected.toString() + "'");
    }

    @Step("Verify Equals: {0} and {1}")
    public static void verifyEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        LogUtils.info("Verify equals: " + actual + " and " + expected);
//        ExtentTestManager.logMessage(Status.PASS, "Verify equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Check element existing {0}")
    public static Boolean checkElementExist(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        List<WebElement> listElement = getWebElements(by);

        if (listElement.size() > 0) {
            LogUtils.info("Check Element Exist: " + true + " --- " + by);
            return true;
        } else {
            LogUtils.info("Check Element Exist: " + false + " --- " + by);
            return false;
        }
    }

    @Step("Open URL: {0}")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        LogUtils.info("Open URL: " + url);
//        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);
        AllureManager.saveTextLog("Open URL: " + url);
        waitForPageLoaded();
        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("openURL_" + SystemHelpers.makeSlug(url));
        }
    }

    @Step("Click element {0}")
    public static void clickElement(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Clicked element " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Click element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("clickElement_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Click element {0} with timeout {1}")
    public static void clickElement(By by, int timeout) {
        waitForPageLoaded();
        waitForElementVisible(by, timeout);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Click element " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Click element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("clickElement_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Set text {1} on {0}")
    public static void setText(By by, String value) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text: " + value + " on element " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("setText_" + SystemHelpers.makeSlug(by.toString()));
        }
    }


    @Step("Set text {1} on {0}")
    public static void setTextElementNotVisible(By by, String value) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text: " + value + " on element " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("setText_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Set text {1} on {0} and press key {2}")
    public static void setTextAndKey(By by, String value, Keys key) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value, key);
        LogUtils.info("Set text: " + value + " on element " + by);
//        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("setText_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Get text of element {0}")
    public static String getElementText(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getText();
        LogUtils.info("Get text: " + text);
//        ExtentTestManager.logMessage(Status.PASS, "Get text: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    @Step("Get text of element {0}")
    public static String getElementText(String locator) {
        By by = By.xpath(locator); // Assuming the string is an XPath locator
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getText();
        LogUtils.info("Get text: " + text);
        return text; // Trả về một giá trị kiểu String
    }

    //Wait for Element

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }


    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());

        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    //Vài hàm bổ trợ nâng cao hơn

    @Step("Scroll to element {0}")
    public static void scrollToElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
    }

    @Step("Click to element by js {0}")
    public static void clickByJs(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", getWebElement(by));
    }

    @Step("Scroll to element {0}")
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("scrollToElement_" + SystemHelpers.makeSlug(element.getText()));
        }
    }

    @Step("Scroll to element {0} with type {1}")
    public static void scrollToElement(WebElement element, String type) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(" + type + ");", element);
    }

    @Step("Scroll to position with X={0}, Y={1}")
    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    @Step("Move to element {0}")
    public static boolean moveToElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).release(getWebElement(by)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    @Step("Highlight element {0}")
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    @Step("Hover on element {0}")
    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Mouse hover on element {0}")
    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Reload the current page")
    public static boolean reloadPage() {
        try {
            WebDriver driver = DriverManager.getDriver();
            driver.navigate().refresh();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Drag element {0} to element {1}")
    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Step("Press ENTER on keyboard")
    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Press ESC on keyboard")
    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Press F11 on keyboard")
    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for Page
     * Chờ đợi trang tải xong (Javascript) với thời gian thiết lập sẵn
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    /**
     * Chờ đợi JQuery tải xong với thời gian thiết lập sẵn
     */
    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            LogUtils.info("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    //Wait for Angular Load

    /**
     * Chờ đợi Angular tải xong với thời gian thiết lập sẵn
     */
    public static void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) DriverManager.getDriver()).executeScript(angularReadyScript).toString());
        };

        //Get Angular is Ready
        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());

        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            LogUtils.info("Angular is NOT Ready!");
            //Wait for Angular to load
            try {
                //Wait for jQuery to load
                wait.until(angularLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for Angular load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }

    }

    @Step("Get attribute {1} of element {0}")
    public static String getElementAttribute(By by, String attribute) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        WebElement element = getWebElement(by);
        String attributeValue = element.getAttribute(attribute);
        LogUtils.info("Get attribute '" + attribute + "': " + attributeValue);
//        ExtentTestManager.logMessage(Status.PASS, "Get attribute '" + attribute + "': " + attributeValue);
        return attributeValue; // Trả về một giá trị kiểu String
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static boolean verifyElementNotPresent(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMinutes(10));
            WebElement webElement = WebUI.findElement(xpath);
            wait.until(ExpectedConditions.invisibilityOf(webElement));
            return true;
        } catch (Exception e) {
            // Element is not present
            LogUtils.error("The root cause is: " + e.getMessage());

        }
        return false;
    }

    public static void verifyElementNotPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_TIMEOUT));
            boolean isNotPresent = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            if (!isNotPresent) {
                throw new AssertionError("Element is present: " + by.toString());
            }
        } catch (NoSuchElementException e) {
            // Element is not present
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element to be not present. " + by.toString());
            Assert.fail("Timeout waiting for the element to be not present. " + by.toString());
        }
    }

    public static WebElement findElementOnWebElement(String xpath, WebElement we) {
        try {
//            LogUtils.info("Finding element locator by" +xpath );
            WebElement webElement = we.findElement(By.xpath(xpath));
//            LogUtils.info("Found element locator by" +xpath +"Successfully");
            return webElement;
        } catch (Exception e) {
            LogUtils.info("the root cause is: " + e.getMessage());
            return null;
        }

    }

    public static List<WebElement> findElements(String xpath) {
        try {
//            LogUtils.info("Finding web elements by: "+xpath);
            List<WebElement> elements = DriverManager.getDriver().findElements(By.xpath(xpath));
            return elements;
        } catch (Exception e) {
            LogUtils.error("the root cause is: " + e.getMessage());
            return null;
        }
//
    }

    public static void click(String xpath) {
//        waitForElementVisible(xpath);
        try {
//            LogUtils.info("Clicking web element locator by"+xpath);
            WebElement webElement = findElement(xpath);
            waitForWebElementClickable(webElement);
            webElement.click();
            LogUtils.info("Click an element successfully: "+xpath);
        } catch (Exception e) {
            LogUtils.error("the root cause is: " + e.getMessage());
        }
    }

    @Step("Click element {0}")
    public static void click(WebElement we) {
        try {
//            LogUtils.info("Clicking web element is"+we);
            we.click();
            LogUtils.info("Clicked web element is" + we + "Successfully");
        } catch (Exception e) {
            LogUtils.error("the root cause is: " + e.getMessage());
        }
    }
    //Wait for Element

    public static void waitForElementVisible(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(300), Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + xpath);
            Assert.fail("Timeout waiting for the element Visible. " + xpath);
        }
    }

    public static void waitForWebElementClickable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(300), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element clickable. " + webElement);
            Assert.fail("Timeout waiting for the element clickable. " + webElement);
        }
    }

    public static WebElement findElement(String xpath) {
        waitForPageLoaded();
//        waitForElementVisible(xpath);
        try {
//            LogUtils.info("Finding element");
            WebElement webElement = DriverManager.getDriver().findElement(By.xpath(xpath));
            LogUtils.info("Found element successfully");
            return webElement;
        } catch (Exception e) {
            LogUtils.error("Can not find the element locator by xpath"+xpath+" the root cause is: " + e.getMessage());
        }
        return null;
    }

    public static void sendKey(String xpath, String text) {
        waitForElementVisible(xpath);
        WebElement we = findElement(xpath);
        try {
            LogUtils.info("Sending key on search box");
            we.clear();
            we.sendKeys(text);
        } catch (Exception e) {
            LogUtils.error("the root cause is: " + e.getMessage());
        }
    }

    public static String getTextElement(WebElement we) {
        try {
            String text=we.getText();
            LogUtils.info("got text of an element successfully: "+we);
            return text;
        } catch (Exception e) {
            LogUtils.error("the root cause is: " + e.getMessage());
            return null;
        }

    }

    public static WebElement findWebElement(String xpath) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver()).withTimeout(Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);
            WebElement we = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return DriverManager.getDriver().findElement(By.xpath(xpath));
                }
            });
            return we;
        } catch (Exception e) {
            LogUtils.error("the root cause is: " + e.getMessage());
        }
        return null;
    }

    public static boolean waitForVisibilityOfElementFluentWait(String xpath) {
        // Define the FluentWait, specify the timeout and polling interval
        FluentWait<WebDriver> wait = new FluentWait<>(
                DriverManager.getDriver()).withTimeout(Duration.ofMinutes(5))  // Maximum wait time
                .pollingEvery(Duration.ofSeconds(1))  // Polling frequency
                .ignoring(org.openqa.selenium.NoSuchElementException.class);  // Ignore NoSuchElementException

        try {
            // Wait until the element is visible
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By.xpath(xpath));
                    return element != null && element.isDisplayed();
                }
            });
            return true;
        } catch (TimeoutException e) {
            LogUtils.error("the root cause is: " + e.getMessage());

        }
        return false;
    }

    public static void verifyElementVisible(By by) {
        try {
            WebDriver driver = DriverManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            // If the element is found and visible, log success
            LogUtils.info("Element is visible: " + by.toString());
        } catch (Throwable error) {
            // If the element is not found or not visible within the timeout, log error and fail the test
            LogUtils.error("Timeout waiting for the element to be visible: " + by.toString());
            Assert.fail("Timeout waiting for the element to be visible: " + by.toString());
        }
    }

    public static void verifyElementPresent(By by) {
        try {
            WebDriver driver = DriverManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            // If the element is found, log success
            LogUtils.info("Element is present: " + by.toString());
        } catch (Throwable error) {
            // If the element is not found within the timeout, log error and fail the test
            LogUtils.error("Timeout waiting for the element to be present: " + by.toString());
            Assert.fail("Timeout waiting for the element to be present: " + by.toString());
        }
    }

    // Method to check if an element is displayed
    public static boolean isElementDisplayed(By locator) {
        try {
            WebElement element = DriverManager.getDriver().findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Method to add a delay
    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Method to log comments (optional)
    public static void comment(String message) {
        System.out.println(message);
    }

    @Step("Set text {1} on {0}")
    public static void clearAndSetText(By by, String value) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);

        WebElement element = getWebElement(by);
        element.clear(); // Clear the existing text
        element.sendKeys(value); // Set the new text

        LogUtils.info("Set text: " + value + " on element " + by);
//    ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);

        if (PropertiesHelpers.getValue("SCREENSHOT_STEP").equals("yes")) {
            CaptureHelpers.takeScreenshot("setText_" + SystemHelpers.makeSlug(by.toString()));
        }
    }

    @Step("Clear text from element {0}")
    public static boolean clearText(By by) {
        try {
            WebDriver driver = DriverManager.getDriver();
            WebElement element = getWebElement(by);
            element.clear();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clear text in element {0}")
    public static boolean clearTextByText(By by) {
        try {
            waitForPageLoaded();
            waitForElementVisible(by);
            WebElement element = getWebElement(by);
            String text = element.getText();
            for (int i = 0; i < text.length(); i++) {
                element.sendKeys(Keys.BACK_SPACE);
            }
            LogUtils.info("Cleared text in element " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error("Failed to clear text in element " + by + ". The root cause is: " + e.getMessage());
            return false;
        }
    }

    @Step("Press ENTER on element {0}")
    public static boolean press_ENTER(By by) {
        try {
            waitForPageLoaded();
            waitForElementVisible(by);
            WebElement element = getWebElement(by);
            element.sendKeys(Keys.ENTER);
            LogUtils.info("Pressed ENTER on element " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error("Failed to press ENTER on element " + by + ". The root cause is: " + e.getMessage());
            return false;
        }
    }

}
