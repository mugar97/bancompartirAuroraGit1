package com.bancompartir.aurora.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.bancompartir.aurora.Banco.Bancompartir_DB;
import com.bancompartir.aurora.Banco.Bancompartir_variables;
import com.choucair.framework.FW_Web;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreacionCuentasPage extends PageObject {
	
	FW_Web fw_web = new FW_Web();
	Bancompartir_variables vars = new Bancompartir_variables();
	
	public void enfocarSubproductos() {
		getDriver().switchTo().frame("list");
	}
	
	public void enfocarProductos(){
		getDriver().switchTo().frame("detail");
	}
	
	public void hacerClickEnSubproducto(String strSubproducto) throws Exception {
		if (strSubproducto.contains("CCNR")) {
			getDriver().findElement(By.partialLinkText("CUENTA CORRIENTE NO REMUNERADA (CCNR)")).click();
		} else if (strSubproducto.contains("CACN")) {
			//Se comenta porque es la opción por defecto de la cuenta de ahorro
			//getDriver().findElement(By.partialLinkText("CUENTA AHORROS CONTRACTUAL (CACN)")).click();
		} else {
			throw new Exception("No se puede manejar el subproducto " + strSubproducto);
		}
	}
	
	public void hacerClickEnProducto(String strProducto) throws Exception {

		if (strProducto.contains("NRJS"))
		{
			
		}
		else if (strProducto.contains("NRNS")) 
		{
			getDriver().findElement(By.xpath("//input[@value='NRNS']")).click();
		}
		else if (strProducto.contains("NRPJ")) 
		{
			getDriver().findElement(By.xpath("//input[@value='NRPJ']")).click();
		} 
		else if (strProducto.contains("NRPN")) 
		{
			getDriver().findElement(By.xpath("//input[@value='NRPN']")).click();
		} 
		else if (strProducto.contains("COMP")) 
		{
			
		} 
		else if (strProducto.contains("MILG")) 
		{
			getDriver().findElement(By.xpath("//input[@value='MILG']")).click();
		} 
		else if (strProducto.contains("PROP")) 
		{
			getDriver().findElement(By.xpath("//input[@value='PROP']")).click();
		} 
		else 
		{
			try {
				getDriver().findElement(By.xpath("//input[@value='" + strProducto + "']")).click();
			}catch (Exception e) {
				throw new Exception("No se puede manejar el producto " + strProducto + ": " + e.getMessage());
			}finally {
				
			}
		}
	}

	public void hacerClickEnAccionesDeProducto(String strNombreLink) throws Exception {
		if (strNombreLink.equals("Apertura")) {
			getDriver().findElement(By.id("eibsNew")).click();
		} else {
			throw new Exception("No se puede manejar la acción " + strNombreLink);
		}
	}

	public void hacerClickEnBuscarClientesAhorros() {
		getDriver().findElement(By.xpath("//img[@title='Busqueda de Clientes']")).click();
	}
	
	public void hacerClickEnBuscarClientesCorriente() {
		getDriver().findElement(By.xpath("//img[@title='Ayuda']")).click();
	}
	
	
	public void escribirNombreDeLaCuenta_DatosBasicosDeOperacion(String strNombreDeLaCuenta) {
		if (!strNombreDeLaCuenta.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01ACMNME")).clear();
			getDriver().findElement(By.name("E01ACMNME")).sendKeys(strNombreDeLaCuenta);
		}
	}

	public void seleccionarTipoDeCuenta_DatosBasicosDeOperacion(String strTipoDeCuenta) {
		if (!strTipoDeCuenta.equals("<IGNORE>")) {
			new Select(getDriver().findElement(By.name("E01ACMPEC"))).selectByVisibleText(strTipoDeCuenta);
		}
	}

	public void escribirBanco_DatosBasicosDeOperacion(String strBanco) {
		if (!strBanco.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01ACMBNK")).clear();
			getDriver().findElement(By.name("E01ACMBNK")).sendKeys(strBanco);
		}
	}

	public void escribirSucursal_DatosBasicosDeOperacion(String strSucursal) {
		if (!strSucursal.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01ACMBRN")).clear();
			getDriver().findElement(By.name("E01ACMBRN")).sendKeys(strSucursal);
		}
	}

	public void escribirCentroDeCosto_DatosBasicosDeOperacion(String strCentroDeCosto) {
		if (!strCentroDeCosto.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01ACMCCN")).clear();
			getDriver().findElement(By.name("E01ACMCCN")).sendKeys(strCentroDeCosto);
		}
	}

	public void seleccionarUsoDeDomicilio_DatosBasicosDeOperacion(String strUsoDeDomicilio) throws Exception {
		if (!strUsoDeDomicilio.equals("<IGNORE>")) {
			if (strUsoDeDomicilio.toLowerCase().equals("correo electrónico") || strUsoDeDomicilio.toLowerCase().contains("correo"))
			{
				new Select(getDriver().findElement(By.name("E01ACMWHF"))).selectByValue("C");
			} 
			else if (strUsoDeDomicilio.equals("Dirección Fisica"))
			{
				new Select(getDriver().findElement(By.name("E01ACMWHF"))).selectByValue("R");
			}
			else
			{
				throw new Exception("No se puede manejar el selector " + strUsoDeDomicilio);
			}
			
		}
	}

	public void seleccionarTipoEstadoDeCuenta_InformacionEstadoDeCuenta(String strTipoEstadoDeCuenta) {
		if (!strTipoEstadoDeCuenta.equals("<IGNORE>")) {
			new Select(getDriver().findElement(By.name("E01ACMSTY"))).selectByVisibleText(strTipoEstadoDeCuenta);
		}
	}

	public void seleccionarTipoDeChequera_AsignacionChequeras(String strTipoDeChequera) {
		if (!strTipoDeChequera.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01ACMTBK")).clear();
			getDriver().findElement(By.name("E01ACMTBK")).sendKeys(strTipoDeChequera);
		}
	}

	public void abrirMenuOrigenDeFondos() {
		fw_web.clickDerecho(getDriver(),getDriver().findElement(By.name("E01OFFCO1")));
		getDriver().findElement(By.xpath("//span[contains(text(), 'Ayuda')]")).click();
	}
	
	public void buscarConcepto_OrigenDeFondos(String strConcepto) {
		getDriver().findElement(By.linkText(strConcepto)).click();
	}

	public void escribirBanco_OrigenDeFondos(String strBanco) {
		if (!strBanco.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01OFFBK1")).clear();
			getDriver().findElement(By.name("E01OFFBK1")).sendKeys(strBanco);
		}
	}

	public void escribirSucursal_OrigenDeFondos(String strSucursal) {
		if (!strSucursal.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01OFFBR1")).clear();
			getDriver().findElement(By.name("E01OFFBR1")).sendKeys(strSucursal);
		}
	}

	public void escribirMoneda_OrigenDeFondos(String strMoneda) {
		if (!strMoneda.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01OFFCY1")).clear();
			getDriver().findElement(By.name("E01OFFCY1")).sendKeys(strMoneda);
		}
	}

	public void escribirReferencia_OrigenDeFondos(String strReferencia) {
		if (!strReferencia.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01OFFAC1")).clear();
			getDriver().findElement(By.name("E01OFFAC1")).sendKeys(strReferencia);
		}
	}

	public void escribirMonto_OrigenDeFondos(String strMonto) {
		if (!strMonto.equals("<IGNORE>")) {
			getDriver().findElement(By.name("E01OFFAM1")).clear();
			getDriver().findElement(By.name("E01OFFAM1")).sendKeys(strMonto);
		}
	}

	public void hacerClickEnEnviar() {
		getDriver().findElement(By.id("EIBSBTN")).click();
	}

	public void verificarTextoExitosoDePaginaResultado() throws Exception {
		try {
			getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			String strNombreCampo = getDriver().findElement(By.xpath("//table[@class='tableinfo']/tbody/tr/td/table/tbody/tr[3]/td[2]/div")).getText();
			getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
			assertThat(strNombreCampo).contains("Cuenta");
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
			throw new Exception("Se encontraron errores en el formulario: " + strDetalle);
		}
		
	}

	public void guardarDatosGenerados() throws Exception {
		String strNumeroDeCuentaCreado = getDriver().findElement(By.xpath("//table[@class='tableinfo']/tbody/tr/td/table/tbody/tr[3]/td[3]")).getText();
		vars.setNumeroDeCuenta(strNumeroDeCuentaCreado);
	}
	
	public String getNumeroDeCuenta() {
		return vars.getNumeroDeCuenta();
	}

	public void esperarCambioDePagina() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void reiniciarVariables() {
		fw_web = new FW_Web();
		vars = new Bancompartir_variables();
	}

	public void guardarCuentaExitosa(String strID, String strNumeroCuenta) {
		Bancompartir_DB banco = new Bancompartir_DB();
		banco.actualizarNumeroCuenta(strID, strNumeroCuenta);
	}
	
	public void guardarCuentaConError(String strID) {
		Bancompartir_DB banco = new Bancompartir_DB();
		banco.guardaErrorConsultandoCuenta(strID);
	}
}
