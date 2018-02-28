package com.bancompartir.aurora.PageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bancompartir.aurora.Banco.Bancompartir_DB;
import com.choucair.framework.FW_Web;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;

@DefaultUrl("https://10.121.240.71:9443")
@NamedUrls(
	{
		@NamedUrl(name = "eibs_qps", url = "https://10.121.240.71:9443/eibs_qps"),
		@NamedUrl(name = "eibs_bcb", url = "https://10.121.240.71:9443/eibs_bcb"),
		@NamedUrl(name = "eibs_qac", url = "https://10.121.240.71:9443/eibs_qac")
	}
)
public class AplicativoPage extends PageObject{

	FW_Web fw_web = new FW_Web();
	
	public void abrirAplicativoIBS(String strAmbiente) throws Exception {		
		if(strAmbiente.toLowerCase().contains("qps")) {
			open("eibs_qps",withParameters());
		} else if(strAmbiente.toLowerCase().contains("bcb")) {
			open("eibs_bcb",withParameters());
		} else if(strAmbiente.toLowerCase().contains("qac")) {
			open("eibs_qac",withParameters());
		} else {
			throw new Exception("No se encuentra el ambiente " + strAmbiente);
		}
	}
	
	public void hacerClickEnLogin() throws Exception {
		//Se registra esta ventana como Index
		fw_web.registrarVentanaInicial(getDriver(), "Index");
		
		//Click en el botón Login
		getDriver().findElement(By.name("spanish")).click();
		
	}
	
	public void enfocarNuevaVentana(String strVentana) throws Exception {
		//Se identifica la ventana emergente
		fw_web.registrarNuevaVentana(getDriver(), strVentana);
		//Se pasa a controlar la ventana 
		try {
			fw_web.cambiarVentana(getDriver(), strVentana);
		} catch (Exception e) {
			throw new Exception("No se encontró la ventana " + strVentana);
		}	
	}
	
	public void enfocarVentana(String strVentana) throws Exception {
		try {
			fw_web.cambiarVentana(getDriver(), strVentana);
		} catch (Exception e) {
			throw new Exception("No se encontró la ventana " + strVentana);
		}
	}
	
	public void cerrarVentanaYEnfocar(String strVentanaCerrar, String strVentanaEnfocar) throws Exception {
		try {
			fw_web.cerrarVentanaYEnfocaVentana(getDriver(), strVentanaCerrar,strVentanaEnfocar);
		} catch (Exception e) {
			throw new Exception("No se encontró la ventana especificada");
		}
	}
	
	public void olvidarVentana(String strVentana) throws Exception {
		fw_web.olvidarVentanaPorIdentificador(strVentana);
	}

	public void escribirUsuario(String strUsuario) {
		findBy("//*[@name='UserId']").type(strUsuario);
	}

	public void escribirPassword(String strPassword) {
		findBy("//*[@name='Password']").type(strPassword);
	}

	public void hacerClickEnValidar() {
		getDriver().findElement(By.name("imgLogin")).click();
	}
	
	public void hacerClickEnSubmenu(String strMenu, String strSubmenu) throws Exception {
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame("menu");
		String submenuContainer = hacerClickEnMenuConSubmenu(strMenu);
		hacerClickEnSubmenuDentroDeMenu(submenuContainer,strSubmenu);
	}
	
	// Switch/Case de objetos para menú
	public String hacerClickEnMenuConSubmenu(String strMenu) throws Exception {
		
		String submenuContainer = "";
		String menuId = "";
		
		//Switch case según el menú al que se debe hacer click
		if (strMenu.equals("Cuentas de Ahorros") || strMenu.toLowerCase().contains("ahorro")) 
		{
			submenuContainer = "suboptionWEB210";
			menuId = "tdWEB210";
		} 
		else if (strMenu.equals("Cuentas Corrientes") || strMenu.toLowerCase().contains("corriente")) 
		{
			submenuContainer = "suboptionWEB220";
			menuId = "tdWEB220";
		} 
		else if (strMenu.equals("Aprobaciones") || strMenu.toLowerCase().contains("aprobaciones")) 
		{
			submenuContainer = "suboptionWEB020";
			menuId = "tdWEB020";
		} 
		else if (strMenu.equals("Clientes") || strMenu.toLowerCase().contains("clientes")) 
		{
			submenuContainer = "suboptionWEB120";
			menuId = "tdWEB120";
		}  
		else if (strMenu.equals("Préstamos") || strMenu.toLowerCase().contains("préstamos")) 
		{
			submenuContainer = "suboptionWEB400";
			menuId = "tdWEB400";
		} 
		else 
		{
			throw new Exception("No se puede manejar el menú " + strMenu);
		}
		
		//Si el submenú no es visible, hacer clic en el menú
		if(!getDriver().findElement(By.id(submenuContainer)).isDisplayed())
		{
			getDriver().findElement(By.id(menuId)).click();
		}
		
		return submenuContainer;
	}

