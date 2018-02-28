package com.bancompartir.aurora.Definitions;

import com.bancompartir.aurora.Steps.PruebasSteps;
import cucumber.api.PendingException;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.thucydides.core.annotations.Steps;

public class PruebasDefinition {

	@Steps
	PruebasSteps pruebasSteps;
	
	@Dada("^precondicion$")
	public void precondicion() throws Exception {
	    pruebasSteps.precondicion();
	}

	@Cuando("^ejecuto paso$")
	public void ejecuto_paso() throws Exception {
		pruebasSteps.ejecuto_paso();
	}

	@Entonces("^verifico$")
	public void verifico() throws Exception {
		pruebasSteps.verifico();
	}
	
	@Entonces("^verifico fallido$")
	public void verifico_fallido() throws Exception {
		throw new Exception();
	}
	
	@Entonces("^verifico pendiente$")
	public void verifico_pendiente() throws Exception {
		throw new PendingException();
	}
	
	/*
	 * WebServices
	 */
	
	@Dado("^que se consume el servicio base$")
	public void que_se_consume_el_servicio_base() throws Exception {
	   pruebasSteps.consumirElServicioBase(); 
	}

	@Cuando("^se verifica el estado$")
	public void se_verifica_el_estado() throws Exception {
		pruebasSteps.verificarElEstado(); 
	}

	@Entonces("^se valida que el campo tenga un valor$")
	public void se_valida_que_el_campo_tenga_un_valor() throws Exception {
		pruebasSteps.validarValorEnRespuesta();
	}
}
