/**
 * Copyright (c) 2017, Choucair Cárdenas Testing S.A.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package com.bancompartir.aurora.Banco;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import com.choucair.framework.FW_ConexionBD;
import com.choucair.framework.FW_FuncionesDB;

/**
 * Contiene las consultas y acciones específicas ejecutadas sobre la Base de
 * Datos del cliente. Esta clase realiza la conexión a Base de Datos usando la
 * clase {@link FW_ConexionBD FW_ConexionBD} del {@link com.choucair.framework
 * Framework Choucair}.
 * <p>
 * Los métodos implementados en esta clase son de caracter <b>CONFIDENCIAL</b> y
 * de <b>USO EXCLUSIVO</b> del proyecto <b>BANCOMPARTIR - AURORA</b> y son
 * aplicables únicamente para el caso particular de la automatización en este
 * proyecto.
 * <p>
 * NO DEBE SER REPLICADO ANTE OTROS CLIENTES.
 * 
 * @author cmurciag
 * @version 1.2
 * @since 1.0, 11/12/2017
 * 
 * @see Bancompartir_Funciones
 * @see Bancompartir_variables
 * @see FW_ConexionBD
 * @see FW_FuncionesDB
 */

public class Bancompartir_DB {

	/**
	 * Nombres de Tablas específicas en Base de Datos del cliente para almacenar
	 * los clientes y cuentas creados como precondiciones.
	 */
	private String strTablaClientes = "DD_Clientes_D";
	private String strTablaCuentas = "DD_CUENTAS_D";

