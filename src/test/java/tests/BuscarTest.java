package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.YoutubePage;
import utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BuscarTest {
	private final WebDriver driver;
	private final YoutubePage youtubePage;

	// Inyección por Constructor: PicoContainer pasa la instancia de Hooks aquí
	public BuscarTest(Hooks hooks) {
		this.driver = hooks.getDriver();
		this.youtubePage = new YoutubePage(this.driver);
	}

	@Given("el usuario esta en la pagina de youtube")
	public void elUsuarioEstaEnLaPaginaDeYoutube() {
		driver.get(ConfigReader.getProperty("url"));
	}

	@When("hace click sobre caja de busqueda")
	public void haceClickSobreCajaDeBusqueda() {
		youtubePage.haceClickSobreCajaDeBusqueda(); 
	}

	@And("escribe una tematica {string}")
	public void escribeUnaTematica(String tema) {
		youtubePage.escribeUnaTematica(tema); 
	}

	@And("confirma la busqueda usando {string}")
	public void confirmaLaBusqueda(String metodo) {
	    if (metodo.equalsIgnoreCase("click")) {
	        youtubePage.haceClickEnIconoDeBusqueda();
	    } else {
	        youtubePage.presionarEnter(); // Deberías crear este método en tu YoutubePage
	    }
	}
	@Then("se muestran videos relacionados")
	public void seMuestranVideosRelacionados() {
		int cantidadVideos = youtubePage.obtenerCantidadDeVideos();
		// 1. Aserción de cantidad de videos
		// La aserción: Si la lista tiene más de 0 elementos, el test pasa.
		// El segundo parámetro es el mensaje que verás si el test falla.
		Assert.assertTrue(cantidadVideos > 0, 
				"ERROR: No se encontraron videos para la búsqueda realizada.");

		System.out.println("Validación exitosa: Se encontraron " + cantidadVideos + " videos.");

		// 2. Aserción del Título
		String titulo = youtubePage.getActualTitle();
		// Validamos que el título contenga la palabra buscada (ej. "selenium")
		// YouTube suele poner "busqueda - YouTube" o similar en el título
		Assert.assertTrue(titulo.toLowerCase().contains("youtube"), 
				"El título de la página no es el esperado. Actual: " + titulo);
	}
}