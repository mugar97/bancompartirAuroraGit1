package com.bancompartir.aurora.PageObjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bancompartir.aurora.Banco.Bancompartir_DB;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AprobacionesPage extends PageObject {
	
	public void escribirSegundaClaveAdmin(String strPassword) {
		getDriver().findElement(By.name("AppPassword")).clear();
		getDriver().findElement(By.name("AppPassword")).sendKeys(strPassword);;
	}
	
	public void escribirSegundaClaveAdminParaElAmbiente(String strAmbiente) throws Exception {
		
		Bancompartir_DB banco = new Bancompartir_DB();
		Map<String, String> mapCredenciales = banco.getCredenciales(strAmbiente);
		
		String strPassword = mapCredenciales.get("passwordAuth2");
		findBy("//*[@name='AppPassword']").type(strPassword);
	}
	
	public void hacerClickEnEnviar() {
		getDriver().findElement(By.id("EIBSBTN")).click();
	}
	
	public void hacerClickEnElementoParaAprobar(String strNumeroDeCuenta) {
		getDriver().findElement(By.cssSelector("input[value='"+ strNumeroDeCuenta +"']")).click();
	}

	public void hacerClickEnAprobar() {
		findBy("//*[@id='linkApproval']").click();
	}
	
	public void hacerClickEnEliminar() {
		getDriver().findElement(By.cssSelector("img[title='Eliminar']")).click();
	}

	public void verificarCuentaEliminada(String strNumeroDeCuenta) {
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		List<WebElement> lst = getDriver().findElements(By.cssSelector("input[value='"+ strNumeroDeCuenta +"']"));
		getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		assertThat(lst.isEmpty()).isTrue().describedAs("No se completó la acción sobre la cuenta No. " + strNumeroDeCuenta);
	}

	public boolean verificarAprobacionFinal(String strSelector) throws Exception {
		try {
			getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			getDriver().findElement(By.id("id")).isDisplayed();
			List<WebElement> lst = getDriver().findElements(By.cssSelector("input[value='"+ strSelector +"']"));
			getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
			
			if(lst.isEmpty()){
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			String strDetalle = "";
			try {
				Set<String> hs = getDriver().getWindowHandles();
				getDriver().switchTo().window(hs.toArray(new String[hs.size()])[hs.size()-1]);
				List<WebElementFacade> errores = findAll("//td[@align='left']/a[@href]");
				for (WebElementFacade error : errores) {
					strDetalle = strDetalle + System.getProperty("line.separator") + error.getText();
				}
				Serenity.takeScreenshot();
				
			} catch (Exception ex) {}
			getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
			throw new Exception("Se encontraron errores en la aprobación: " + strDetalle);
		}
	}
}
