package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.time.Duration;

public class BaseTest {
	 protected WebDriver driver;
	    protected WebDriverWait wait;

	    @BeforeMethod(alwaysRun = true)
	    @Parameters("browser")
	    public void setUp(@Optional("chrome") String browser) {
	        driver = createDriver(browser);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }

	    private WebDriver createDriver(String browser) {
	        if (browser == null) {
	            browser = "chrome";
	        }
	        switch (browser.toLowerCase()) {
	            case "firefox":
	                WebDriverManager.firefoxdriver().setup();
	                return new FirefoxDriver();
	            case "edge":
	                WebDriverManager.edgedriver().setup();
	                return new EdgeDriver();
	            case "chrome":
	            default:
	                WebDriverManager.chromedriver().setup();
	                return new ChromeDriver();
	        }
	    }
	    @AfterMethod(alwaysRun = true)
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }


}
