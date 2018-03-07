package com.bancompartir.aurora.Suites;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.bancompartir.aurora.Banco.Bancompartir_DB;
import com.bancompartir.aurora.Steps.AplicativoSteps;
import com.bancompartir.aurora.Steps.AprobacionesSteps;
import com.bancompartir.aurora.Steps.CreacionCuentasSteps;
import com.bancompartir.aurora.Steps.FormularioBusquedaSteps;
import com.choucair.framework.FW_Web;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.annotations.UseTestDataFrom;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("datadriven/CUENTAS_DD.csv")
public class DD_CuentasApertura {

	private String id;
	private String idcta;
	private String analista;
	private String casodeprueba;
	private String clasecta;
	private String sbprd;
	private String prod;
	private String idcliente;
	private String nombcta;
	private String inftipo;
	private String infbco;
	private String infsuc;
	private String infcc;
	private String infdomicilio;
	private String inftipoestado;
	private String tipocheq;
	private String fondconcepto;
	private String fondbco;
	private String fondsuc;
	private String fondmoneda;
	private String fondref;
	private String monto;
	private String ibsnumcta;
	private String fecha;
	private String ejecutado;
	private String ambiente;

	FW_Web fw_web = new FW_Web();
	Bancompartir_DB db = new Bancompartir_DB();

	@Managed
	WebDriver browser;

	static @Steps AplicativoSteps aplicativoSteps;

	static @Steps CreacionCuentasSteps creacionCuentasSteps;

	static @Steps FormularioBusquedaSteps formularioBusquedaSteps;

	static @Steps AprobacionesSteps aprobacionesSteps;

