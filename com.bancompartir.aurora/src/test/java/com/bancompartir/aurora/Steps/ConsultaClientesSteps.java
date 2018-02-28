package com.bancompartir.aurora.Steps;

import com.bancompartir.aurora.PageObjects.ConsultaClientesPage;
import com.bancompartir.aurora.PageObjects.FormularioBusquedaPage;

import net.thucydides.core.annotations.Step;

public class ConsultaClientesSteps {

	ConsultaClientesPage consultaClientesPage;
	FormularioBusquedaPage formularioBusquedaPage;
	
	@Step
	public String leerNumeroDeClienteConsultado() throws Exception {
		return consultaClientesPage.obtenerNumeroCliente();
	}

	
	public void generarArchivoCompiladoDeClientes() {
		
	}


	public void limpiarNumeroCliente() {
		consultaClientesPage.limpiarNumeroCliente();
	}


	public void guardarClienteEnBaseDeDatos(String strID, String strNumeroCliente) {
		System.out.println("Guardando... " + strID + " : " + strNumeroCliente);
		consultaClientesPage.guardarClienteExitoso(strID, strNumeroCliente);
	}


	public void guardarClienteConError(String strID) {
		System.out.println("Guardando con error: " + strID);
		consultaClientesPage.guardarClienteConError(strID);
	}

}