	// Switch/Case de objetos para submenú
	public void hacerClickEnSubmenuDentroDeMenu(String submenuContainer, String strSubmenu) throws Exception {
		
		if (strSubmenu.equals("Nuevo"))
		{
			WebElement container = getDriver().findElement(By.id(submenuContainer));
			container.findElement(By.partialLinkText("- Nuevo")).click();
		}
		else if (strSubmenu.equals("Cuentas Corrientes") || strSubmenu.toLowerCase().contains("corriente"))
		{
			WebElement container = getDriver().findElement(By.id(submenuContainer));
			container.findElement(By.partialLinkText("- Cuentas Corrientes")).click();
		}
		else if (strSubmenu.equals("Cuentas de Ahorro") || strSubmenu.toLowerCase().contains("ahorro")) 
		{
			WebElement container = getDriver().findElement(By.id(submenuContainer));
			container.findElement(By.partialLinkText("- Cuentas de Ahorro")).click();
		} 
		else if (strSubmenu.equals("Consulta") || strSubmenu.toLowerCase().contains("consulta")) 
		{
			WebElement container = getDriver().findElement(By.id(submenuContainer));
			container.findElement(By.partialLinkText("- Consulta")).click();
		} 
		else 
		{
			try {
				WebElement container = getDriver().findElement(By.id(submenuContainer));
				container.findElement(By.partialLinkText(strSubmenu)).click();
			} catch (Exception e) {
				throw new Exception("No se puede manejar el submenú " + strSubmenu);
			}
			
		}
	}

	public void enfocarSubmenu() {
		getDriver().switchTo().parentFrame();
		getDriver().switchTo().frame("main");
	}
	
	public void irAFramePadre() {
		getDriver().switchTo().parentFrame();
	}
	public void irAFramePrincipal() {
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame("main");
	}
	
	public void enfocarFormulario() {
		getDriver().switchTo().frame("main");
	}
	
	public void cerrarVentana() {
		getDriver().close();
	}
	
	public void cerrarTodo() {
		getDriver().close();
	}

	public void enfocarTop() {
		getDriver().switchTo().parentFrame();
		getDriver().switchTo().frame("top");
	}

	public void hacerClickEnCerrarSesion() {
		getDriver().findElement(By.cssSelector("td[onclick*='log_out']")).click();
	}
	
	public void aceptarAlerta() {
		getDriver().switchTo().alert().accept();
	}
	

	public void reiniciarVariables() {
		fw_web = new FW_Web();
	}

	public void seleccionarOpcionDelMenuOpciones(String strOpcion) {
		findBy("//*[@id='MenuImg']/img").waitUntilVisible().click();
		findBy("//*[@id='Menu']").waitUntilVisible().find(By.partialLinkText(strOpcion)).click();
	}

	public void escribirCredencialesParaElTipoDeUsuario(String strAmbiente, String strTipoUsuario) throws Exception {
		Bancompartir_DB banco = new Bancompartir_DB();
		Map<String, String> mapCredenciales = banco.getCredenciales(strAmbiente);
		
		String strUsuario = "";
		String strPassword = "";
		
		if (strTipoUsuario.toLowerCase().contains("opera")) {
			strUsuario = mapCredenciales.get("usuario");
			strPassword =  mapCredenciales.get("password");
		} else if (strTipoUsuario.toLowerCase().contains("autoriza")) {
			strUsuario = mapCredenciales.get("usuarioAuth");
			strPassword =  mapCredenciales.get("passwordAuth");
		}
		
		escribirUsuario(strUsuario);
		escribirPassword(strPassword);
	}

}
