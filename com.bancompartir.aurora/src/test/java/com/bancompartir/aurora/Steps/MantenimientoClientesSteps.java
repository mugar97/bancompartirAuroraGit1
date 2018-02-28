package com.bancompartir.aurora.Steps;

import com.bancompartir.aurora.PageObjects.AplicativoPage;
import com.bancompartir.aurora.PageObjects.MantenimientoClientesPage;

import net.thucydides.core.annotations.Step;

public class MantenimientoClientesSteps {

	MantenimientoClientesPage mantenimientoClientesPage;
	AplicativoPage aplicativoPage;

	@Step
	public void abrirMantenimientoParaCliente(String strNumeroCliente) {
		mantenimientoClientesPage.escribirClienteParaMantenimiento(strNumeroCliente);
		mantenimientoClientesPage.hacerClickEnLink("Nuevo");
	}

	@Step
	public void irAPestanaInformacionFinanciera() {
		mantenimientoClientesPage.hacerClickEnLink("Información Financiera");
	}

	public void abrirFormularioInformacionFinancieraDetallada() {
		mantenimientoClientesPage.abrirFormularioInformacionFinancieraDetallada();
	}

	@Step
	public void diligenciarInformacionFinancieraDetalladaPorDefecto() {
		mantenimientoClientesPage.llenarFormularioInfoFinancieraPorDefecto();
	}

	public void enviarActualizacionInformacionFinanciera() {
		mantenimientoClientesPage.hacerClickEnLink("Enviar");
	}

	@Step
	public boolean verificarIngresoExitosoDeInformacionFinanciera() throws Exception {
		return mantenimientoClientesPage.verificarEnvioExitosoInformacionFinanciera();
	}

	public void salirDeFormularioInformacionFinaciera() {
		mantenimientoClientesPage.hacerClickEnLink("Salir");
	}

	@Step
	public void enviarFormularioMantenimiento() {
		mantenimientoClientesPage.enviarFormularioMantenimiento();
	}

	@Step
	public void verificarEnvioExitosoFormularioMantenimiento() throws Exception {
		mantenimientoClientesPage.verificarEnvioExitosoMantenimiento();
		mantenimientoClientesPage.clickEnCerrar();
	}

	@Step
	public void agregarAccionista(String strCodigoProspectoAccionista) {
		mantenimientoClientesPage.hacerClickEnLink("Crear");
		mantenimientoClientesPage.escribirNumeroClienteOProspecto(strCodigoProspectoAccionista);
		mantenimientoClientesPage.escribirNumeroAcciones("10");
		mantenimientoClientesPage.hacerClickEnEnviar();
	}

	@Step
	public void agregarJuntaDirectiva(String strCodigoClienteJuntaDirectiva) {
		mantenimientoClientesPage.hacerClickEnLink("Crear");
		mantenimientoClientesPage.escribirNumeroClienteOProspecto(strCodigoClienteJuntaDirectiva);
		mantenimientoClientesPage.escribirCargo("EMP");
		mantenimientoClientesPage.escribirOcupacion("ASAL");
		mantenimientoClientesPage.hacerClickEnEnviar();
	}

	@Step
	public void agregarRepresentanteLegal(String strCodigoClienteRepresentanteLegal) {
		mantenimientoClientesPage.hacerClickEnLink("Crear");
		mantenimientoClientesPage.escribirNumeroClienteOProspecto(strCodigoClienteRepresentanteLegal);
		mantenimientoClientesPage.escribirCargo("EMP");
		mantenimientoClientesPage.escribirOcupacion("ASAL");
		mantenimientoClientesPage.hacerClickEnEnviar();
	}

	public boolean verificarExistenciaTablaResultados() {
		return mantenimientoClientesPage.verificarExistenciaTablaResultados();
	}
}
