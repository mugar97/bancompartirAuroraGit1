package com.bancompartir.aurora.Suites;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.bancompartir.aurora.ActualizarDD;
import com.bancompartir.aurora.Banco.Bancompartir_DB;
import com.bancompartir.aurora.Steps.AplicativoSteps;
import com.bancompartir.aurora.Steps.AprobacionesSteps;
import com.bancompartir.aurora.Steps.ConsultaClientesSteps;
import com.bancompartir.aurora.Steps.FormularioBusquedaSteps;
import com.bancompartir.aurora.Steps.MantenimientoClientesSteps;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.annotations.UseTestDataFrom;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("datadriven/CLIENTES_DD.csv")
public class DD_ClientesAdicionInfoFinanciera {
	private String id;
	private String idcli;
	private String analista;
	private String casodeprueba;
	private String e01pid;
	private String e01lgt;
	private String e01tid;
	private String e01idn;
	private String e01fna;
	private String e01fn2;
	private String e01ln1;
	private String e01ln2;
	private String e01sex;
	private String e01na1;
	private String e01nm6;
	private String e01uc2;
	private String e01mst;
	private String e01uc3;
	private String e01uc4;
	private String e01bdd;
	private String e01bdm;
	private String e01bdy;
	private String e01gec;
	private String e01nso;
	private String e01n5d;
	private String e01red;
	private String e01rem;
	private String e01rey;
	private String e01n5p;
	private String e01n5c;
	private String e01n3p;
	private String e01n3d;
	private String e01n3c;
	private String e01ccs;
	private String e01tx8;
	private String e01obc;
	private String e01edl;
	private String e01uc9;
	private String e01uc6;
	private String e01nem;
	private String e01sst;
	private String e01fl8;
	private String e01alb;
	private String e01tx1;
	private String e01tx4;
	private String e01tx5;
	private String e01tx3;
	private String e01tx2;
	private String e01uc7;
	private String e01adc;
	private String e01ste;
	private String e01ctc;
	private String e01na2;
	private String e01tud;
	private String e01mun;
	private String e01par;
	private String e01fg1;
	private String e01inl;
	private String e01fl5;
	private String e01tvy;
	private String e01tvm;
	private String e01pho;
	private String e01pht;
	private String e01ccc;
	private String e01soi;
	private String e01cob;
	private String e01phn;
	private String e01pnt;
	private String e01iad;
	private String e01iat;
	private String e01fl2;
	private String e01ds2;
	private String e01fx;
	private String e01fxp;
	private String e01mrp;
	private String e01pep;
	private String e01fl4;
	private String e01rcp;
	private String e1rrtp;
	private String e1rma1;
	private String e1rnm3;
	private String e1rnm4;
	private String e1rnm5;
	private String e1rpht;
	private String e1rhpn;
	private String e1rctr;
	private String e1rste;
	private String e1rctc;
	private String e01idd;
	private String e01idm;
	private String e01idy;
	private String e01bra;
	private String e01ofc;
	private String e01of2;
	private String d01uc5;
	private String e01fl6;
	private String e01rby;
	private String e01stf;
	private String e01tse;
	private String e01typ;
	private String e01lif;
	private String e01ilv;
	private String e01a1d;
	private String e01a1m;
	private String e01a1y;
	private String e01fl3;
	private String e01pi4;
	private String e01ti4;
	private String e01uc1;
	private String e01id4;
	private String e01cnm;
	private String e01dio;
	private String e01ona;
	private String e01icc;
	private String e01ren;
	private String e01rbn;
	private String e01web;
	private String e01enf;
	private String e01bol;
	private String e01inf;
	private String e01cso;
	private String e01if1;
	private String e01reo;
	private String ibsnumcli;
	private String fecha;
	private String ejecutado;
	private String ccty1;
	private String bln_consultado;
	private String bln_mantenimiento;
	private String bln_infofinanciera;
	private String bln_accionistas;
	private String bln_juntadirectiva;
	private String bln_representante;
	private String bln_aprobado;
	private String ambiente;
	
	@Managed
	WebDriver browser;
	
	static
	@Steps
	AplicativoSteps aplicativoSteps;
	
	static
	@Steps
	FormularioBusquedaSteps formularioBusquedaSteps;
	
	static
	@Steps
	ConsultaClientesSteps consultaClientesSteps;
	
	static
	@Steps
	MantenimientoClientesSteps mantenimientoClientesSteps;
	
	static
	@Steps
	AprobacionesSteps aprobacionesSteps;
	
	Bancompartir_DB db = new Bancompartir_DB();
	
