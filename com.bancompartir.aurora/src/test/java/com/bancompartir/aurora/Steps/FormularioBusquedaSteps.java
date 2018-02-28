package com.bancompartir.aurora.Steps;

import com.bancompartir.aurora.PageObjects.FormularioBusquedaPage;

import net.thucydides.core.annotations.Step;

public class FormularioBusquedaSteps {

	FormularioBusquedaPage formularioBusquedaPage;
	
	public void configurarFormularioBusqueda(String strTipoCliente, String strMetodo, String strTipoPersona) throws Exception {
		formularioBusquedaPage.enfocarFormularioBusquedaEnBusqueda();
		formularioBusquedaPage.hacerClickEnRadioButtonEnBusqueda(strTipoCliente);
		formularioBusquedaPage.hacerClickEnRadioButtonEnBusqueda(strMetodo);
		formularioBusquedaPage.hacerClickEnRadioButtonEnBusqueda(strTipoPersona);
	}

	@Step
	public void buscarNumeroDeIdentificacion(String strNumeroDeIdentificacion) throws Exception {
		formularioBusquedaPage.escribirNumeroIdentificacionEnBusqueda(strNumeroDeIdentificacion);
		formularioBusquedaPage.hacerClickEnBuscarEnBusqueda();
	}
	
	@Step
	public void seleccionarResultadoCliente(String strNumeroDeIdentificacion) throws Exception {
		formularioBusquedaPage.hacerClickEnResultadoEnBusqueda(strNumeroDeIdentificacion);
	}
}
