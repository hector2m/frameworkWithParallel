package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
    features = "src/test/resources",
    glue = {"pages", "tests"},
    monochrome = true,
    tags = "@busqueda",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml"
    }
)
public class YoutubeRunner extends AbstractTestNGCucumberTests {
	public static ThreadLocal<String> browserName = new ThreadLocal<>();

	@BeforeClass
    @Parameters("browser")
	public void setupBrowser(String browser) {
		// Guardamos el navegador usando el ID del hilo actual de TestNG
        System.setProperty("browser_" + Thread.currentThread().getId(), browser);
    
    }
	
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
