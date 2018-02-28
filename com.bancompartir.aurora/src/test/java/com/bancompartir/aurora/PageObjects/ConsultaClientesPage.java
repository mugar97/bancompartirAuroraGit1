package com.bancompartir.aurora.PageObjects;

import org.openqa.selenium.By;

import com.bancompartir.aurora.Banco.Bancompartir_DB;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class ConsultaClientesPage extends PageObject {

	public String leerNumeroCliente() {
		getDriver().switchTo().frame("main");
		String strOut = getDriver().findElement(By.name("E01ACMCUN")).getAttribute("value");
		return strOut;
	}

	public String obtenerNumeroCliente() throws Exception {
		getDriver().switchTo().parentFrame();
		getDriver().switchTo().frame("results");
		String out;
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		Serenity.takeScreenshot();
		if (getDriver().findElements(By.xpath("/html/body/form/table")).size() > 1) //sí hay resultados
		{
			out = getDriver().findElement(By.xpath("/html/body/form/table[1]/tbody/tr[2]/td[1]/a")).getText();
			getDriver().switchTo().defaultContent();
		} else {
			out = "Error: No se encontró el cliente";
			getDriver().switchTo().defaultContent();
			throw new Exception("Error: No se encontró el cliente");
		}
		return out;
	}
	
	public void limpiarNumeroCliente() {
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame("main");
		getDriver().findElement(By.name("E01ACMCUN")).clear();
	}

	public void guardarClienteExitoso(String strID, String strNumeroCliente) {
		Bancompartir_DB banco = new Bancompartir_DB();
		banco.actualizarNumeroCliente(strID, strNumeroCliente);
	}

	public void guardarClienteConError(String strID) {
		Bancompartir_DB banco = new Bancompartir_DB();
		banco.guardaErrorConsultandoCliente(strID);
	}

	
}
