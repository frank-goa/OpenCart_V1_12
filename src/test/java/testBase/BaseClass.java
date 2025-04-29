package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass(groups = {"sanity", "regression", "master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {

        //loading properties file
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        properties = new Properties();
        properties.load(file);

        logger = LogManager.getLogger(this.getClass());

        if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
            //code for remote execution
            DesiredCapabilities capabilities = new DesiredCapabilities();
            /*capabilities.setPlatform(Platform.IOS);
            capabilities.setBrowserName("chrome");*/

            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WINDOWS);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("Platform not supported");
            }

            /*if (browser.equalsIgnoreCase("chrome")) {
                capabilities.setBrowserName("chrome");
            } else if (browser.equalsIgnoreCase("firefox")) {
                capabilities.setBrowserName("firefox");
            } else {
                System.out.println("Browser not supported");
            }*/

            switch (browser) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;

                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;

                default:
                    System.out.println("Browser not supported");
                    break;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);


        } else if (properties.getProperty("execution_env").equalsIgnoreCase("local")) {
            // Code for local execution
            switch (browser) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    System.out.println("Browser not supported");
                    break;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        //driver.get("http://localhost/opencart/upload/index.php");
        driver.get(properties.getProperty("appURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass(groups = {"sanity", "regression", "master"})
    public void tearDown() throws InterruptedException {
        //Thread.sleep(3000);
        driver.quit();
    }


    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber() {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphaNumeric() {
        String str = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(4);

        return (str + "@" + num);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        /*
        ====> TakesScreenshot takesScreenshot = (TakesScreenshot) driver; <======
        is used to enable the functionality of capturing screenshots in Selenium WebDriver. In Selenium,
        the TakesScreenshot interface provides a method to capture a screenshot of the current browser window.
        Here, the driver object, which is an instance of WebDriver, is cast to the TakesScreenshot interface.
        This casting is necessary because not all WebDriver implementations inherently support the
        TakesScreenshot interface. For example, browsers like Chrome and Firefox (via their respective drivers)
        implement this interface, allowing screenshots to be taken.
        By casting the driver to TakesScreenshot, you gain access to the getScreenshotAs method,
        which can be used to capture the screenshot and save it in a specified format (e.g., as a file ).
        This is particularly useful for debugging or reporting purposes in automated tests.
         */

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "//screenshots//" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        //move the screenshot from its temporary location (sourceFile) to the specified target location (targetFile)
        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }


}