	@Test
	@Screenshots(afterEachStep = true)
	@WithTags({ @WithTag("Aurora:Otros"), @WithTag("Frente:Cuentas"), @WithTag("Theme:Utilidades"),
			@WithTag("Capability:Creación_de_cuentas"), @WithTag("Feature:Creación_Cuentas"),
			@WithTag("Test:Crea_cuenta_para_precondición"), @WithTag("Tipo:Precondiciones"),
			@WithTag("Módulos:Cuentas") })
	public void creacionDeCuentas() throws Exception {

		String strID = getId();

		String strClaseCuenta = getClasecta();

		boolean blnEsCtaCorriente = strClaseCuenta.toLowerCase().contains("corriente");

		String strSubproducto = getSbprd();
		String strProducto = getProd();
		String strIdCliente = getIdcliente();
		String strNombreCuenta = getNombcta();
		String strInfoTipoCuenta = getInftipo();
		String strInfoBanco = getInfbco();
		String strInfoSucursal = getInfsuc();
		String strInfoCentroCostos = getInfcc();
		String strInfoDomicilio = getInfdomicilio();
		String strInfoTipoEstado = getInftipoestado();
		String strTipoCheque = getTipocheq();
		String strFondoConcepto = getFondconcepto();
		String strFondoBanco = getFondbco();
		String strFondoSucursal = getFondsuc();
		String strFondoMoneda = getFondmoneda();
		String strFondoReferencia = getFondref();
		String strFondoMonto = getMonto();
		String strNumeroDeCuenta = getIbsnumcta();
		String strAmbiente = getAmbiente();

		String strAccionFinal = "aprobar";

		Map<String, String> mapCredenciales = db.getCredenciales(strAmbiente);
		String strUsuario = mapCredenciales.get("usuario");
		String strPassword = mapCredenciales.get("password");
		String strUsuarioAuth = mapCredenciales.get("usuarioAuth");
		String strPasswordAuth = mapCredenciales.get("passwordAuth");
		String str2ndPassword = mapCredenciales.get("passwordAuth2");
		
		
		// Dado el ingreso al aplicativo
		aplicativoSteps.ingresarAlAplicativoIBS(strAmbiente);

		// Y se realiza el login con el usuario "QPSTRCOP01" y la contraseña
		// "QPSTRCOP01"
		aplicativoSteps.hacerClickEnLogin();
		aplicativoSteps.hacerLogin(strUsuario, strPassword);

		// Cuando se ingresa al menú "Cuentas Corrientes" y al submenú "Nuevo"
		aplicativoSteps.seleccionarEnSubmenu(strClaseCuenta, "Nuevo");

		// Y se selecciona el subproducto "CUENTA CORRIENTE NO REMUNERADA
		// (CCNR)"
		creacionCuentasSteps.seleccionarSubroducto(strSubproducto);

		// Y se selecciona el producto "Persona natural - NRPN"
		creacionCuentasSteps.seleccionarProducto(strProducto);

		// Y se da click en "Apertura" y se espera la carga del formulario
		creacionCuentasSteps.hacerClickEnAccionesDeProducto("Apertura");

		// Y se hace click en buscar cliente al que se le asignará la cuenta
		// corriente
		if (blnEsCtaCorriente) {
			creacionCuentasSteps.hacerClickEnBuscarClientesCtaCorriente();
		} else {
			creacionCuentasSteps.hacerClickEnBuscarClientesCtaAhorros();
		}

		// Y se va a la ventana emergente de Búsqueda de Clientes
		aplicativoSteps.irAVentanaEmergenteBusquedaClientes();

		// Y se selecciona búsqueda por "Clientes", por "Identificación" y tipo
		// de cliente "Persona"
		formularioBusquedaSteps.configurarFormularioBusqueda("Clientes", "Identificación", "Persona");

		// Y se busca y selecciona el número de identificación "79628340"
		formularioBusquedaSteps.buscarNumeroDeIdentificacion(strIdCliente);
		formularioBusquedaSteps.seleccionarResultadoCliente(strIdCliente);

		// Y se vuelve al formulario
		aplicativoSteps.volverAlFormularioLuegoDeSeleccionarCliente();
		aplicativoSteps.enfocarFormulario();

		// Y se diligencian los Datos básicos de operación. Nombre de la Cuenta:
		// "CTA CORRIENTE CREADA AUTOMATICAMENTE 1", Tipo de Cuenta:
		// "Individual", Banco: "01", Sucursal: "1010", Centro de Costo:
		// "23011010", Uso de domicilio: "Correo Electrónico"
		creacionCuentasSteps.diligenciarDatosBasicosDeOperacion(strNombreCuenta, strInfoTipoCuenta, strInfoBanco,
				strInfoSucursal, strInfoCentroCostos, strInfoDomicilio);

		// Y se diligencia la Información Estado de Cuenta. Tipo Estado de
		// Cuenta: "Personal"
		creacionCuentasSteps.diligenciarInformacionEstadoDeCuenta(strInfoTipoEstado);

		// Y se diligencia la Asignación de Chequeras. Tipo de Chequera: "A1"
		if (blnEsCtaCorriente) {
			creacionCuentasSteps.diligenciarAsignacionChequeras(strTipoCheque);
		}

		// Y se diligencian los datos del Origen de fondos. Concepto: "FONDOS
		// CUENTA AHORROS/CORRIENTE", Banco: "01", Sucursal: "1010", Moneda:
		// "COP", Referencia: "410000000263", Monto: "200000"
		creacionCuentasSteps.abrirAyudaOrigenDeFondos();
		aplicativoSteps.irAVentanaEmergenteOrigenDeFondos();
		creacionCuentasSteps.seleccionarOrigenDeFondos(strFondoConcepto);
		aplicativoSteps.volverAlFormularioLuegoDeSeleccionarOrigenDeFondos();
		creacionCuentasSteps.diligenciarOrigenDeFondos(strFondoBanco, strFondoSucursal, strFondoMoneda,
				strFondoReferencia, strFondoMonto);

		// Y se hace click en Enviar
		creacionCuentasSteps.hacerClickEnEnviar();

		// Y se verifica que se envió la solicitud de creación de cuenta
		creacionCuentasSteps.verificarEnvioSolicitudCreacionDeCuenta();

		// Y se lee el número de cuenta asignado
		creacionCuentasSteps.guardarTemporalmenteNumeroDeCuenta();
		strNumeroDeCuenta = creacionCuentasSteps.devolverNumeroDeCuenta();
		creacionCuentasSteps.esperarCambioDePagina();

		// Y se cierra sesión
		aplicativoSteps.cerrarSesion();

		// Y se realiza el login con el usuario "QPSIBSAC01" y la contraseña
		// "Camilo85"
		aplicativoSteps.hacerClickEnLogin();
		aplicativoSteps.hacerLogin(strUsuarioAuth, strPasswordAuth);

		// Y se ingresa al menú "Aprobaciones" y al submenú "Cuentas Corrientes"
		aplicativoSteps.seleccionarEnSubmenu("Aprobaciones", strClaseCuenta);

		// Y se ingresa con la segunda clave "QPSIBSAC01"
		aprobacionesSteps.ingresarConSegundaClave(str2ndPassword);

		// Y se selecciona la cuenta creada
		aprobacionesSteps.seleccionarCuentaCreada(strNumeroDeCuenta);

		// Y se hace click en Aprobar/Eliminar
		if (strAccionFinal.toLowerCase().equals("aprobar")) {
			aprobacionesSteps.hacerClickEnAprobar();
		} else {
			aprobacionesSteps.hacerClickEnEliminar();
			aplicativoSteps.AceptarAlerta();
		}

		// Entonces se verifica que se la transacción haya sido exitosa
		aprobacionesSteps.verificarCuentaEliminada(strNumeroDeCuenta);

		// Y se cierra sesión
		aplicativoSteps.cerrarSesion();

		if (!(strNumeroDeCuenta.equals("0") || strNumeroDeCuenta.equals("") || strNumeroDeCuenta.isEmpty())) {
			creacionCuentasSteps.guardarCuentaEnBaseDeDatos(strID, strNumeroDeCuenta);
		} else {
			System.out.println("else: strNumeroDeCuenta no válido");
			creacionCuentasSteps.guardarCuentaConError(strID);
			throw new Exception("No se completó la creación de la cuenta");
		}
	}

