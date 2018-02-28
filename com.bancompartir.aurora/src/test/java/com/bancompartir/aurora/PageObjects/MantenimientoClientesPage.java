package com.bancompartir.aurora.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;

public class MantenimientoClientesPage extends PageObject{

	public void escribirClienteParaMantenimiento(String strNumeroCliente) {
		findBy("//*[@id='NEWMNT']").type(strNumeroCliente);
	}

	public void hacerClickEnLink(String strTextoParcialLink) {
		getDriver().findElement(By.partialLinkText(strTextoParcialLink)).click();
	}

	public void abrirFormularioInformacionFinancieraDetallada() {
		findBy("//*[@title='Detalle IFD']").waitUntilVisible().click();
	}

	public void llenarFormularioInfoFinancieraPorDefecto() {
		escribirVentasDeContado("40000000");
		escribirVentasACredito("100000");
		escribirGastosDeFabricacion("1000000");
		escribirMateriasPrimasEInsumos("1000000");
		escribirSalariosYManoDeObra("1000000");
		escribirAlquilerDeLocal("0");
		escribirServiciosPublicos("500000");
		escribirGastosFinanciero("0");
		escribirImpuestos("2000000");
		escribirOtrosGastosDeOperacion("0");
		escribirArriendos("1000000");
		escribirConyugeYoHijos("0");
		escribirPensiones("0");
		escribirSueldo("0");
		escribirOtrosIngresos("5000000");
		escribirAlimentacion("500000");
		escribirVivienda("0");
		escribirServciosPublicos("200000");
		escribirTransporte("0");
		escribirEducacion("0");
		escribirSalud("0");
		escribirOtrosEgresos("0");
		escribirCajaYBancos("5000000");
		escribirCuentasPorCobrar("0");
		escribirInventarioMateriaPrima("0");
		escribirInventarioProductoProceso("0");
		escribirInventarioProductoTerminado("0");
		escribirInventarioAgricolas("0");
		escribirInventarioSemovientes("0");
		escribirMaquinariaYEquipo("200000000");
		escribirMueblesYEnseres("80000000");
		escribirTerrenosYEdificios("0");
		escribirVehiculos("40000000");
		escribirDepreciacionAcumulada("0");
		escribirOtrosActivos("0");
		escribirViviendaFincaRaiz("0");
		escribirProveedores("500000");
		escribirObligacionesFinancierasCortoPlazo("300000");
		escribirOtrosPasivosCorrientes("140000");
		escribirObligacionesFinancierasLargoPlazo("1500000");
		escribirOtrosPasivosLargoPlazo("1200000");
		//escribirCapital("285100000"); //calculado
		//escribirUtilidadDelEjercicio("39900000"); //calculado
		escribirUtilidadAcumulada("0");		
	}

	public boolean verificarEnvioExitosoInformacionFinanciera() throws Exception {
		try{Thread.sleep(2000);}catch(Exception e){}
		String mensaje = findBy("//*[@name='D02IFIERRD']").waitUntilVisible().getTextValue();
		if (mensaje.toLowerCase().contains("exitosamente")){
			return true;
		} else {
			throw new Exception("No se pudieron actualizar los datos de Información Financiera");
		} 
	}

	public void enviarFormularioMantenimiento() {
		findBy("//*[@id='EIBSBTN']").click();
	}

	public void verificarEnvioExitosoMantenimiento() throws Exception {
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		String strNombreCampo = getDriver().findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[1]/td")).getText();
		getDriver().manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		if(!strNombreCampo.toLowerCase().contains("actualizado satisfactoriamente")) { 
			throw new Exception("No se actualizó el mantenimiento del cliente");
		}
	}

	public void clickEnCerrar() {
		findBy("//*[@id='EIBSBTN']").click();
	}
	
	
	//-- Métodos para llenado de formulario información financiera detallada
	
