package com.bancompartir.aurora.Steps;

import com.bancompartir.aurora.PageObjects.AplicativoPage;
import com.bancompartir.aurora.PageObjects.AprobacionesPage;
import com.bancompartir.aurora.PageObjects.CreacionCuentasPage;

import net.thucydides.core.annotations.Step;


public class CreacionCuentasSteps {

	AplicativoPage aplicativoPage;
	CreacionCuentasPage creacionCuentasPage;
	AprobacionesPage aprobacionesPage;
	
	@Step
	public void seleccionarSubroducto(String strSubproducto) throws Exception {
		creacionCuentasPage.enfocarSubproductos();
		creacionCuentasPage.hacerClickEnSubproducto(strSubproducto);
		aplicativoPage.irAFramePadre();
	}
	
	
	@Step
	public void seleccionarProducto(String strProducto) throws Exception {
		creacionCuentasPage.enfocarProductos();
		creacionCuentasPage.hacerClickEnProducto(strProducto);
	}

	@Step
	public void hacerClickEnAccionesDeProducto(String strNombreLink) throws Exception {
		creacionCuentasPage.hacerClickEnAccionesDeProducto(strNombreLink);
	}

	@Step
	public void hacerClickEnBuscarClientesCtaAhorros() throws Exception {
		aplicativoPage.irAFramePrincipal();
		creacionCuentasPage.hacerClickEnBuscarClientesAhorros();
		//aplicativoPage.enfocarNuevaVentana("Búqueda de cliente");
	}

	@Step
	public void hacerClickEnBuscarClientesCtaCorriente() throws Exception {
		aplicativoPage.irAFramePadre();
		creacionCuentasPage.hacerClickEnBuscarClientesCorriente();
		//aplicativoPage.enfocarNuevaVentana("Búqueda de cliente");
	}
	
	@Step
	public void diligenciarDatosBasicosDeOperacion(String strNombreDeLaCuenta, String strTipoDeCuenta, String strBanco,
			String strSucursal, String strCentroDeCosto, String strUsoDeDomicilio) throws Exception {
		creacionCuentasPage.escribirNombreDeLaCuenta_DatosBasicosDeOperacion(strNombreDeLaCuenta);
		creacionCuentasPage.seleccionarTipoDeCuenta_DatosBasicosDeOperacion(strTipoDeCuenta);
		creacionCuentasPage.escribirBanco_DatosBasicosDeOperacion(strBanco);
		creacionCuentasPage.escribirSucursal_DatosBasicosDeOperacion(strSucursal);
		creacionCuentasPage.escribirCentroDeCosto_DatosBasicosDeOperacion(strCentroDeCosto);
		creacionCuentasPage.seleccionarUsoDeDomicilio_DatosBasicosDeOperacion(strUsoDeDomicilio);
	}

	@Step
	public void diligenciarInformacionEstadoDeCuenta(String strTipoEstadoDeCuenta) {
		creacionCuentasPage.seleccionarTipoEstadoDeCuenta_InformacionEstadoDeCuenta(strTipoEstadoDeCuenta);
	}

	@Step
	public void diligenciarAsignacionChequeras(String strTipoDeChequera) {
		creacionCuentasPage.seleccionarTipoDeChequera_AsignacionChequeras(strTipoDeChequera);
	}

	@Step
	public void abrirAyudaOrigenDeFondos() {
		creacionCuentasPage.abrirMenuOrigenDeFondos();
		//aplicativoPage.enfocarNuevaVentana("Origen de fondos");
	}
	
	@Step
	public void seleccionarOrigenDeFondos(String strConcepto) throws Exception {
		creacionCuentasPage.buscarConcepto_OrigenDeFondos(strConcepto);
		//aplicativoPage.olvidarVentana("Origen de fondos");
		//aplicativoPage.enfocarVentana("Aplicativo");
		//aplicativoPage.enfocarFormulario();
	}

	@Step
	public void diligenciarOrigenDeFondos(String strBanco, String strSucursal, String strMoneda,
			String strReferencia, String strMonto) {
		creacionCuentasPage.escribirBanco_OrigenDeFondos(strBanco);
		creacionCuentasPage.escribirSucursal_OrigenDeFondos(strSucursal);
		creacionCuentasPage.escribirMoneda_OrigenDeFondos(strMoneda);
		creacionCuentasPage.escribirReferencia_OrigenDeFondos(strReferencia);
		creacionCuentasPage.escribirMonto_OrigenDeFondos(strMonto);
	}

	@Step
	public void hacerClickEnEnviar() {
		creacionCuentasPage.hacerClickEnEnviar();
	}

	@Step
	public void verificarEnvioSolicitudCreacionDeCuenta() throws Exception {
		creacionCuentasPage.verificarTextoExitosoDePaginaResultado();
	}

	@Step
	public void guardarTemporalmenteNumeroDeCuenta() throws Exception {
		creacionCuentasPage.guardarDatosGenerados(); 
	}
	
	@Step
	public String devolverNumeroDeCuenta() {
		return creacionCuentasPage.getNumeroDeCuenta();
	}

	@Step
	public void esperarCambioDePagina() {
		creacionCuentasPage.esperarCambioDePagina();
	}

	public void finalizar() {
		creacionCuentasPage.reiniciarVariables();
	}
	
	public void guardarCuentaEnBaseDeDatos(String strID, String strNumeroCuenta) {
		System.out.println("Guardando... " + strID + " : " + strNumeroCuenta);
		creacionCuentasPage.guardarCuentaExitosa(strID, strNumeroCuenta);
	}


	public void guardarCuentaConError(String strID) {
		System.out.println("Guardando con error: " + strID);
		creacionCuentasPage.guardarCuentaConError(strID);
	}

}
