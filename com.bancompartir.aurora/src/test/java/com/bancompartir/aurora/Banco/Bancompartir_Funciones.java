/**
 * Copyright (c) 2017, Choucair Cárdenas Testing S.A.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.bancompartir.aurora.Banco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.choucair.framework.FW_Utilidades;

/**
 * Contiene las funciones auxiliares específicas del Banco.
 * <p>
 * Los métodos implementados en esta clase son de caracter <b>CONFIDENCIAL</b> y
 * de <b>USO EXCLUSIVO</b> del proyecto <b>BANCOMPARTIR - AURORA</b> y son
 * aplicables únicamente para el caso particular de la automatización ante el
 * cliente.
 * <p>
 * No debe ser replicado ante otros clientes.
 * 
 * @author cmurciag
 * @version 1.1
 * @since 26/12/2017
 * 
 * @see Bancompartir_DB
 * @see Bancompartir_variables
 */
public class Bancompartir_Funciones {

	// Variables
	String strRutaOrigenClientes = "\\\\192.168.0.16\\16-001 Proyecto Aurora\\04 Implementacion\\3. Ejecucion\\Alcance\\Entregas-parciales PASIVO-CLIENTES\\MIGRACION\\bin\\cli\\creadas\\";
	String strRutaDestinoClientes = "\\\\192.168.0.16\\16-001 Proyecto Aurora\\04 Implementacion\\3. Ejecucion\\Alcance\\Entregas-parciales PASIVO-CLIENTES\\MIGRACION\\bin\\cli\\verificadas\\";
	String strNombreTablaClientes = "DD_Clientes_D";

	String strRutaOrigenCuentas = "\\\\192.168.0.16\\16-001 Proyecto Aurora\\04 Implementacion\\3. Ejecucion\\Alcance\\Entregas-parciales PASIVO-CLIENTES\\MIGRACION\\bin\\cta\\solicitadas\\";
	String strRutaDestinoCuentas = "\\\\192.168.0.16\\16-001 Proyecto Aurora\\04 Implementacion\\3. Ejecucion\\Alcance\\Entregas-parciales PASIVO-CLIENTES\\MIGRACION\\bin\\cta\\creadas\\";
	String strNombreTablaCuentas = "DD_Cuentas_D";

	// --- Métodos públicos ---
	/**
	 * Verifica si hay CSVs pendientes con datos de Clientes por consultar
	 * 
	 * @return booleano
	 */
	public boolean hayCsvClientesPendientes() {
		File[] archivos = obtenerArchivos(strRutaOrigenClientes);
		return archivos.length > 0;
	}

	/**
	 * Verifica si hay CSVs pendientes con datos de Cuentas por crear
	 * 
	 * @return booleano
	 */
	public boolean hayCsvCuentasPendientes() {
		File[] archivos = obtenerArchivos(strRutaOrigenCuentas);
		return archivos.length > 0;
	}

	/**
	 * Carga Todos los CSVs de los clientes Solicitados para cargue Masivo y que
	 * se supone ya están creados, con el fin de posteriormente buscar el número
	 * de cliente y actualizar la información
	 * 
	 * @throws Exception
	 */
	public void cargarCsvClientesCreados() throws Exception {
		String strRutaOrigen = strRutaOrigenClientes;
		String strRutaDestino = strRutaDestinoClientes;
		String strNombreTabla = strNombreTablaClientes;
		cargarCsvDesdeRuta(strRutaOrigen, strRutaDestino, strNombreTabla);
	}

	/**
	 * Carga Todos los CSVs de las cuentas Solicitadas para creación, con el fin
	 * de posteriormente buscar el número de cuenta y actualizar la información
	 * 
	 * @throws Exception
	 */
	public void cargarCsvCuentasCreadas() throws Exception {
		String strRutaOrigen = strRutaOrigenCuentas;
		String strRutaDestino = strRutaDestinoCuentas;
		String strNombreTabla = strNombreTablaCuentas;
		cargarCsvDesdeRuta(strRutaOrigen, strRutaDestino, strNombreTabla);
	}

	/*-- Métodos privados --*/
	/**
	 * Devuelve la lista de archivos alojados en una ruta
	 * 
	 * @param strRuta
	 *            Ruta donde se buscarán los archivos
	 * @return Arreglo de archivos tipo File alojados en la ruta
	 */
	private File[] obtenerArchivos(String strRuta) {
		File folder = new File(strRuta);
		File[] archivos = folder.listFiles();
		return archivos;
	}

