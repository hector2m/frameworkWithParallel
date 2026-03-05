package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

public class TestListener implements ITestListener {

	// ThreadLocal para almacenar el navegador por hilo
	private static ThreadLocal<String> browserThreadLocal = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		// Se ejecuta antes de cada método de prueba (cada escenario de Cucumber)
		ITestContext context = result.getTestContext();
		XmlTest xmlTest = context.getCurrentXmlTest();
		String browser = xmlTest.getParameter("browser");
		if (browser != null) {
			browserThreadLocal.set(browser);
		}
	}

	// Método estático para que Hooks pueda obtener el navegador
	public static String getBrowser() {
		return browserThreadLocal.get();
	}

	// Opcional: limpiar el ThreadLocal al finalizar el test
	@Override
	public void onTestSuccess(ITestResult result) {
		browserThreadLocal.remove();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		browserThreadLocal.remove();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		browserThreadLocal.remove();
	}
}