	@BeforeClass
	public static void antes() {
		ActualizarDD actualizarDD = new ActualizarDD();
		actualizarDD.actualizarClientesMasivos();
	}
	
	@Test
	@Screenshots(afterEachStep=true)
	@WithTags (
		{
			@WithTag("Aurora:Otros"),
			@WithTag("Frente:Clientes"),
			@WithTag("Theme:Utilidades"),
			@WithTag("Capability:Consulta_de_clientes"),
			@WithTag("Feature:Consulta_Clientes"),
			@WithTag("Test:Consultar_clientes_para_precondición"),
			@WithTag("Tipo:Precondiciones"),
			@WithTag("Módulos:Clientes")
		}
	)
	public void consulta_de_cliente() throws Exception {
		System.out.println("test: " + getE01idn());
		
		String strID = String.valueOf(getId());
		String strTipoCliente = "Clientes";
		String strMetodo = "Identificación";
		String strTipoPersona = getStrTipoPersona();
		String strNumeroDeIdentificacion = getE01idn(); //Número de identificación
		String strNumeroCliente = getIbsnumcli();
		String strAmbiente = getAmbiente();
		
		boolean blnConsultado = bln_consultado.equals("1");
		boolean blnMantenimiento = bln_mantenimiento.equals("1");
		boolean blnInfofinanciera = bln_infofinanciera.equals("1");
		boolean blnAccionistas = bln_accionistas.equals("1");
		boolean blnJuntadirectiva = bln_juntadirectiva.equals("1");
		boolean blnRepresentante = bln_representante.equals("1");
		boolean blnAprobado= bln_aprobado.equals("1");
		

		Map<String, String> mapCredenciales = db.getCredenciales(strAmbiente);
		String strUsuario = mapCredenciales.get("usuario");
		String strPassword = mapCredenciales.get("password");
		String strUsuarioAuth = mapCredenciales.get("usuarioAuth");
		String strPasswordAuth = mapCredenciales.get("passwordAuth");
		String str2ndPassword = mapCredenciales.get("passwordAuth2");
		
		
		String strCodigoProspectoAccionista = mapCredenciales.get("CodigoProspectoAccionista");
		String strCodigoClienteJuntaDirectiva = mapCredenciales.get("CodigoClienteJuntaDirectiva");
		String strCodigoClienteRepresentanteLegal = mapCredenciales.get("CodigoClienteRepresentanteLegal");
		
		//Dado el ingreso al aplicativo
		System.out.println("Dado el ingreso al aplicativo");
		aplicativoSteps.ingresarAlAplicativoIBS(strAmbiente);
		
		//Y se realiza el login con el usuario "QPSTRCOP01" y la contraseña "QPSTRCOP01"
		System.out.println("Y se realiza el login con el usuario \"" + strUsuario + "\" y la contraseña \"" + strPassword + "\"");
		aplicativoSteps.hacerClickEnLogin();
		aplicativoSteps.hacerLogin(strUsuario,strPassword);
		
		
		// --- Consulta número de cliente 
		if(!StringUtils.isNumeric(strNumeroCliente) || !blnConsultado){
		    //Cuando se ingresa al menú "Clientes" y al submenú "Consulta"
			System.out.println("Cuando se ingresa al menú \"Clientes\" y al submenú \"Consulta\"");
			aplicativoSteps.seleccionarEnSubmenu("Clientes", "Consulta");
			
			//Se configura el formulario según el cliente
			System.out.println("Se configura el formulario según el cliente");
			formularioBusquedaSteps.configurarFormularioBusqueda(strTipoCliente, strMetodo, strTipoPersona);
			
			//Se busca al cliente
			System.out.println("Se busca al cliente");
			formularioBusquedaSteps.buscarNumeroDeIdentificacion(strNumeroDeIdentificacion);
			
			//Se intenta guardar el número del cliente
			System.out.println("Se intenta guardar el número del cliente");
			strNumeroCliente = consultaClientesSteps.leerNumeroDeClienteConsultado();
			
			if (!(strNumeroCliente.toLowerCase().contains("error") || strNumeroCliente.isEmpty() || strNumeroCliente.equals(""))) {
				//Se guardan los resultados exitosos
				System.out.println("Se guardan los resultados exitosos: "+ strID + "" + strNumeroCliente);
				consultaClientesSteps.guardarClienteEnBaseDeDatos(strID,strNumeroCliente);
				blnConsultado = true;
				db.guardaValorEnCampoCliente(strID, "BLN_CONSULTADO", "1");
			} else {
				System.out.println("else: strNumeroCliente no válido");
				consultaClientesSteps.guardarClienteConError(strID);
				throw new Exception(strNumeroCliente);
			}
		} else {
			blnConsultado = true;
			db.guardaValorEnCampoCliente(strID, "BLN_CONSULTADO", "1");
		}
		//--- Fin Consulta
		
		//--- Mantenimiento
		if(!blnMantenimiento) {
			//Se ingresa al menú Clientes / Mantenimiento
			System.out.println("Se ingresa al menú Clientes / Mantenimiento");
			aplicativoSteps.seleccionarEnSubmenu("Clientes", "Mantenimiento");
	
			//Se abre el mantenimiento para el cliente 
			System.out.println("Se abre el mantenimiento para el cliente " + strNumeroCliente);
			mantenimientoClientesSteps.abrirMantenimientoParaCliente(strNumeroCliente);
	
			if(!blnInfofinanciera) {
				//Se abre la información detallada del cliente
				System.out.println("Se abre la información detallada del cliente");
				mantenimientoClientesSteps.irAPestanaInformacionFinanciera();
				mantenimientoClientesSteps.abrirFormularioInformacionFinancieraDetallada();
				aplicativoSteps.enfocarFormularioInformacionFinanciera();
		
				//Se diligencia la infromación financiera detallada del cliente
				System.out.println("Se diligencia la infromación financiera detallada del cliente");
				mantenimientoClientesSteps.diligenciarInformacionFinancieraDetalladaPorDefecto();
				mantenimientoClientesSteps.enviarActualizacionInformacionFinanciera();
		
				//Se verifica que la información se diligenció exitosamente
				System.out.println("Se verifica que la información se diligenció exitosamente");
				blnInfofinanciera = mantenimientoClientesSteps.verificarIngresoExitosoDeInformacionFinanciera();
				mantenimientoClientesSteps.salirDeFormularioInformacionFinaciera();
				aplicativoSteps.volverAlFormularioLuegoDeLlenarInformacionFinanciera();
				
				//Se actualiza la bandera de Información Financiera
				if(blnInfofinanciera){
					db.guardaValorEnCampoCliente(strID, "BLN_INFOFINANCIERA", "1");
				}
			}
	
			//En caso que el cliente sea jurídico se agregan Accionistas, Junta Directiva y Representantes Legales
			if(strTipoPersona.equals("juridico"))
			{
				if(!blnAccionistas){
					//Se agrega Accionista
					aplicativoSteps.seleccionarOpcionDelMenuOpciones("Accionistas");
					mantenimientoClientesSteps.agregarAccionista(strCodigoProspectoAccionista);
					blnAccionistas = mantenimientoClientesSteps.verificarExistenciaTablaResultados();
					
					//Se actualiza la bandera de Accionistas
					if(blnAccionistas) {
						db.guardaValorEnCampoCliente(strID, "BLN_ACCIONISTAS", "1");
					}
				}
				
				if(!blnJuntadirectiva){
					//Se agrega Junta Directiva
					aplicativoSteps.seleccionarOpcionDelMenuOpciones("Junta Directiva");
					mantenimientoClientesSteps.agregarJuntaDirectiva(strCodigoClienteJuntaDirectiva);
					blnJuntadirectiva = mantenimientoClientesSteps.verificarExistenciaTablaResultados();
					
					//Se actualiza la bandera de Junta Directiva
					if (blnJuntadirectiva) {
						db.guardaValorEnCampoCliente(strID, "BLN_JUNTADIRECTIVA", "1");
					}
				}
				
				if(!blnRepresentante){
					//Se agrega Representante Legal
					aplicativoSteps.seleccionarOpcionDelMenuOpciones("Representantes Legales");
					mantenimientoClientesSteps.agregarRepresentanteLegal(strCodigoClienteRepresentanteLegal);
					blnRepresentante = mantenimientoClientesSteps.verificarExistenciaTablaResultados();
					
					//Se actualiza la bandera de Representante Legal
					if (blnRepresentante) {
						db.guardaValorEnCampoCliente(strID, "BLN_REPRESENTANTE", "1");
					}
				}
				//Se vuelve al formulario principal de mantenimiento
				aplicativoSteps.seleccionarOpcionDelMenuOpciones("Información Básica");
			}
			
			//Se guarda el mantenimiento
			System.out.println("Se guarda el mantenimiento");
			mantenimientoClientesSteps.enviarFormularioMantenimiento();
			
			//Verificar que el envío del mantenimiento fue exitoso
			System.out.println("Se verifica envío correcto de mantenimiento");
			mantenimientoClientesSteps.verificarEnvioExitosoFormularioMantenimiento();
			
			blnMantenimiento = (strTipoPersona.equals("persona") && blnInfofinanciera) || (strTipoPersona.equals("juridico") && blnInfofinanciera && blnAccionistas && blnJuntadirectiva && blnRepresentante);
			
			if(blnMantenimiento){
				db.guardaValorEnCampoCliente(strID, "BLN_MANTENIMIENTO", "1");
			}
			
			//Se cierra sesión
			aplicativoSteps.cerrarSesion();
		}
		//--- Fin mantenimiento
		
		//--- Aprobación
		if(!blnAprobado) {
			// Se inicia sesión para aprobación
			System.out.println("Se inicia sesión para aprobación");
			aplicativoSteps.hacerClickEnLogin();
			//aplicativoSteps.hacerLogin(strUsuarioAuth,strPasswordAuth);
			aplicativoSteps.hacerLogin(strUsuarioAuth,strPasswordAuth);
			
			// Se ingresa al submenú Aprobaciones>Clientes
			System.out.println("Se ingresa al submenú Aprobaciones>Clientes");
			aplicativoSteps.seleccionarEnSubmenu("Aprobaciones", "Clientes");
			
			// Se ingresa con segunda clave
			System.out.println("Se ingresa con segunda clave");
		    //aprobacionesSteps.ingresarConSegundaClave(str2ndPassword);
			aprobacionesSteps.ingresarConSegundaClave(str2ndPassword);
		    
			// Se selecciona el cliente para aprobar
			System.out.println("Se selecciona el cliente para aprobar");
		    aprobacionesSteps.seleccionarClienteParaAprobar(strNumeroCliente);
	
			// Se hace click en aprobar
			System.out.println("Se hace click en aprobar");
			aprobacionesSteps.hacerClickEnAprobar();
	
			// Se verifica que se haya aprobado
			System.out.println("Se verifica que se haya aprobado");
			blnAprobado = aprobacionesSteps.verificarAprobacion(strNumeroCliente);
			
			if(blnAprobado) {
				db.guardaValorEnCampoCliente(strID, "BLN_APROBADO", "1");
			}
	
			// Se cierra sesión
			System.out.println("Se cierra sesión");
			aplicativoSteps.cerrarSesion();
		}
		//--- Fin Aprobación
		
		//--- Si todo se realizó se marcan como ejecutado el cliente
		if(blnConsultado && blnMantenimiento && blnAprobado) {
			db.guardaValorEnCampoCliente(strID, "EJECUTADO", "1");
		}
	}