	/**
	 * Carga todos los CSVs existentes en una ruta y los sube a una tabla de la
	 * base de datos, una vez quedan ingresados en la base de datos se llevan a
	 * otra ruta de destino
	 * 
	 * @param strRutaOrigen:
	 *            Ubicación donde se encuentran los archivos a subir
	 * @param strRutaDestino:
	 *            Ubicación donde se dejarán los archivos una vez subidos
	 * @param strNombreTabla:
	 *            Nombre de la tabla en la base de Datos. Si no existe se crea
	 *            la tabla.
	 * @throws Exception
	 *             No se lograron reubicar los archivos.
	 */
	private void cargarCsvDesdeRuta(String strRutaOrigen, String strRutaDestino, String strNombreTabla)
			throws Exception {
		// cargar csv's de carpeta "cli/creados"
		File[] archivos = obtenerArchivos(strRutaOrigen);

		if (archivos.length > 0) {
			// subir csv's a tabla de clientes Data Driven con "Ejecutado" en
			// falso
			for (File archivo : archivos) {
				// Carga el archivo a la Base de datos
				try {
					subirCSVaBD(strNombreTabla, archivo);

					// Mueve el archivo luego de cargarlo para quitarlo de la
					// carpeta de los pendientes
					if (!archivo.renameTo(new File(strRutaDestino + archivo.getName()))) {
						System.out.println(
								"El archivo " + archivo.getName() + " no se pudo mover al directorio de destino.");
						throw new Exception("No se puedo mover el archivo al directorio de destino");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error al cargar CSV: " + e.getMessage());
				}
			}
		} else {
			System.out.println("No hay archivos pendientes por consultar");
		}
	}

	/**
	 * Sube un CSV a una tabla en la Base de Datos Bancompartir
	 * 
	 * @param strNombreTabla
	 *            Nombre de la tabla existente o a crear
	 * @param filArchivo
	 *            variable tipo File del archivo CSV a subir / No realiza
	 *            validación de tipo de archivo
	 * @return
	 * @throws Exception
	 */
	private void subirCSVaBD(String strNombreTabla, File filArchivo) throws Exception {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		Bancompartir_DB bd = new Bancompartir_DB();

		try {
			// br = new BufferedReader(new FileReader(filArchivo));
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filArchivo), "ISO-8859-1"));
			// La primer línea es el encabezado
			String[] arrEncabezados = br.readLine().split(cvsSplitBy, -1);

			// El resto de líneas se suben a la tabla
			while ((line = br.readLine()) != null) {
				String[] arrValores = line.split(cvsSplitBy, -1);
				try {
					bd.guardarDatosPorEjecutar(strNombreTabla, arrEncabezados, arrValores);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error guardando datos: " + e.getMessage());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error cargando archivo: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Map<String, String> obtenerVariablesDeBuildXml(Map<String, String> map) {

		FW_Utilidades util = new FW_Utilidades();

		String strRutaAJob = map.get("JENKINS_HOME") + "\\jobs\\" + map.get("JOB_NAME") + "\\builds\\"
				+ map.get("BUILD_ID") + "\\build.xml";

		Map<String, String> xPaths = new LinkedHashMap<>();
		xPaths.put("USERNAME_BUILD", "//userId/text()");
		xPaths.put("TIMESTAMP_BUILD", "//timestamp/text()");
		xPaths.put("STARTTIME_BUILD", "//startTime/text()");
		xPaths.put("RESULT_BUILD", "//result/text()");
		xPaths.put("DURATION_BUILD", "//duration/text()");
		xPaths.put("CHARSET_BUILD", "//charset/text()");
		xPaths.put("WORKSPACE_BUILD", "//workspace/text()");
		xPaths.put("TESTCOUNT_BUILD", "//hudson.tasks.junit.TestResultAction/totalCount/text()");

		return util.obtenerCamposDeArchivoXML(strRutaAJob, xPaths);
	}

	public Map<String, String> obtenerVariablesDeSummaryTxt(Map<String, String> map) {
		FW_Utilidades util = new FW_Utilidades();

		String strRutaArchivo = map.get("JENKINS_HOME") + "\\jobs\\" + map.get("JOB_NAME") + "\\builds\\"
				+ map.get("BUILD_ID") + "\\thucydidesReports\\summary.txt";

		Map<String, String> keys = new LinkedHashMap<>();
		keys.put("DATE_REPORT_SUMMARY", "Serenity report generated");
		keys.put("SCENARIOS_SUMMARY", "test scenarios");
		keys.put("PASSED_SUMMARY", "Passed:");
		keys.put("FAILED_SUMMARY", "Failed:");
		keys.put("FAILED_WITH_ERRORS_SUMMARY", "Failed with errors:");
		keys.put("COMPROMISED_SUMMARY", "Compromised:");
		keys.put("PENDING_SUMMARY", "Pending:");
		keys.put("IGNORED_SUMMARY", "Ignored:");
		keys.put("SKIPPED_SUMMARY", "Skipped:");

		return util.obtenerCamposDeArchivoTXT(strRutaArchivo, keys);
	}

	public String[] obtenerArregloDeLineasResultsCsv(Map<String, String> map) {

		List<String> lines = new ArrayList<String>();
		String[] arrLines = null;

		String strRutaArchivo = map.get("JENKINS_HOME") + "\\jobs\\" + map.get("JOB_NAME") + "\\builds\\"
				+ map.get("BUILD_ID") + "\\thucydidesReports\\results.csv";

		BufferedReader br = null;
		String line;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(strRutaArchivo), "ISO-8859-1"));

			// La primer línea es el encabezado
			br.readLine();

			// El resto de líneas se incluyen
			while ((line = br.readLine()) != null) {
				lines.add(line.replace("\"", ""));
			}

			arrLines = new String[lines.size()];
			arrLines = lines.toArray(arrLines);

			return arrLines;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return arrLines;
	}

}
