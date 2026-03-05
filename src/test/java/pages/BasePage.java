package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Página base que encapsula las operaciones comunes de Selenium.
 * Todas las Page Objects heredan de esta clase para abstraerse del API de Selenium.
 */
public class BasePage {
	 protected final WebDriver driver;
	    protected final WebDriverWait wait;

	    private static final int DEFAULT_TIMEOUT_SECONDS = 15;

	    public BasePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
	    }

	    public BasePage(WebDriver driver, int timeoutSeconds) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	    }

	    // ---------- Navegación ----------
	    public void navigateTo(String url) {
	        driver.get(url);
	    }

	    public String getCurrentUrl() {
	        return driver.getCurrentUrl();
	    }

	    public String getTitle() {
	        return driver.getTitle();
	    }

	    public boolean urlContains(String partialUrl) {
	        return driver.getCurrentUrl().contains(partialUrl);
	    }

	    // ---------- Esperas (explícitas) ----------
	    public void waitForVisible(WebElement element) {
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    public void waitForClickable(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

	    public void waitForUrlContains(String partialUrl) {
	        wait.until(ExpectedConditions.urlContains(partialUrl));
	    }

	    public void waitForPresenceOfElement(By locator) {
	        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    }

	    public void waitForElementVisible(By locator) {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    // ---------- Clic y escritura ----------
	    public void click(WebElement element) {
	    	System.out.println("Haciendo clic en: " + element.toString());
	        waitForClickable(element);
	        element.click();
	    }

	    public void clearAndSendKeys(WebElement element, String text) {
	    	System.out.println("Limpiando: " + element.toString() + " y escribiendo: "+ text);
	        waitForVisible(element);
	        element.clear();
	        element.sendKeys(text);
	    }

	    public void sendKeys(WebElement element, String text) {
	    	System.out.println("Escribiendo: "+ text + " en: " + element.toString());
	        waitForVisible(element);
	        element.sendKeys(text);
	    }

	    public void sendKeysEnter(WebElement element) {
	    	System.out.println("Haciendo ENTER sobre : " + element.toString());
	        waitForVisible(element);
	        element.sendKeys(Keys.ENTER);
	    }

	    // ---------- Lectura y estado ----------
	    public String getText(WebElement element) {
	        waitForVisible(element);
	        return element.getText();
	    }

	    public boolean isDisplayed(WebElement element) {
	        try {
	            waitForVisible(element);
	            return element.isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean isSelected(WebElement element) {
	        waitForVisible(element);
	        return element.isSelected();
	    }

	    public boolean isEnabled(WebElement element) {
	        waitForVisible(element);
	        return element.isEnabled();
	    }

	    // ---------- Select (dropdowns) ----------
	    public void selectByVisibleText(WebElement selectElement, String visibleText) {
	        waitForVisible(selectElement);
	        new Select(selectElement).selectByVisibleText(visibleText);
	    }

	    public void selectByValue(WebElement selectElement, String value) {
	        waitForVisible(selectElement);
	        new Select(selectElement).selectByValue(value);
	    }

	    public void selectByIndex(WebElement selectElement, int index) {
	        waitForVisible(selectElement);
	        new Select(selectElement).selectByIndex(index);
	    }

	    // ---------- Búsqueda de elementos (por si se necesita sin @FindBy) ----------
	    public WebElement findElement(By locator) {
	        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    }

	    public List<WebElement> findElements(By locator) {
	        return driver.findElements(locator);
	    }

	    // ---------- Utilidades ----------
	    public WebDriver getDriver() {
	        return driver;
	    }

	    public WebDriverWait getWait() {
	        return wait;
	    }
	    //---- Aserciones -----
	    public boolean isElementDisplayed(WebElement element) {
	        try {
	            waitForVisible(element);
	            return element.isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public String getElementText(WebElement element) {
	        waitForVisible(element);
	        return element.getText();
	    }
	    
	    public String getPageTitle() {
	        return driver.getTitle();
	    }

}