	/**
	 * Inserta un arreglo de campos y valores en un registro de una tabla
	 * específica por medio de la función
	 * <tt>{@link FW_FuncionesDB#insertarCreando(java.sql.Connection, String, String[], String[])
	 * insertarCreando()}</tt> del {@link com.choucair.framework Framework
	 * Choucair}.
	 * <p>
	 * En este método, además de insertar los valores especificados en los
	 * parámetros de entrada, se insertan los siguientes campos en cada
	 * registro:
	 * <ul>
	 * <li>{@code FECHA = fecha actual}</li>
	 * <li>{@code EJECUTADO = 0}</li>
	 * </ul>
	 * 
	 * @param strNombreTabla
	 *            Nombre de la tabla en base de datos excluyendo el prefijo
	 *            {@code AUT_}. En caso de no existir, la tabla se creará.
	 * @param arrEncabezados
	 *            Arreglo de Strings con los nombres de los campos, en caso de
	 *            no existir el campo en la tabla, se creará el campo.
	 * @param arrValores
	 *            Arreglo de Strings con los valores a insertar. El tamaño del
	 *            arreglo de valores debe ser igual al tamaño del arreglo de
	 *            encabezados.
	 * @throws Exception
	 *             En caso que el tamaño del arreglo de Encabezados sea
	 *             diferente al del arreglo de Valores.<br>
	 *             En caso de encontrar un error consultando la base de datos.
	 * 
	 * @see FW_FuncionesDB#insertarCreando(java.sql.Connection, String,
	 *      String[], String[])
	 */
	public void guardarDatosPorEjecutar(String strNombreTabla, String[] arrEncabezados, String[] arrValores)
			throws Exception {
		try {
			if (arrEncabezados.length == arrValores.length) {
				FW_FuncionesDB fw_db_fun = new FW_FuncionesDB();
				FW_ConexionBD fw_db = new FW_ConexionBD("oracle_bancompartir");

				fw_db.abrirConexion();
				String[] arrEncabezadosFinales = (String.join(",", arrEncabezados) + ",FECHA,EJECUTADO").split(",");
				String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
				String[] arrValoresFinales = (String.join(",", arrValores) + "," + timeStamp + ",0").split(",");

				fw_db_fun.insertarCreando(fw_db.getCon(), strNombreTabla, arrEncabezadosFinales, arrValoresFinales);

				fw_db.cerrarConexion();
			} else {
				throw new Exception("El número de valores es diferente al número de encabezados. "
						+ arrEncabezados.length + " Encabezados, " + arrValores.length + " Valores");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error guardando Datos: " + e.getMessage());
		}

	}

	/**
	 * Indica si hay clientes pendientes por ejecución del robot de creación de
	 * precondiciones Clientes.
	 * <p>
	 * Hace uso del método
	 * {@link Bancompartir_DB#verificarPendientesTabla(String)
	 * verificarPendientesTabla()} con la variable
	 * {@link Bancompartir_DB#strTablaClientes strTablaClientes} como parámetro
	 * de entrada.
	 * 
	 * @return Booleano que indica si hay clientes pendientes por consultar
	 */
	public boolean hayConsultasClientesPendientes() {
		return verificarPendientesTabla(strTablaClientes);
	}

	/**
	 * Indica si hay cuentas pendientes por crear con el robot de creación de
	 * precondiciones Cuentas.
	 * <p>
	 * Hace uso del método
	 * {@link Bancompartir_DB#verificarPendientesTabla(String)
	 * verificarPendientesTabla()} con la variable
	 * {@link Bancompartir_DB#strTablaCuentas strTablaCuentas} como parámetro de
	 * entrada.
	 * 
	 * @return Booleano que indica si hay cuentas pendientes por crear
	 */
	public boolean hayCreacionCuentasPendientes() {
		return verificarPendientesTabla(strTablaCuentas);
	}

	/**
	 * Retorna todos los registros de los Clientes sobre los cuales no ha sido
	 * ejecutado el robot. Para la consulta se usa el método
	 * {@link Bancompartir_DB#consultarTablaPendientes(String)
	 * consultarTablaPendientes()} para la tabla de clientes descrita en
	 * {@link Bancompartir_DB#strTablaClientes strTablaClientes}
	 * 
	 * @return ResultSet con los registros de la tabla de clientes pendientes de
	 *         ejecución
	 */
	public ResultSet consultarClientesPendientes() {
		return consultarTablaPendientes(strTablaClientes);
	}

	/**
	 * Retorna todos los registros de las Cuentas pendientes por crear con el
	 * robot.
	 * <p>
	 * Para la consulta se usa el método
	 * {@link Bancompartir_DB#consultarTablaPendientes(String)
	 * consultarTablaPendientes()} para la tabla de clientes descrita en
	 * {@link Bancompartir_DB#strTablaCuentas strTablaCuentas}
	 * 
	 * @return ResultSet con los registros de la tabla de Cuentas pendientes de
	 *         crear
	 */
	public ResultSet consultarCuentasPendientes() {
		return consultarTablaPendientes(strTablaCuentas);
	}

	/**
	 * Actualiza el número de un cliente específico en la tabla de clientes
	 * definida en la variable {@link Bancompartir_DB#strTablaClientes
	 * strTablaClientes}.
	 * <p>
	 * Modifica el campo <tt>ISBNUMCLI</tt> buscando por el campo <tt>ID</tt>
	 * haciendo uso del método
	 * {@link Bancompartir_DB#actualizarCampoPorID(String, String, String, String)
	 * actualizarCampoPorID()}.
	 * 
	 * @param strID
	 *            String con el <tt>ID</tt> del cliente que se va a modificar
	 * @param strNumeroCliente
	 *            String con el número de cliente a guardar
	 */
	public void actualizarNumeroCliente(String strID, String strNumeroCliente) {
		actualizarCampoPorID(strTablaClientes, "IBSNUMCLI", strNumeroCliente, strID);
	}

	/**
	 * Actualiza el número de cuenta en la tabla de cuentas definida en la
	 * variable {@link Bancompartir_DB#strTablaCuentas strTablaCuentas}.
	 * <p>
	 * Modifica el campo <tt>IBSNUMCTA</tt> buscando por el campo <tt>ID</tt>
	 * haciendo uso del método
	 * {@link Bancompartir_DB#actualizarCampoPorID(String, String, String, String)
	 * actualizarCampoPorID()}.
	 * 
	 * @param strID
	 *            String con el <tt>ID</tt> de la cuenta que se va a modificar
	 * @param strNumeroDeCuenta
	 *            String con el número de Cuenta a guardar
	 */
	public void actualizarNumeroCuenta(String strID, String strNumeroDeCuenta) {
		actualizarCampoPorID(strTablaCuentas, "IBSNUMCTA", strNumeroDeCuenta, strID);
	}

	/**
	 * Guarda un valor Booleano en los campos en forma binaria:
	 * <ul>
	 * <li><tt>TRUE = 1</tt></li>
	 * <li>FALSE = 0</li>
	 * </ul>
	 * <p>
	 * Hace uso del método
	 * {@link Bancompartir_DB#actualizarCampoPorID(String, String, String, String)
	 * actualizarCampoPorID()}.
	 * 
	 * @param strID
	 *            String con el <tt>ID</tt> del cliente que se va a Actualizar
	 * @param strCampo
	 *            String con el nombre del campo que se va actualizar
	 * @param blnValor
	 *            Booleano con el valor que se va a guardar
	 */
	public void guardaValorBooleanoEnCampoCliente(String strID, String strCampo, boolean blnValor) {
		String strValor = "0";
		if (blnValor) {
			strValor = "1";
		}
		actualizarCampoPorID(strTablaClientes, strCampo, strValor, strID);
	}

	/**
	 * Guarda un valor <tt>String</tt> en un campo específico de la tabla de
	 * clientes.
	 * <p>
	 * Hace uso del método
	 * {@link Bancompartir_DB#actualizarCampoPorID(String, String, String, String)
	 * actualizarCampoPorID()}.
	 * 
	 * @param strID
	 *            String con el <tt>ID</tt> del cliente que se va a Actualizar
	 * @param strCampo
	 *            String con el nombre del campo que se va actualizar
	 * @param strValor
	 *            String con el valor que se va a guardar
	 */
	public void guardaValorEnCampoCliente(String strID, String strCampo, String strValor) {
		actualizarCampoPorID(strTablaClientes, strCampo, strValor, strID);
	}

	/**
	 * Guarda un valor <tt>String</tt> en un campo específico de la tabla de
	 * cuentas.
	 * <p>
	 * Hace uso del método
	 * {@link Bancompartir_DB#actualizarCampoPorID(String, String, String, String)
	 * actualizarCampoPorID()}.
	 * 
	 * @param strID
	 *            String con el <tt>ID</tt> de la cuenta que se va a Actualizar
	 * @param strCampo
	 *            String con el nombre del campo que se va actualizar
	 * @param strValor
	 *            String con el valor que se va a guardar
	 */
	public void guardaValorEnCampoCuentas(String strID, String strCampo, String strValor) {
		actualizarCampoPorID(strTablaCuentas, strCampo, strValor, strID);
	}

	/**
	 * Guarda la palabra <tt>"ERROR"</tt> en el campo <tt>IBSNUMCLI</tt> de la
	 * tabla descrita en la variable {@link Bancompartir_DB#strTablaClientes
	 * strTablaClientes}.
	 * <p>
	 * Se hace uso del método
	 * {@link Bancompartir_DB#actualizarCampoPorID(String, String, String, String)
	 * actualizarCampoPorID()}.
	 * 
	 * @param strID
	 *            String con el <tt>ID</tt> del cliente que se va a modificar
	 */
	public void guardaErrorConsultandoCliente(String strID) {
		actualizarCampoPorID(strTablaClientes, "IBSNUMCLI", "ERROR", strID);
	}

	/**
	 * Guarda la palabra <tt>"ERROR"</tt> en el campo <tt>IBSNUMCTA</tt> de la
	 * tabla descrita en la variable {@link Bancompartir_DB#strTablaCuentas
	 * strTablaCuentas}.
	 * <p>
	 * Se hace uso del método
	 * {@link Bancompartir_DB#actualizarCampoPorID(String, String, String, String)
	 * actualizarCampoPorID()}.
	 * 
	 * @param strID
	 *            String con el <tt>ID</tt> del cliente que se va a modificar
	 */
	public void guardaErrorConsultandoCuenta(String strID) {
		actualizarCampoPorID(strTablaCuentas, "IBSNUMCTA", "ERROR", strID);
	}

	/**
	 * Devuelve los resultados de la consulta de los registros de la tabla
	 * <tt>strNombreTabla</tt> donde el campo <tt>EJECUTADO</tt> es <tt>0</tt> ó
	 * <tt>NULL</tt>.
	 * 
	 * @param strNombreTabla
	 *            Nombre de la tabla sin incluir el prefijo <tt>"AUT_"</tt>. El
	 *            nombre de la tabla no es sensible a Mayúsculas prues se
	 *            convierte a Mayúscula para realizar la consulta.
	 * @return ResultSet con todos los campos de los registros donde
	 *         <tt>EJECUTADO = 0</tt> ó <tt>NULL</tt>
	 */
	private ResultSet consultarTablaPendientes(String strNombreTabla) {
		try {

			FW_ConexionBD fw_db;
			fw_db = new FW_ConexionBD("oracle_bancompartir");

			fw_db.abrirConexion();

			Statement stmt = fw_db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT * FROM CHOUCAIR.AUT_" + strNombreTabla.toUpperCase()
					+ " where EJECUTADO ='0' OR EJECUTADO = NULL");

			// fw_db.cerrarConexion(); // Se comenta debido a que genera error

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Indica si una tabla específica tiene algún registro donde el campo
	 * <tt>EJECUTADO</tt> es <tt>0</tt> ó <tt>NULL</tt>.
	 * 
	 * @param strNombreTabla
	 *            Nombre de la tabla sin incluir el prefijo <tt>"AUT_"</tt>. El
	 *            nombre de la tabla no es sensible a Mayúsculas prues se
	 *            convierte a Mayúscula para realizar la consulta.
	 * @return Booleano que indica si se encontraron registros con ejecución
	 *         pendiente
	 */
	private boolean verificarPendientesTabla(String strNombreTabla) {
		try {

			boolean outP = false;

			FW_ConexionBD fw_db;
			fw_db = new FW_ConexionBD("oracle_bancompartir");

			fw_db.abrirConexion();

			Statement stmt = fw_db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT count(*) AS total FROM CHOUCAIR.AUT_" + strNombreTabla.toUpperCase()
					+ " where EJECUTADO ='0' OR EJECUTADO = NULL";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int n = rs.getInt(1);
				outP = n > 0;
			}

			return outP;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Actualiza un <tt>CAMPO</tt> de un registro específico de una tabla según
	 * el <tt>ID</tt>.
	 * 
	 * @param strNombreTabla
	 *            Nombre de la tabla sin incluir el prefijo <tt>"AUT_"</tt>. El
	 *            nombre de la tabla no es sensible a Mayúsculas prues se
	 *            convierte a Mayúscula para realizar la consulta.
	 * @param strNombreCampo
	 *            Nombre del campo que se va a modificar.
	 * @param strValor
	 *            Nuevo valor del campo indicado.
	 * @param strID
	 *            String con el <tt>ID</tt> del registro que se va a modificar
	 */
	public void actualizarCampoPorID(String strNombreTabla, String strNombreCampo, String strValor, String strID) {
		try {

			FW_ConexionBD fw_db;
			fw_db = new FW_ConexionBD("oracle_bancompartir");

			fw_db.abrirConexion();

			Statement stmt = fw_db.getCon().createStatement();
			String strSql = "UPDATE CHOUCAIR.AUT_" + strNombreTabla.toUpperCase() + " SET " + strNombreCampo + " = '"
					+ strValor + "' where ID = " + strID;

			System.out.println(strSql);

			stmt.execute(strSql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void actualizarCsvDataDriven(String archivo, ResultSet rs) throws Exception {

		Writer csv = new OutputStreamWriter(new FileOutputStream(archivo), "ISO-8859-1");
		ResultSetMetaData meta = rs.getMetaData();

		int numberOfColumns = meta.getColumnCount();
		String dataHeaders = meta.getColumnName(1);
		for (int i = 2; i < numberOfColumns + 1; i++) {
			dataHeaders += "," + meta.getColumnName(i);
		}
		csv.write(dataHeaders);

		while (rs.next()) {
			String datos = rs.getString(1);
			for (int i = 2; i < numberOfColumns + 1; i++) {
				datos += "," + rs.getString(i);
			}
			csv.write(System.getProperty("line.separator"));
			csv.write(datos);
		}
		csv.close();
	}

	/**
	 * Obtiene las credenciales para un ambiente específico
	 * 
	 * @param strAmbiente
	 *            nombre del ambiente, no es sensible a Mayúsculas ni a
	 *            caracteres adicionales (ej: ibs_qps, qps, eIBSQPS..)
	 * @return Mapa de variables donde el Key es el nombre de la variable y el
	 *         Value el valor de la misma
	 * @throws Exception
	 *             Si no encuentra resultados para el ambiente especificado, ya
	 *             sea porque está mal referenciado el ambiente o porque aún no
	 *             se han agregado las credenciales correspondientes en la base
	 *             de datos
	 */
	public Map<String, String> getCredenciales(String strAmbiente) throws Exception {
		Map<String, String> mapVariables = new LinkedHashMap<String, String>();

		ResultSet r = consultarVariablesPorAmbiente(strAmbiente);

		if (r.isBeforeFirst()) {
			while (r.next()) {
				mapVariables.put(r.getString("variable"), r.getString("valor"));
			}
			return mapVariables;
		} else {
			throw new Exception("No se encontraron credenciales para el ambiente " + strAmbiente.toUpperCase());
		}
	}

	/**
	 * Devuelve la consulta de las variables definidas en la Base de Datos para
	 * cada ambiente
	 * 
	 * @param strAmbiente
	 *            nombre del ambiente, no es sensible a Mayúsculas ni a
	 *            caracteres adicionales (ej: ibs_qps, qps, eIBSQPS..)
	 * @return ResultSet de variables para el ambiente
	 */
	private ResultSet consultarVariablesPorAmbiente(String strAmbiente) {
		try {

			FW_ConexionBD fw_db;
			fw_db = new FW_ConexionBD("oracle_bancompartir");

			fw_db.abrirConexion();

			Statement stmt = fw_db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * from choucair.aut_vw_variables_por_ambiente where UPPER('" + strAmbiente
					+ "') like '%' || upper(ambiente) || '%'";
			ResultSet rs = stmt.executeQuery(query);

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void guardarDatosEnTabla(String strNombreTabla, Map<String, String> mapCampos) throws Exception {
		try {
			FW_FuncionesDB fw_db_fun = new FW_FuncionesDB();
			FW_ConexionBD fw_db = new FW_ConexionBD("oracle_bancompartir");

			fw_db.abrirConexion();
			fw_db_fun.insertarCreando(fw_db.getCon(), strNombreTabla, mapCampos);

			fw_db.cerrarConexion();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error guardando Datos: " + e.getMessage());
		}
	}

	private String obtenerJobID(Map<String, String> vars) {
		String id = "";

		// Quita letras del nombre de la máquina
		id += vars.get("COMPUTERNAME").replaceAll("\\D+", "");

		// Codifica el nombre del job en números
		id += codificarString(vars.get("JOB_NAME"));

		// número de ejecutor
		id += vars.get("EXECUTOR_NUMBER");

		// número de build
		id += vars.get("BUILD_ID").replaceAll("\\D+", "");

		id = id.replaceAll("\\D+", "");

		return id;
	}

	private String codificarString(String text) {
		String formato = "10000000000000";
		BigInteger bigInt = new BigInteger(text.getBytes());
		bigInt = bigInt.mod(new BigInteger(formato));
		String out = String.format("%0" + formato.length() + "d", bigInt);
		return out;
	}

	public void guardarHistorialInicialJob(Map<String, String> vars) throws Exception {
		String[] arrCamposDeVariables = { "BUILD_NUMBER", "BUILD_ID", "BUILD_URL", "NODE_NAME", "JOB_NAME", "BUILD_TAG",
				"JENKINS_URL", "EXECUTOR_NUMBER", "JAVA_HOME", "WORKSPACE", "SVN_REVISION", "CVS_BRANCH", "GIT_COMMIT",
				"GIT_URL", "GIT_BRANCH", "COMPUTERNAME" };

		Map<String, String> mapCampos = new LinkedHashMap<>();

		mapCampos.put("ID_JOB", obtenerJobID(vars));

		for (String strCampo : arrCamposDeVariables) {
			mapCampos.put(strCampo + "_JOB", vars.get(strCampo));
		}

		guardarDatosEnTabla("JENKINS_JOBS", mapCampos);

	}

	public void guardarHistorialFinalBuild(Map<String, String> vars) throws Exception {

		Bancompartir_Funciones banco = new Bancompartir_Funciones();

		Map<String, String> mapCampos = new LinkedHashMap<>();

		// ID del JOB
		mapCampos.put("ID_JOB", obtenerJobID(vars));

		// Campos del build.xml
		mapCampos.putAll(banco.obtenerVariablesDeBuildXml(vars));

		// Campos del summary.txt
		mapCampos.putAll(banco.obtenerVariablesDeSummaryTxt(vars));

		guardarDatosEnTabla("JENKINS_BUILDS", mapCampos);
	}

	public void guardarHistorialFinalResults(Map<String, String> vars) throws Exception {

		Bancompartir_Funciones banco = new Bancompartir_Funciones();

		String JobId = obtenerJobID(vars);

		String[] encabezados = { "STORY_RESULT", "TITLE_RESULT", "RESULT_RESULT", "DATE_RESULT", "STABILITY_RESULT",
				"DURATION_RESULT" };
		String[] resultados = banco.obtenerArregloDeLineasResultsCsv(vars);

		for (String resultado : resultados) {
			String[] campos = resultado.trim().split(",");
			if (encabezados.length == campos.length) {
				Map<String, String> mapCampos = new LinkedHashMap<>();

				mapCampos.put("ID_JOB", JobId);

				int index = 0;
				for (String campo : campos) {
					mapCampos.put(encabezados[index], campo);
					index++;
				}

				guardarDatosEnTabla("JENKINS_RESULTS", mapCampos);
			} else {
				throw new Exception("Los campos no corresponden al archivo CSV cargado");
			}
		}
	}

}
