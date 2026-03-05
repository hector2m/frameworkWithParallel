package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YoutubePage extends BasePage {
	//private WebDriver driver;

    //elementos 
    @FindBy(xpath = "//input[@name = \"search_query\"]")
    private WebElement inputTxt;
    @FindBy(xpath = "//button[@aria-label = \"Search\"]")
    private WebElement btnBuscar;
    // este elemento demustra que se listan videos luego de la busqueda
    @FindBy(xpath = "//ytd-video-renderer") // Selecciona los contenedores de videos
    private List<WebElement> listaDeVideos;

    

    //constructor de este objeto
    public YoutubePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
	//"hace click sobre caja de busqueda"
	public void haceClickSobreCajaDeBusqueda() {
		click(inputTxt);
	}
	//"escribe una tematica {string}"
	public void escribeUnaTematica(String txt) {
		clearAndSendKeys(inputTxt,txt);
		
	}
	//"hace click en icono de busqueda"
	public void haceClickEnIconoDeBusqueda() {
		click(btnBuscar);
	}
	//"presiona enter"
	public void presionarEnter() {
		inputTxt.sendKeys(Keys.ENTER);
	}				
	//"se muestran videos relacionados"
	public void seMuestranVideosRelacionados() {
		
	}
	// lista de videos luego de busqueda para asercion (Assertion)
	public int obtenerCantidadDeVideos() {
        return listaDeVideos.size();
    }
	// ---- Asercion con titulo de pagina
	public String getActualTitle() {
	    return getPageTitle();
	}
    

}
