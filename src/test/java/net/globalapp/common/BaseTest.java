package net.globalapp.common;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;



public abstract class BaseTest {

	/** Driver*/
	protected WebDriver browser = null;
	protected String baseUrl = null;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", SeleniumParameters.getInstance().getParam("common.driver.chrome.path"));
		browser = new ChromeDriver();
		baseUrl = SeleniumParameters.getInstance().getParam("common.url.base");
		browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void execute() throws IOException {
		
		abrirLaPagina(getInitialPath());
		
		executeTest();
		
		close();
	}
	
	public abstract void executeTest() throws IOException;
	
	public abstract String getInitialPath();

	protected void revisarSiElTagTieneElValor(String tagName, String valor) {
		String bodyText = browser.findElement(By.tagName(tagName)).getText();
		Assert.assertTrue(valor, bodyText.contains(bodyText));
	}

	protected String tomarCapturaDePantallaYGuardelaComo(String nombreDeArchivo) throws IOException {
		String appAbsulotePath = new File(".").getAbsolutePath();
		appAbsulotePath = appAbsulotePath.substring(0,appAbsulotePath.length()-1);
		if(screenShotIsActive()){
			File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(appAbsulotePath.concat("/screeshot/").concat(browser.getClass().getSimpleName()).concat("/").concat(this.getClass().getName()).concat("/"+nombreDeArchivo).concat(".png")));
		}
		return appAbsulotePath;
	}

	private Boolean screenShotIsActive() {
		return Boolean.valueOf(SeleniumParameters.getInstance().getParam("common.screenshot.enabled"));
	}

	protected void abrirLaPagina(String url) {
		browser.get(baseUrl + url);
	}

	protected void escribirEnElCampo(String xPath, String valor) {
		WebElement campoDePassword = browser.findElement(By.xpath(xPath));
		campoDePassword.sendKeys(valor);
	}

	protected void clickAl(String xPath) {
		WebElement botonDeOK = browser.findElement(By.xpath(xPath));
		botonDeOK.click();
	}

	@After
	public void close() {
		browser.quit();
	}
}