	private void escribirVentasDeContado(String strValor) {
		findBy("//input[@name='E02IFIAMT_4']").type(strValor);
	}
	private void escribirVentasACredito(String strValor) {
		findBy("//input[@name='E02IFIAMT_5']").type(strValor);
	}
	private void escribirGastosDeFabricacion(String strValor) {
		findBy("//input[@name='E02IFIAMT_7']").type(strValor);
	}
	private void escribirMateriasPrimasEInsumos(String strValor) {
		findBy("//input[@name='E02IFIAMT_8']").type(strValor);
	}
	private void escribirSalariosYManoDeObra(String strValor) {
		findBy("//input[@name='E02IFIAMT_9']").type(strValor);
	}
	private void escribirAlquilerDeLocal(String strValor) {
		findBy("//input[@name='E02IFIAMT_12']").type(strValor);
	}
	private void escribirServiciosPublicos(String strValor) {
		findBy("//input[@name='E02IFIAMT_13']").type(strValor);
	}
	private void escribirGastosFinanciero(String strValor) {
		findBy("//input[@name='E02IFIAMT_14']").type(strValor);
	}
	private void escribirImpuestos(String strValor) {
		findBy("//input[@name='E02IFIAMT_15']").type(strValor);
	}
	private void escribirOtrosGastosDeOperacion(String strValor) {
		findBy("//input[@name='E02IFIAMT_16']").type(strValor);
	}
	private void escribirArriendos(String strValor) {
		findBy("//input[@name='E02IFIAMT_19']").type(strValor);
	}
	private void escribirConyugeYoHijos(String strValor) {
		findBy("//input[@name='E02IFIAMT_20']").type(strValor);
	}
	private void escribirPensiones(String strValor) {
		findBy("//input[@name='E02IFIAMT_21']").type(strValor);
	}
	private void escribirSueldo(String strValor) {
		findBy("//input[@name='E02IFIAMT_22']").type(strValor);
	}
	private void escribirOtrosIngresos(String strValor) {
		findBy("//input[@name='E02IFIAMT_23']").type(strValor);
	}
	private void escribirAlimentacion(String strValor) {
		findBy("//input[@name='E02IFIAMT_25']").type(strValor);
	}
	private void escribirVivienda(String strValor) {
		findBy("//input[@name='E02IFIAMT_26']").type(strValor);
	}
	private void escribirServciosPublicos(String strValor) {
		findBy("//input[@name='E02IFIAMT_27']").type(strValor);
	}
	private void escribirTransporte(String strValor) {
		findBy("//input[@name='E02IFIAMT_28']").type(strValor);
	}
	private void escribirEducacion(String strValor) {
		findBy("//input[@name='E02IFIAMT_29']").type(strValor);
	}
	private void escribirSalud(String strValor) {
		findBy("//input[@name='E02IFIAMT_30']").type(strValor);
	}
	private void escribirOtrosEgresos(String strValor) {
		findBy("//input[@name='E02IFIAMT_31']").type(strValor);
	}
	private void escribirCajaYBancos(String strValor) {
		findBy("//input[@name='E02IFIAMT_36']").type(strValor);
	}
	private void escribirCuentasPorCobrar(String strValor) {
		findBy("//input[@name='E02IFIAMT_37']").type(strValor);
	}
	private void escribirInventarioMateriaPrima(String strValor) {
		findBy("//input[@name='E02IFIAMT_38']").type(strValor);
	}
	private void escribirInventarioProductoProceso(String strValor) {
		findBy("//input[@name='E02IFIAMT_39']").type(strValor);
	}
	private void escribirInventarioProductoTerminado(String strValor) {
		findBy("//input[@name='E02IFIAMT_40']").type(strValor);
	}
	private void escribirInventarioAgricolas(String strValor) {
		findBy("//input[@name='E02IFIAMT_41']").type(strValor);
	}
	private void escribirInventarioSemovientes(String strValor) {
		findBy("//input[@name='E02IFIAMT_42']").type(strValor);
	}
	private void escribirMaquinariaYEquipo(String strValor) {
		findBy("//input[@name='E02IFIAMT_45']").type(strValor);
	}
	private void escribirMueblesYEnseres(String strValor) {
		findBy("//input[@name='E02IFIAMT_46']").type(strValor);
	}
	private void escribirTerrenosYEdificios(String strValor) {
		findBy("//input[@name='E02IFIAMT_47']").type(strValor);
	}
	private void escribirVehiculos(String strValor) {
		findBy("//input[@name='E02IFIAMT_48']").type(strValor);
	}
	private void escribirDepreciacionAcumulada(String strValor) {
		findBy("//input[@name='E02IFIAMT_49']").type(strValor);
	}
	private void escribirOtrosActivos(String strValor) {
		findBy("//input[@name='E02IFIAMT_51']").type(strValor);
	}
	private void escribirViviendaFincaRaiz(String strValor) {
		findBy("//input[@name='E02IFIAMT_53']").type(strValor);
	}
	private void escribirProveedores(String strValor) {
		findBy("//input[@name='E02IFIAMT_56']").type(strValor);
	}
	private void escribirObligacionesFinancierasCortoPlazo(String strValor) {
		findBy("//input[@name='E02IFIAMT_57']").type(strValor);
	}
	private void escribirOtrosPasivosCorrientes(String strValor) {
		findBy("//input[@name='E02IFIAMT_58']").type(strValor);
	}
	private void escribirObligacionesFinancierasLargoPlazo(String strValor) {
		findBy("//input[@name='E02IFIAMT_60']").type(strValor);
	}
	private void escribirOtrosPasivosLargoPlazo(String strValor) {
		findBy("//input[@name='E02IFIAMT_61']").type(strValor);
	}
	/*private void escribirCapital(String strValor) {
		findBy("//input[@name='E02IFIAMT_64']").type(strValor);
	}*/
	/*private void escribirUtilidadDelEjercicio(String strValor) {
		findBy("//input[@name='E02IFIAMT_65']").type(strValor);
	}*/
	private void escribirUtilidadAcumulada(String strValor) {
		findBy("//input[@name='E02IFIAMT_66']").type(strValor);
	}

	public void escribirNumeroClienteOProspecto(String strCodigoProspectoAccionista) {
		findBy("//input[@name='E01RNU']").type(strCodigoProspectoAccionista);
	}

	public void escribirNumeroAcciones(String strNumeroAcciones) {
		findBy("//input[@name='E01CON']").type(strNumeroAcciones);
	}

	public void hacerClickEnEnviar() {
		findBy("//*[@class='wait-div']").waitUntilNotVisible();
		findBy("//*[@id='EIBSBTN']").click();
		try{Thread.sleep(2000);}catch(Exception e){}
	}

	public void escribirCargo(String strCargo) {
		findBy("//input[@name='E01UC5']").type(strCargo);
	}
	
	public void escribirOcupacion(String strOcupacion) {
		findBy("//input[@name='E01UC9']").type(strOcupacion);
	}

	public boolean verificarExistenciaTablaResultados() {
		return findBy("//table[@class='tableinfo']/tbody/tr[@id='trdark']/td[1]/b").waitUntilPresent().getText().contains("Sel");
	}

}
