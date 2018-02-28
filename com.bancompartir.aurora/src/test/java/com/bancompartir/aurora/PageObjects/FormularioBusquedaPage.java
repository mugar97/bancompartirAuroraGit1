package com.bancompartir.aurora.PageObjects;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacadeImpl;

public class FormularioBusquedaPage extends PageObject{

	public void enfocarFormularioBusquedaEnBusqueda() {
		getDriver().switchTo().frame("query");
	}

	public void hacerClickEnRadioButtonEnBusqueda(String strRadioButton) throws Exception {
		if (strRadioButton.equals("Clientes")) {
			//Se comenta porque es el valor por defecto y el selector está generando error
			//getDriver().findElement(By.cssSelector("input[value='A']")).click();
		} else if (strRadioButton.equals("Entidades")) {
			getDriver().findElement(By.cssSelector("input[value='E']")).click();
		} else if (strRadioButton.equals("Identificación")) {
			getDriver().findElement(By.cssSelector("input[value='I']")).click();
		} else if (strRadioButton.toLowerCase().equals("persona")) {
			//No se hace clic debido a que es el valor por defecto y se puede reducir el tiempo de ejecución
			//getDriver().findElement(By.cssSelector("input[value='PERS']")).click();
		} else if (strRadioButton.toLowerCase().equals("juridico") || strRadioButton.toLowerCase().equals("jurídico")) {
			getDriver().findElement(By.cssSelector("input[value='CORP']")).click();
		} else {
			throw new Exception("No se puede manejar el selector " + strRadioButton);
		}
	}

	public void escribirNumeroIdentificacionEnBusqueda(String strNumeroDeIdentificacion) {
		WebElementFacadeImpl txtId = findBy("//*[@id='XXIDN0']");
		txtId.type(strNumeroDeIdentificacion);
	}

	public void hacerClickEnBuscarEnBusqueda() {
		getDriver().findElement(By.id("ID_SEARCH")).click();
	}

	public void hacerClickEnResultadoEnBusqueda(String strNumeroDeIdentificacion) throws Exception {
		getDriver().switchTo().parentFrame();
		getDriver().switchTo().frame("results");
		Thread.sleep(500);
		if (getDriver().findElements(By.xpath("/html/body/form/table")).size() > 1) //sí hay resultados
		{
			getDriver().findElement(By.linkText(strNumeroDeIdentificacion)).click();
		} else {
			throw new Exception("No se encontró el cliente " + strNumeroDeIdentificacion);
		}
	}

}
