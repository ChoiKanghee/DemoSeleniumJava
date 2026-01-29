package com.demo.common;

import org.example.constants.ConstantGlobal;
import org.example.driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {

    public static void createDriver() {
        WebDriver driver = setupBrowser("chrome");
        DriverManager.setDriver(driver);
        //PropertiesHelpers.loadAllFiles();
    }

    public static void createDriver(String browserName) {
        WebDriver driver = setupBrowser(browserName);
        DriverManager.setDriver(driver);
        //PropertiesHelpers.loadAllFiles();
    }


    //Viết hàm trung gian để lựa chọn Browser cần chạy với giá trị tham số "browser" bên trên truyền vào
    public static WebDriver setupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    // Viết các hàm khởi chạy cho từng Browser đó
    private static WebDriver initChromeDriver() {
        WebDriver driver;
        System.out.println("Launching Chrome browser...");

//  Chỉ định đường dẫn đến ChromeDriver 2 cách: Chỉ chạy 1 trong 2
//      Cách 1: auto tự động tải webdriver cho máy
//       WebDriverManager.chromedriver().setup();
//      Cách 2 chỉ định và chỉ chạy đúng nơi chứa file webdriver
//         String driverFile= FileHelper.getDriverPath("chromedriver");
//         System.out.println("Check driver path file: "+driverFile);
//         System.setProperty("webdriver.chrome.driver", driverFile);
        WebDriverManager.chromedriver().driverVersion(ConstantGlobal.DRIVE_VERSION).setup();
        // 129.0.6668.100 132.0.6834.159 133.0.6943.53 130.0.6723.116 135.0.7008.0 134.0.6998.36 136.0.7103.92 137.0.7151.70

        ChromeOptions options = new ChromeOptions();
        if (ConstantGlobal.HEADLESS == true)
        {
            options.addArguments("--headless=new");
            //        options.setHeadless(ConstantGlobal.HEADLESS);
            options.addArguments("--disable-gpu");
            options.addArguments("window-size=1800,900");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--verbose");
        }
        // Tự động cho phép truy cập micro
        options.addArguments("use-fake-ui-for-media-stream");

        // Thêm tùy chọn để chạy Chrome ở chế độ ẩn danh
        options.addArguments("--incognito");

        // Tạo một thư mục dữ liệu người dùng tạm thời và duy nhất
//        try {
//            Path tempDir = Files.createTempDirectory("chrome_user_data");
//            options.addArguments("--user-data-dir=" + tempDir.toAbsolutePath().toString());
//            System.out.println("Using temporary user data directory: " + tempDir.toAbsolutePath().toString());
//            tempDir.toFile().deleteOnExit(); // Xóa thư mục khi JVM thoát
//        } catch (Exception e) {
//            System.err.println("Could not create temp directory: " + e.getMessage());
//        }


        driver = new ChromeDriver(options);
        // Print the ChromeDriver path
        String driverPath = System.getProperty("webdriver.chrome.driver");
//        String Path = WebDriverManager.chromedriver().getDownloadedDriverPath();
//        System.out.println("ChromeDriver Path: " + Path);
        System.out.println("ChromeDriver Path: " + driverPath);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        WebDriver driver;
        System.out.println("Launching Edge browser...");
//        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        if (ConstantGlobal.HEADLESS == true)
        {
            options.addArguments("--headless=new");
            options.addArguments("window-size=1800,900");
        }

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        WebDriver driver;
        System.out.println("Launching Firefox browser...");
//        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        if (ConstantGlobal.HEADLESS == true)
        {
            options.addArguments("--headless=new");
//        options.setHeadless(ConstantGlobal.HEADLESS);
            options.addArguments("window-size=1800,900");
        }

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }

}
