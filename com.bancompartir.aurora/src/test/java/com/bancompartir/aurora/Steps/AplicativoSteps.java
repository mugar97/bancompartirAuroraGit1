package com.bancompartir.aurora.Steps;

import com.bancompartir.aurora.PageObjects.AplicativoPage;

import net.thucydides.core.annotations.Step;

public class AplicativoSteps {
	
	AplicativoPage aplicativoPage = new AplicativoPage();
	
	@Step
	public void ingresarAlAplicativoIBS(String strAmbiente) throws Exception {
		aplicativoPage.abrirAplicativoIBS(strAmbiente);
	}
	
	public void hacerClickEnLogin() throws Exception {
		aplicativoPage.hacerClickEnLogin();
		aplicativoPage.enfocarNuevaVentana("Login");
	}

	@Step
	public void hacerLogin(String strUsuario, String strPassword) throws Exception {
		aplicativoPage.escribirUsuario(strUsuario);
		aplicativoPage.escribirPassword(strPassword);
		aplicativoPage.hacerClickEnValidar();
		aplicativoPage.olvidarVentana("Login");
		aplicativoPage.enfocarNuevaVentana("Aplicativo");
	}

	@Step
	public void seleccionarEnSubmenu(String strMenu, String strSubmenu) throws Exception {
		aplicativoPage.hacerClickEnSubmenu(strMenu, strSubmenu);
		aplicativoPage.enfocarSubmenu();
	}
	
	public void cerrar() {
		aplicativoPage.cerrarTodo();
	}
	
	public void cerrarSesion() throws Exception {
		aplicativoPage.enfocarTop();
		aplicativoPage.hacerClickEnCerrarSesion();
		aplicativoPage.olvidarVentana("Aplicativo");
		aplicativoPage.enfocarVentana("Index");
	}

	public void irAFramePadre(){
		aplicativoPage.irAFramePadre();
	}
	
	public void irAFramePrincipal() {
		aplicativoPage.irAFramePrincipal();
	}
	
	public void enfocarFormulario(){
		aplicativoPage.enfocarFormulario();
	}
	
	public void enfocarPrincipal() throws Exception {
		aplicativoPage.enfocarSubmenu();
	}
	
	public void irAVentanaEmergenteBusquedaClientes() throws Exception{
		aplicativoPage.enfocarNuevaVentana("Búqueda de cliente");
	}
	
	public void volverAlFormularioLuegoDeSeleccionarCliente() throws Exception{
		aplicativoPage.olvidarVentana("Búqueda de cliente");
		aplicativoPage.enfocarVentana("Aplicativo");
	}
	
	public void cerrarVentanaBusquedaClientes() throws Exception {
		aplicativoPage.cerrarVentana();
		aplicativoPage.olvidarVentana("Búqueda de cliente");
		aplicativoPage.enfocarVentana("Aplicativo");
	}
	
	public void irAVentanaEmergenteOrigenDeFondos() throws Exception{
		aplicativoPage.enfocarNuevaVentana("Origen de fondos");
	}
	
	public void volverAlFormularioLuegoDeSeleccionarOrigenDeFondos() throws Exception{
		aplicativoPage.olvidarVentana("Origen de fondos");
		aplicativoPage.enfocarVentana("Aplicativo");
		aplicativoPage.enfocarFormulario();
	}
	
	public void salirDeBusquedaYEnfocarFormulario() throws Exception{
		aplicativoPage.cerrarVentanaYEnfocar("Búqueda de cliente", "Aplicativo");
	}

	public void AceptarAlerta() {
		aplicativoPage.aceptarAlerta();
	}

	public void finalizar() {
		aplicativoPage.reiniciarVariables();
	}


	public void enfocarFormularioInformacionFinanciera() throws Exception {
		aplicativoPage.enfocarNuevaVentana("Información Financiera");
	}


	public void volverAlFormularioLuegoDeLlenarInformacionFinanciera() throws Exception {
		aplicativoPage.olvidarVentana("Información Financiera");
		aplicativoPage.enfocarVentana("Aplicativo");
		aplicativoPage.enfocarFormulario();
	}

	@Step
	public void seleccionarOpcionDelMenuOpciones(String strOpcion) {
		aplicativoPage.seleccionarOpcionDelMenuOpciones(strOpcion);
	}

	public void ingresarAlAplicativoConTipoDeUsuario(String strAmbiente, String strTipoUsuario) throws Exception {
		aplicativoPage.abrirAplicativoIBS(strAmbiente);
		aplicativoPage.hacerClickEnLogin();
		aplicativoPage.escribirCredencialesParaElTipoDeUsuario(strAmbiente,strTipoUsuario);
		aplicativoPage.hacerClickEnValidar();
	}

}
