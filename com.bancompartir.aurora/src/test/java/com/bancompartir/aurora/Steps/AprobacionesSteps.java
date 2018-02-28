package com.bancompartir.aurora.Steps;

import com.bancompartir.aurora.PageObjects.AprobacionesPage;

import net.thucydides.core.annotations.Step;

public class AprobacionesSteps {

	AprobacionesPage aprobacionesPage;
	
	@Step
	public void ingresarConSegundaClave(String strPassword) {
		aprobacionesPage.escribirSegundaClaveAdmin(strPassword);
		aprobacionesPage.hacerClickEnEnviar();
	}
	
	@Step
	public void ingresarConSegundaClaveParaElAmbiente(String strAmbiente) throws Exception {
		aprobacionesPage.escribirSegundaClaveAdminParaElAmbiente(strAmbiente);
		aprobacionesPage.hacerClickEnEnviar();
	}
	
	@Step
	public void seleccionarCuentaCreada(String strNumeroDeCuenta) { 
		aprobacionesPage.hacerClickEnElementoParaAprobar(strNumeroDeCuenta);
	}
	
	@Step
	public void seleccionarClienteParaAprobar(String strNumeroDeCliente) { 
		aprobacionesPage.hacerClickEnElementoParaAprobar(strNumeroDeCliente);
	}

	@Step
	public void hacerClickEnAprobar() {
		aprobacionesPage.hacerClickEnAprobar();
	}
	
	@Step
	public void hacerClickEnEliminar() {
		aprobacionesPage.hacerClickEnEliminar();
	}

	@Step
	public void verificarCuentaEliminada(String strNumeroDeCuenta) {
		aprobacionesPage.verificarCuentaEliminada(strNumeroDeCuenta);
	}
	
	@Step
	public boolean verificarAprobacion(String strSelector) throws Exception {
		return aprobacionesPage.verificarAprobacionFinal(strSelector);
	}
}
