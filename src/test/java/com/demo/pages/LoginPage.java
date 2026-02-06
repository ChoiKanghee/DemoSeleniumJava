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

public class LoginPage {
    //Khai báo Objects
    private static By homepageIcon = By.xpath("//picture");
    private static By comboBox = By.xpath("//textarea[@name='q']");
    private static By buttonSubmit = By.xpath("(//input[@type='submit'])[2]");
    private static By titleSearchResult = By.xpath("//h1[@class='firstHeading mw-first-heading']//i");


    //Hàm xử lý đặc trưng cho Login Page, action
    public static void visit(){
        BaseTest.createDriver(ConstantGlobal.BROWSER);
        DriverManager.getDriver().manage().window().maximize();
        WebUI.openURL("https://www.google.com/");
        WebUI.waitForPageLoaded();
    }

    public static void verifyPage(){
        WebUI.waitForElementVisible(comboBox,30);
        WebUI.verifyElementPresent(comboBox);
    }

    public static void inputText(String text){
        WebUI.waitForElementVisible(comboBox,30);
        WebUI.clearAndSetText(comboBox,text);
        WebUI.sleep(2000);
    }

    public static void clickSubmitBtn(){
        WebUI.waitForElementVisible(buttonSubmit,30);
        WebUI.clickElement(buttonSubmit);
        WebUI.delay(5);
    }
}