	private String getStrTipoPersona() {
		if (getE01lgt().equals("1")) { // tipo cliente: 1: jurídico, 2: persona
			return "juridico";
		} else {
			return "persona";
		}
	}

	@After
	public void reiniciar() throws Exception {
		System.out.println("final test: retomando...");
		aplicativoSteps.finalizar();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdcli() {
		return idcli;
	}

	public void setIdcli(String idcli) {
		this.idcli = idcli;
	}

	public String getAnalista() {
		return analista;
	}

	public void setAnalista(String analista) {
		this.analista = analista;
	}

	public String getCasodeprueba() {
		return casodeprueba;
	}

	public void setCasodeprueba(String casodeprueba) {
		this.casodeprueba = casodeprueba;
	}

	public String getE01pid() {
		return e01pid;
	}

	public void setE01pid(String e01pid) {
		this.e01pid = e01pid;
	}

	public String getE01lgt() {
		return e01lgt;
	}

	public void setE01lgt(String e01lgt) {
		this.e01lgt = e01lgt;
	}

	public String getE01tid() {
		return e01tid;
	}

	public void setE01tid(String e01tid) {
		this.e01tid = e01tid;
	}

	public String getE01idn() {
		return e01idn;
	}

	public void setE01idn(String e01idn) {
		this.e01idn = e01idn;
	}

	public String getE01fna() {
		return e01fna;
	}

	public void setE01fna(String e01fna) {
		this.e01fna = e01fna;
	}

	public String getE01fn2() {
		return e01fn2;
	}

	public void setE01fn2(String e01fn2) {
		this.e01fn2 = e01fn2;
	}

	public String getE01ln1() {
		return e01ln1;
	}

	public void setE01ln1(String e01ln1) {
		this.e01ln1 = e01ln1;
	}

	public String getE01ln2() {
		return e01ln2;
	}

	public void setE01ln2(String e01ln2) {
		this.e01ln2 = e01ln2;
	}

	public String getE01sex() {
		return e01sex;
	}

	public void setE01sex(String e01sex) {
		this.e01sex = e01sex;
	}

	public String getE01na1() {
		return e01na1;
	}

	public void setE01na1(String e01na1) {
		this.e01na1 = e01na1;
	}

	public String getE01nm6() {
		return e01nm6;
	}

	public void setE01nm6(String e01nm6) {
		this.e01nm6 = e01nm6;
	}

	public String getE01uc2() {
		return e01uc2;
	}

	public void setE01uc2(String e01uc2) {
		this.e01uc2 = e01uc2;
	}

	public String getE01mst() {
		return e01mst;
	}

	public void setE01mst(String e01mst) {
		this.e01mst = e01mst;
	}

	public String getE01uc3() {
		return e01uc3;
	}

	public void setE01uc3(String e01uc3) {
		this.e01uc3 = e01uc3;
	}

	public String getE01uc4() {
		return e01uc4;
	}

	public void setE01uc4(String e01uc4) {
		this.e01uc4 = e01uc4;
	}

	public String getE01bdd() {
		return e01bdd;
	}

	public void setE01bdd(String e01bdd) {
		this.e01bdd = e01bdd;
	}

	public String getE01bdm() {
		return e01bdm;
	}

	public void setE01bdm(String e01bdm) {
		this.e01bdm = e01bdm;
	}

	public String getE01bdy() {
		return e01bdy;
	}

	public void setE01bdy(String e01bdy) {
		this.e01bdy = e01bdy;
	}

	public String getE01gec() {
		return e01gec;
	}

	public void setE01gec(String e01gec) {
		this.e01gec = e01gec;
	}

	public String getE01nso() {
		return e01nso;
	}

	public void setE01nso(String e01nso) {
		this.e01nso = e01nso;
	}

	public String getE01n5d() {
		return e01n5d;
	}

	public void setE01n5d(String e01n5d) {
		this.e01n5d = e01n5d;
	}

	public String getE01red() {
		return e01red;
	}

	public void setE01red(String e01red) {
		this.e01red = e01red;
	}

	public String getE01rem() {
		return e01rem;
	}

	public void setE01rem(String e01rem) {
		this.e01rem = e01rem;
	}

	public String getE01rey() {
		return e01rey;
	}

	public void setE01rey(String e01rey) {
		this.e01rey = e01rey;
	}

	public String getE01n5p() {
		return e01n5p;
	}

	public void setE01n5p(String e01n5p) {
		this.e01n5p = e01n5p;
	}

	public String getE01n5c() {
		return e01n5c;
	}

	public void setE01n5c(String e01n5c) {
		this.e01n5c = e01n5c;
	}

	public String getE01n3p() {
		return e01n3p;
	}

	public void setE01n3p(String e01n3p) {
		this.e01n3p = e01n3p;
	}

	public String getE01n3d() {
		return e01n3d;
	}

	public void setE01n3d(String e01n3d) {
		this.e01n3d = e01n3d;
	}

	public String getE01n3c() {
		return e01n3c;
	}

	public void setE01n3c(String e01n3c) {
		this.e01n3c = e01n3c;
	}

	public String getE01ccs() {
		return e01ccs;
	}

	public void setE01ccs(String e01ccs) {
		this.e01ccs = e01ccs;
	}

	public String getE01tx8() {
		return e01tx8;
	}

	public void setE01tx8(String e01tx8) {
		this.e01tx8 = e01tx8;
	}

	public String getE01obc() {
		return e01obc;
	}

	public void setE01obc(String e01obc) {
		this.e01obc = e01obc;
	}

	public String getE01edl() {
		return e01edl;
	}

	public void setE01edl(String e01edl) {
		this.e01edl = e01edl;
	}

	public String getE01uc9() {
		return e01uc9;
	}

	public void setE01uc9(String e01uc9) {
		this.e01uc9 = e01uc9;
	}

	public String getE01uc6() {
		return e01uc6;
	}

	public void setE01uc6(String e01uc6) {
		this.e01uc6 = e01uc6;
	}

	public String getE01nem() {
		return e01nem;
	}

	public void setE01nem(String e01nem) {
		this.e01nem = e01nem;
	}

	public String getE01sst() {
		return e01sst;
	}

	public void setE01sst(String e01sst) {
		this.e01sst = e01sst;
	}

	public String getE01fl8() {
		return e01fl8;
	}

	public void setE01fl8(String e01fl8) {
		this.e01fl8 = e01fl8;
	}

	public String getE01alb() {
		return e01alb;
	}

	public void setE01alb(String e01alb) {
		this.e01alb = e01alb;
	}

	public String getE01tx1() {
		return e01tx1;
	}

	public void setE01tx1(String e01tx1) {
		this.e01tx1 = e01tx1;
	}

	public String getE01tx4() {
		return e01tx4;
	}

	public void setE01tx4(String e01tx4) {
		this.e01tx4 = e01tx4;
	}

	public String getE01tx5() {
		return e01tx5;
	}

	public void setE01tx5(String e01tx5) {
		this.e01tx5 = e01tx5;
	}

	public String getE01tx3() {
		return e01tx3;
	}

	public void setE01tx3(String e01tx3) {
		this.e01tx3 = e01tx3;
	}

	public String getE01tx2() {
		return e01tx2;
	}

	public void setE01tx2(String e01tx2) {
		this.e01tx2 = e01tx2;
	}

	public String getE01uc7() {
		return e01uc7;
	}

	public void setE01uc7(String e01uc7) {
		this.e01uc7 = e01uc7;
	}

	public String getE01adc() {
		return e01adc;
	}

	public void setE01adc(String e01adc) {
		this.e01adc = e01adc;
	}

	public String getE01ste() {
		return e01ste;
	}

	public void setE01ste(String e01ste) {
		this.e01ste = e01ste;
	}

	public String getE01ctc() {
		return e01ctc;
	}

	public void setE01ctc(String e01ctc) {
		this.e01ctc = e01ctc;
	}

	public String getE01na2() {
		return e01na2;
	}

	public void setE01na2(String e01na2) {
		this.e01na2 = e01na2;
	}

	public String getE01tud() {
		return e01tud;
	}

	public void setE01tud(String e01tud) {
		this.e01tud = e01tud;
	}

	public String getE01mun() {
		return e01mun;
	}

	public void setE01mun(String e01mun) {
		this.e01mun = e01mun;
	}

	public String getE01par() {
		return e01par;
	}

	public void setE01par(String e01par) {
		this.e01par = e01par;
	}

	public String getE01fg1() {
		return e01fg1;
	}

	public void setE01fg1(String e01fg1) {
		this.e01fg1 = e01fg1;
	}

	public String getE01inl() {
		return e01inl;
	}

	public void setE01inl(String e01inl) {
		this.e01inl = e01inl;
	}

	public String getE01fl5() {
		return e01fl5;
	}

	public void setE01fl5(String e01fl5) {
		this.e01fl5 = e01fl5;
	}

	public String getE01tvy() {
		return e01tvy;
	}

	public void setE01tvy(String e01tvy) {
		this.e01tvy = e01tvy;
	}

	public String getE01tvm() {
		return e01tvm;
	}

	public void setE01tvm(String e01tvm) {
		this.e01tvm = e01tvm;
	}

	public String getE01pho() {
		return e01pho;
	}

	public void setE01pho(String e01pho) {
		this.e01pho = e01pho;
	}

	public String getE01pht() {
		return e01pht;
	}

	public void setE01pht(String e01pht) {
		this.e01pht = e01pht;
	}

	public String getE01ccc() {
		return e01ccc;
	}

	public void setE01ccc(String e01ccc) {
		this.e01ccc = e01ccc;
	}

	public String getE01soi() {
		return e01soi;
	}

	public void setE01soi(String e01soi) {
		this.e01soi = e01soi;
	}

	public String getE01cob() {
		return e01cob;
	}

	public void setE01cob(String e01cob) {
		this.e01cob = e01cob;
	}

	public String getE01phn() {
		return e01phn;
	}

	public void setE01phn(String e01phn) {
		this.e01phn = e01phn;
	}

	public String getE01pnt() {
		return e01pnt;
	}

	public void setE01pnt(String e01pnt) {
		this.e01pnt = e01pnt;
	}

	public String getE01iad() {
		return e01iad;
	}

	public void setE01iad(String e01iad) {
		this.e01iad = e01iad;
	}

	public String getE01iat() {
		return e01iat;
	}

	public void setE01iat(String e01iat) {
		this.e01iat = e01iat;
	}

	public String getE01fl2() {
		return e01fl2;
	}

	public void setE01fl2(String e01fl2) {
		this.e01fl2 = e01fl2;
	}

	public String getE01ds2() {
		return e01ds2;
	}

	public void setE01ds2(String e01ds2) {
		this.e01ds2 = e01ds2;
	}

	public String getE01fx() {
		return e01fx;
	}

	public void setE01fx(String e01fx) {
		this.e01fx = e01fx;
	}

	public String getE01fxp() {
		return e01fxp;
	}

	public void setE01fxp(String e01fxp) {
		this.e01fxp = e01fxp;
	}

	public String getE01mrp() {
		return e01mrp;
	}

	public void setE01mrp(String e01mrp) {
		this.e01mrp = e01mrp;
	}

	public String getE01pep() {
		return e01pep;
	}

	public void setE01pep(String e01pep) {
		this.e01pep = e01pep;
	}

	public String getE01fl4() {
		return e01fl4;
	}

	public void setE01fl4(String e01fl4) {
		this.e01fl4 = e01fl4;
	}

	public String getE01rcp() {
		return e01rcp;
	}

	public void setE01rcp(String e01rcp) {
		this.e01rcp = e01rcp;
	}

	public String getE1rrtp() {
		return e1rrtp;
	}

	public void setE1rrtp(String e1rrtp) {
		this.e1rrtp = e1rrtp;
	}

	public String getE1rma1() {
		return e1rma1;
	}

	public void setE1rma1(String e1rma1) {
		this.e1rma1 = e1rma1;
	}

	public String getE1rnm3() {
		return e1rnm3;
	}

	public void setE1rnm3(String e1rnm3) {
		this.e1rnm3 = e1rnm3;
	}

	public String getE1rnm4() {
		return e1rnm4;
	}

	public void setE1rnm4(String e1rnm4) {
		this.e1rnm4 = e1rnm4;
	}

	public String getE1rnm5() {
		return e1rnm5;
	}

	public void setE1rnm5(String e1rnm5) {
		this.e1rnm5 = e1rnm5;
	}

	public String getE1rpht() {
		return e1rpht;
	}

	public void setE1rpht(String e1rpht) {
		this.e1rpht = e1rpht;
	}

	public String getE1rhpn() {
		return e1rhpn;
	}

	public void setE1rhpn(String e1rhpn) {
		this.e1rhpn = e1rhpn;
	}

	public String getE1rctr() {
		return e1rctr;
	}

	public void setE1rctr(String e1rctr) {
		this.e1rctr = e1rctr;
	}

	public String getE1rste() {
		return e1rste;
	}

	public void setE1rste(String e1rste) {
		this.e1rste = e1rste;
	}

	public String getE1rctc() {
		return e1rctc;
	}

	public void setE1rctc(String e1rctc) {
		this.e1rctc = e1rctc;
	}

	public String getE01idd() {
		return e01idd;
	}

	public void setE01idd(String e01idd) {
		this.e01idd = e01idd;
	}

	public String getE01idm() {
		return e01idm;
	}

	public void setE01idm(String e01idm) {
		this.e01idm = e01idm;
	}

	public String getE01idy() {
		return e01idy;
	}

	public void setE01idy(String e01idy) {
		this.e01idy = e01idy;
	}

	public String getE01bra() {
		return e01bra;
	}

	public void setE01bra(String e01bra) {
		this.e01bra = e01bra;
	}

	public String getE01ofc() {
		return e01ofc;
	}

	public void setE01ofc(String e01ofc) {
		this.e01ofc = e01ofc;
	}

	public String getE01of2() {
		return e01of2;
	}

	public void setE01of2(String e01of2) {
		this.e01of2 = e01of2;
	}

	public String getD01uc5() {
		return d01uc5;
	}

	public void setD01uc5(String d01uc5) {
		this.d01uc5 = d01uc5;
	}

	public String getE01fl6() {
		return e01fl6;
	}

	public void setE01fl6(String e01fl6) {
		this.e01fl6 = e01fl6;
	}

	public String getE01rby() {
		return e01rby;
	}

	public void setE01rby(String e01rby) {
		this.e01rby = e01rby;
	}

	public String getE01stf() {
		return e01stf;
	}

	public void setE01stf(String e01stf) {
		this.e01stf = e01stf;
	}

	public String getE01tse() {
		return e01tse;
	}

	public void setE01tse(String e01tse) {
		this.e01tse = e01tse;
	}

	public String getE01typ() {
		return e01typ;
	}

	public void setE01typ(String e01typ) {
		this.e01typ = e01typ;
	}

	public String getE01lif() {
		return e01lif;
	}

	public void setE01lif(String e01lif) {
		this.e01lif = e01lif;
	}

	public String getE01ilv() {
		return e01ilv;
	}

	public void setE01ilv(String e01ilv) {
		this.e01ilv = e01ilv;
	}

	public String getE01a1d() {
		return e01a1d;
	}

	public void setE01a1d(String e01a1d) {
		this.e01a1d = e01a1d;
	}

	public String getE01a1m() {
		return e01a1m;
	}

	public void setE01a1m(String e01a1m) {
		this.e01a1m = e01a1m;
	}

	public String getE01a1y() {
		return e01a1y;
	}

	public void setE01a1y(String e01a1y) {
		this.e01a1y = e01a1y;
	}

	public String getE01fl3() {
		return e01fl3;
	}

	public void setE01fl3(String e01fl3) {
		this.e01fl3 = e01fl3;
	}

	public String getE01pi4() {
		return e01pi4;
	}

	public void setE01pi4(String e01pi4) {
		this.e01pi4 = e01pi4;
	}

	public String getE01ti4() {
		return e01ti4;
	}

	public void setE01ti4(String e01ti4) {
		this.e01ti4 = e01ti4;
	}

	public String getE01uc1() {
		return e01uc1;
	}

	public void setE01uc1(String e01uc1) {
		this.e01uc1 = e01uc1;
	}

	public String getE01id4() {
		return e01id4;
	}

	public void setE01id4(String e01id4) {
		this.e01id4 = e01id4;
	}

	public String getE01cnm() {
		return e01cnm;
	}

	public void setE01cnm(String e01cnm) {
		this.e01cnm = e01cnm;
	}

	public String getE01dio() {
		return e01dio;
	}

	public void setE01dio(String e01dio) {
		this.e01dio = e01dio;
	}

	public String getE01ona() {
		return e01ona;
	}

	public void setE01ona(String e01ona) {
		this.e01ona = e01ona;
	}

	public String getE01icc() {
		return e01icc;
	}

	public void setE01icc(String e01icc) {
		this.e01icc = e01icc;
	}

	public String getE01ren() {
		return e01ren;
	}

	public void setE01ren(String e01ren) {
		this.e01ren = e01ren;
	}

	public String getE01rbn() {
		return e01rbn;
	}

	public void setE01rbn(String e01rbn) {
		this.e01rbn = e01rbn;
	}

	public String getE01web() {
		return e01web;
	}

	public void setE01web(String e01web) {
		this.e01web = e01web;
	}

	public String getE01enf() {
		return e01enf;
	}

	public void setE01enf(String e01enf) {
		this.e01enf = e01enf;
	}

	public String getE01bol() {
		return e01bol;
	}

	public void setE01bol(String e01bol) {
		this.e01bol = e01bol;
	}

	public String getE01inf() {
		return e01inf;
	}

	public void setE01inf(String e01inf) {
		this.e01inf = e01inf;
	}

	public String getE01cso() {
		return e01cso;
	}

	public void setE01cso(String e01cso) {
		this.e01cso = e01cso;
	}

	public String getE01if1() {
		return e01if1;
	}

	public void setE01if1(String e01if1) {
		this.e01if1 = e01if1;
	}

	public String getE01reo() {
		return e01reo;
	}

	public void setE01reo(String e01reo) {
		this.e01reo = e01reo;
	}

	public String getIbsnumcli() {
		return ibsnumcli;
	}

	public void setIbsnumcli(String ibsnumcli) {
		this.ibsnumcli = ibsnumcli;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEjecutado() {
		return ejecutado;
	}

	public void setEjecutado(String ejecutado) {
		this.ejecutado = ejecutado;
	}

	public String getCcty1() {
		return ccty1;
	}

	public void setCcty1(String ccty1) {
		this.ccty1 = ccty1;
	}
	
	public String getBln_consultado() {
		return bln_consultado;
	}

	public void setBln_consultado(String bln_consultado) {
		this.bln_consultado = bln_consultado;
	}

	
	public String getBln_mantenimiento() {
		return bln_mantenimiento;
	}

	public void setBln_mantenimiento(String bln_mantenimiento) {
		this.bln_mantenimiento = bln_mantenimiento;
	}
	
	public String getBln_infofinanciera() {
		return bln_infofinanciera;
	}

	public void setBln_infofinanciera(String bln_infofinanciera) {
		this.bln_infofinanciera = bln_infofinanciera;
	}
	
	public String getBln_accionistas() {
		return bln_accionistas;
	}

	public void setBln_accionistas(String bln_accionistas) {
		this.bln_accionistas = bln_accionistas;
	}
	
	public String getBln_juntadirectiva() {
		return bln_juntadirectiva;
	}

	public void setBln_juntadirectiva(String bln_juntadirectiva) {
		this.bln_juntadirectiva = bln_juntadirectiva;
	}
	
	public String getBln_representante() {
		return bln_representante;
	}

	public void setBln_representante(String bln_representante) {
		this.bln_representante = bln_representante;
	}
	
	public String getBln_aprobado() {
		return bln_aprobado;
	}

	public void setBln_aprobado(String bln_aprobado) {
		this.bln_aprobado = bln_aprobado;
	}
	
	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
}