	@After
	public void reiniciar() throws Exception {
		System.out.println("final test: retomando...");
		creacionCuentasSteps.finalizar();
		aplicativoSteps.finalizar();
	}
	
	@AfterClass
	public static void Resultados() {
		
	}

	public String getId() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getIdcta() {
		return idcta;
	}

	public void setidcta(String idcta) {
		this.idcta = idcta;
	}

	public String getAnalista() {
		return analista;
	}

	public void setanalista(String analista) {
		this.analista = analista;
	}

	public String getCasodeprueba() {
		return casodeprueba;
	}

	public void setcasodeprueba(String casodeprueba) {
		this.casodeprueba = casodeprueba;
	}

	public String getClasecta() {
		return clasecta;
	}

	public void setclasecta(String clasecta) {
		this.clasecta = clasecta;
	}

	public String getSbprd() {
		return sbprd;
	}

	public void setsbprd(String sbprd) {
		this.sbprd = sbprd;
	}

	public String getProd() {
		return prod;
	}

	public void setprod(String prod) {
		this.prod = prod;
	}

	public String getIdcliente() {
		return idcliente;
	}

	public void setidcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombcta() {
		return nombcta;
	}

	public void setnombcta(String nombcta) {
		this.nombcta = nombcta;
	}

	public String getInftipo() {
		return inftipo;
	}

	public void setinftipo(String inftipo) {
		this.inftipo = inftipo;
	}

	public String getInfbco() {
		return infbco;
	}

	public void setinfbco(String infbco) {
		this.infbco = infbco;
	}

	public String getInfsuc() {
		return infsuc;
	}

	public void setinfsuc(String infsuc) {
		this.infsuc = infsuc;
	}

	public String getInfcc() {
		return infcc;
	}

	public void setinfcc(String infcc) {
		this.infcc = infcc;
	}

	public String getInfdomicilio() {
		return infdomicilio;
	}

	public void setinfdomicilio(String infdomicilio) {
		this.infdomicilio = infdomicilio;
	}

	public String getInftipoestado() {
		return inftipoestado;
	}

	public void setinftipoestado(String inftipoestado) {
		this.inftipoestado = inftipoestado;
	}

	public String getTipocheq() {
		return tipocheq;
	}

	public void settipocheq(String tipocheq) {
		this.tipocheq = tipocheq;
	}

	public String getFondconcepto() {
		return fondconcepto;
	}

	public void setfondconcepto(String fondconcepto) {
		this.fondconcepto = fondconcepto;
	}

	public String getFondbco() {
		return fondbco;
	}

	public void setfondbco(String fondbco) {
		this.fondbco = fondbco;
	}

	public String getFondsuc() {
		return fondsuc;
	}

	public void setfondsuc(String fondsuc) {
		this.fondsuc = fondsuc;
	}

	public String getFondmoneda() {
		return fondmoneda;
	}

	public void setfondmoneda(String fondmoneda) {
		this.fondmoneda = fondmoneda;
	}

	public String getFondref() {
		return fondref;
	}

	public void setfondref(String fondref) {
		this.fondref = fondref;
	}

	public String getMonto() {
		return monto;
	}

	public void setmonto(String monto) {
		this.monto = monto;
	}

	public String getIbsnumcta() {
		return ibsnumcta;
	}

	public void setibsnumcta(String ibsnumcta) {
		this.ibsnumcta = ibsnumcta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setfecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEjecutado() {
		return ejecutado;
	}

	public void setejecutado(String ejecutado) {
		this.ejecutado = ejecutado;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
}
