package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)   
@CucumberOptions(
		features = "src/test/resources",
		glue = {
				"pages",       
				"tests"
		},
		plugin = {
				"pretty",               
				"html:target/cucumber-reports/cucumber.html",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml"  
		},
		monochrome = true,
		// cambiar el tags = por el nombre del feature a ejecutar (archivo.feature)
		tags = "@busqueda")

public class YoutubeRunner_old_sin_parallel {}

