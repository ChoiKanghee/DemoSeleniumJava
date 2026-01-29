package com.demo.pages;

import com.demo.common.BaseTest;
import io.cucumber.datatable.DataTable;
import org.example.constants.ConstantGlobal;
import org.example.driver.DriverManager;
import org.example.keywords.WebUI;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;

public class DemoPage {
    //Khai báo Objects
    private static By homepageIcon = By.xpath("//img[@alt='Guru99 Demo Sites']");
    private static By titleHomePage = By.xpath("//h2[@class='barone']");
    private static By inputEmail = By.xpath("//input[@name='emailid']");
    private static By messageAlert = By.xpath("//label[@id='message9']");
    private static By buttonSubmit = By.xpath("//input[@name='btnLogin']");
    private static By titleHeader = By.xpath("//td[@align='center']//h2");
    private static By seleniumDropDownBtn = By.xpath("(//a[contains(text(), 'Selenium')])[1]");
    private static By seleniumDropDownList = By.xpath("(//ul[@class='dropdown-menu'])[1]//li");


    //Hàm xử lý đặc trưng cho Login Page, action
    public static void visit(){
        BaseTest.createDriver(ConstantGlobal.BROWSER);
        DriverManager.getDriver().manage().window().maximize();
        WebUI.openURL(ConstantGlobal.BASE_URL);
    }

    public static void verifyUrl() {
        WebUI.verifyEquals((ConstantGlobal.BASE_URL),DriverManager.getDriver().getCurrentUrl(),"URL chuyển hướng không đúng");
    }

    public static void verifyHomePage(){
        WebUI.waitForElementPresent(homepageIcon,30);
        WebUI.verifyElementPresent(homepageIcon);
        String titlePage = WebUI.getElementText(titleHomePage);
        WebUI.verifyEquals(titlePage, "Guru99 Bank","title page không đúng");
        String header = WebUI.getElementText(titleHeader);
        WebUI.verifyEquals(header, "Enter your email address to get\naccess details to demo site","title page không đúng");
    }

    public static void leaveBlank() {
        WebUI.waitForElementPresent(inputEmail,30);
        WebUI.clickElement(buttonSubmit);
    }

    public static void verifyErrorMsgPresent(String expectedMessage){
        WebUI.waitForElementVisible(messageAlert,30);
        String actualMessage = WebUI.getElementText(messageAlert);
        WebUI.verifyEquals(actualMessage, expectedMessage,"error msg sai nội dung");
        WebUI.sleep(5000);
    }

    public static void iInputEmail(String email){
        WebUI.waitForElementVisible(inputEmail,30);
        WebUI.setText(inputEmail,email);
        WebUI.sleep(5000);
    }

    public static void openSeleniumDropDownList(){
        WebUI.waitForElementVisible(seleniumDropDownBtn,30);
        WebUI.clickElement(seleniumDropDownBtn);
        WebUI.sleep(5000);
    }

    public static void verifySeleniumDropdownItems(DataTable dataTable) {
        WebUI.waitForElementVisible(seleniumDropDownBtn, 30);
        WebUI.hoverElement(seleniumDropDownBtn);
        WebUI.waitForElementVisible(seleniumDropDownList, 10);
        List<String> expectedItems = dataTable.asList();
        List<WebElement> elements =
                WebUI.getWebElements(seleniumDropDownList);
        List<String> actualItems = elements.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .collect(java.util.stream.Collectors.toList());
        for (String expected : expectedItems) {
            assertTrue(actualItems.contains(expected), "Missing dropdown item: " + expected
            );
        }
    }

    public static void iClickOnMenu(String menuName){
        String menuXpath = "//ul[contains(@class,'navbar-nav')]//a[normalize-space()='" + menuName + "']";
        WebUI.waitForElementVisible(menuXpath);
        WebUI.click(menuXpath);
    }

    public static void verifyNavigateURLPage(String expectedPageURL){
        String actualUrl = DriverManager.getDriver().getCurrentUrl();
        String expected = expectedPageURL.trim().replace("\\", "/").toLowerCase();
        String actual = actualUrl.trim().toLowerCase();
        assertTrue(actual.contains(expected), "URL trả về không đúng");
        System.out.println("URL:" + actual);
    }
}
