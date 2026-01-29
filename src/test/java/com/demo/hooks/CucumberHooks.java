package com.demo.hooks;

import com.demo.common.BaseTest;
import org.example.driver.DriverManager;
import org.example.helpers.PropertiesHelpers;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.example.helpers.ExcelResultWriter;
import org.example.utils.ScenarioResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CucumberHooks {

    private static final ExcelResultWriter writer = new ExcelResultWriter();
    private long startTime;

    @BeforeAll
    public static void beforeAll() {
//        System.out.println("================ beforeAll ================");
        PropertiesHelpers.loadAllFiles();
        //Khởi chạy Report
    }

    @AfterAll
    public static void afterAll() {
//        System.out.println("================ afterAll ================");
//        WebUI.sleep(3000);
        writer.writeReport();
        System.out.println("End of run auto");

    }

    @Before
    public void beforeScenario() {
//        System.out.println("================ beforeScenario ================");
        //BaseTest.createDriver();
        //Record video
        startTime = System.currentTimeMillis();
    }

    @After
    public void afterScenario(Scenario scenario) {
//        System.out.println("================ afterScenario ================");
        BaseTest.closeDriver();
//        DriverManager.quit();
        // Tính thời gian chạy (ms) cho Scenario
        long duration = System.currentTimeMillis() - startTime;

    /* Lấy tên file .feature
       Ví dụ: "file:///D:/proj/features/Login.feature"  -> "Login.feature"
    */
        String feature = scenario.getUri()        // URI của file feature
                .getPath()           // Chuỗi path
                .replace('\\', '/')  // Chuẩn hoá dấu gạch chéo
                .replaceAll(".*/", ""); // Bỏ hết trước dấu '/' cuối

        // Lưu kết quả vào Writer
        writer.add(new ScenarioResult(feature,
                scenario.getName(),          // Tên Scenario
                scenario.getStatus().name(), // "PASSED" hoặc "FAILED"
                duration                     // Thời gian chạy ms
        ));
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
//        System.out.println("================ beforeStep ================");
        //Ghi file log4j
        //Ghi log step vào report
    }



//    @AfterStep
//    public void afterStep(Scenario scenario) {
//        System.out.println("================ afterStep ================");
//        //validate if scenario has failed then Screenshot
////        if (scenario.isFailed()) {
////            CaptureHelpers.takeScreenshot(scenario.getName());
////        }
//
////        if (scenario.isFailed()) {
////
////            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
////            scenario.attach(screenshot, "image/png", "Screenshot Failed");
////        }
//
//        if (scenario.isFailed()) {
//            String screenshotName = scenario.getName().replaceAll(" ", "_") + ".png";
//
//            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
//            try {
//                Files.copy(screenshot.toPath(), Paths.get("reports/" + screenshotName));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            // Capture HTML contentString htmlContent = driver.getPageSource();
//            String htmlFileName = scenario.getName().replaceAll(" ", "_") + ".html";
//            try {
//                String htmlContent = DriverManager.getDriver().getPageSource();
//                Files.write(Paths.get("reports/" + htmlFileName), htmlContent.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @AfterStep
    public void afterStep(Scenario scenario) {
//        System.out.println("================ afterStep ================");
        //validate if scenario has failed then Screenshot
//        if (scenario.isFailed()) {
//            CaptureHelpers.takeScreenshot(scenario.getName());
//        }

//        if (scenario.isFailed()) {
//
//            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", "Screenshot Failed");
//        }

        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_") + ".png";

            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshot.toPath(), Paths.get("reports/" + screenshotName));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            // Capture HTML contentString htmlContent = driver.getPageSource();
//            String htmlFileName = scenario.getName().replaceAll(" ", "_") + ".html";
//            try {
//                String htmlContent = DriverManager.getDriver().getPageSource();
//                Files.write(Paths.get("reports/" + htmlFileName), htmlContent.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
