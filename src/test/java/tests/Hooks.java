package tests;
import utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class Hooks {
    private WebDriver driver;

    @Before
    @Parameters({"browser"})
    public void setUp() {
    	// 1. Intentamos obtener el navegador del ThreadLocal del Runner
        String browser = runner.YoutubeRunner.browserName.get();
        
     // 2. Si es nulo (ej. ejecución sin TestNG), buscamos en consola o properties
        if (browser == null) {
            browser = System.getProperty("browser");
        }
        if (browser == null) {
            browser = utils.ConfigReader.getProperty("browser");
        }

        // Switch profesional para Cross-Browsing
        switch (browser.toLowerCase()) {
            case "firefox":
                //WebDriverManager.firefoxdriver().setup();
                //driver = new FirefoxDriver();
            	io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                driver = new org.openqa.selenium.firefox.FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
            Integer.parseInt(ConfigReader.getProperty("timeout"))));
    }

    // Método para que PicoContainer inyecte el driver en las otras clases
    public WebDriver getDriver() {
        return driver;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Falla en: " + scenario.getName());
            }
            driver.quit();
        }
    }
}