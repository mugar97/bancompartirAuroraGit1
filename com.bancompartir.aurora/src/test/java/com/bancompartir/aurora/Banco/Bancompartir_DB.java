package com.bancompartir.aurora.Banco;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import com.choucair.framework.FW_ConexionBD;
import com.choucair.framework.FW_FuncionesDB;

public class Bancompartir_DB {

	private String strTablaClientes = "DD_Clientes_D";
	private String strTablaCuentas = "DD_CUENTAS_D";

	public void guardarDatosPorEjecutar(String strNombreTabla, String[] arrEncabezados, String[] arrValores)
			throws Exception {
		try {
			FW_FuncionesDB fw_db_fun = new FW_FuncionesDB();
			FW_ConexionBD fw_db = new FW_ConexionBD("oracle_bancompartir");

			fw_db.abrirConexion();
			System.out.println(arrEncabezados.length + "-" + arrValores.length);
			System.out.println(Arrays.toString(arrEncabezados));
			System.out.println(Arrays.toString(arrValores));
			String[] arrEncabezadosFinales = (String.join(",", arrEncabezados) + ",FECHA,EJECUTADO").split(",");
			String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			String[] arrValoresFinales = (String.join(",", arrValores) + "," + timeStamp + ",0").split(",");

			fw_db_fun.insertarCreando(fw_db.getCon(), strNombreTabla, arrEncabezadosFinales, arrValoresFinales);

			fw_db.cerrarConexion();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error guardando Datos: " + e.getMessage());
		}

	}

	/**
	 * Método que retorna todos los clientes pendientes de Consulta
	 * 
	 * @return
	 */
	public ResultSet consultarClientesPendientes() {
		return consultarTablaPendientes(strTablaClientes);
	}

	public boolean hayConsultasClientesPendientes() {
		return verificarPendientesTabla(strTablaClientes);
	}

	public boolean hayCreacionCuentasPendientes() {
		return verificarPendientesTabla(strTablaCuentas);
	}

	public ResultSet consultarCuentasPendientes() {
		return consultarTablaPendientes(strTablaCuentas);
	}

	public void actualizarNumeroCliente(String strID, String strNumeroCliente) {
		actualizarCampoPorID(strTablaClientes, "IBSNUMCLI", strNumeroCliente, strID);
	}

	public void actualizarNumeroCuenta(String strID, String strNumeroDeCuenta) {
		actualizarCampoPorID(strTablaCuentas, "IBSNUMCTA", strNumeroDeCuenta, strID);
	}

	public void guardaErrorConsultandoCliente(String strID) {
		actualizarCampoPorID(strTablaClientes, "IBSNUMCLI", "ERROR", strID);
	}

	public void guardaValorBooleanoEnCampoCliente(String strID, String strCampo, boolean blnValor) {
		String strValor = "0";
		if (blnValor) {
			strValor = "1";
		}
		actualizarCampoPorID(strTablaClientes, strCampo, strValor, strID);
	}

	public void guardaValorEnCampoCliente(String strID, String strCampo, String strValor) {
		actualizarCampoPorID(strTablaClientes, strCampo, strValor, strID);
	}

	public void guardaValorEnCampoCuentas(String strID, String strCampo, String strValor) {
		actualizarCampoPorID(strTablaCuentas, strCampo, strValor, strID);
	}

	public void guardaErrorConsultandoCuenta(String strID) {
		actualizarCampoPorID(strTablaCuentas, "IBSNUMCTA", "ERROR", strID);
	}

	private ResultSet consultarTablaPendientes(String strNombreTabla) {
		try {

			FW_ConexionBD fw_db;
			fw_db = new FW_ConexionBD("oracle_bancompartir");

			fw_db.abrirConexion();

			Statement stmt = fw_db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT * FROM CHOUCAIR.AUT_" + strNombreTabla.toUpperCase()
					+ " where EJECUTADO ='0' OR EJECUTADO = NULL");

			// fw_db.cerrarConexion();

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

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

	private void guardarDatosEnTabla(String strNombreTabla, Map<String, String> mapCampos)
			throws Exception {
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
			mapCampos.put(strCampo+"_JOB", vars.get(strCampo));
		}

		guardarDatosEnTabla("JENKINS_JOBS", mapCampos);

	}

	public void guardarHistorialFinalBuild(Map<String, String> vars) throws Exception {

		Bancompartir_Funciones banco = new Bancompartir_Funciones();
		
		Map<String, String> mapCampos = new LinkedHashMap<>();
		
		//ID del JOB
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
		
		String[] encabezados = {"STORY_RESULT","TITLE_RESULT","RESULT_RESULT","DATE_RESULT","STABILITY_RESULT","DURATION_RESULT"};
		String[] resultados = banco.obtenerArregloDeLineasResultsCsv(vars);
		
		for (String resultado : resultados) {
			String[] campos = resultado.trim().split(",");
			if(encabezados.length == campos.length) {
				Map<String, String> mapCampos = new LinkedHashMap<>();
				
				mapCampos.put("ID_JOB", JobId);
				
				int index = 0;
				for (String campo : campos) {
					mapCampos.put(encabezados[index], campo);
					index ++;
				}
				
				guardarDatosEnTabla("JENKINS_RESULTS", mapCampos);
			} else {
				throw new Exception("Los campos no corresponden al archivo CSV cargado");
			}
		}
	}

}
