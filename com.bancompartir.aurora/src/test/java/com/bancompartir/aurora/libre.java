package com.bancompartir.aurora;

import java.util.LinkedHashMap;
import java.util.Map;

import com.choucair.framework.FW_Utilidades;

public class libre {

	public static void main(String[] args) {
		FW_Utilidades util = new FW_Utilidades();
		
		String strRuta = "C:/Users/trc_cristhian.murcia/.jenkins/jobs/Prueba Exportando Job/builds/46/thucydidesReports/summary.txt";

		Map<String, String> keys = new LinkedHashMap<>();
		keys.put("DATE_REPORT", "Serenity report generated");
		keys.put("SCENARIOS", "test scenarios");
		keys.put("PASSED", "Passed:");
		keys.put("FAILED", "Failed:");
		keys.put("FAILED_WITH_ERRORS", "Failed with errors:");
		keys.put("COMPROMISED", "Compromised:");
		keys.put("PENDING", "Pending:");
		keys.put("IGNORED", "Ignored:");
		keys.put("SKIPPED", "Skipped:");
		
		Map<String, String> reversedMap = new LinkedHashMap<String, String>(util.obtenerCamposDeArchivoTXT(strRuta, keys));

		for (Map.Entry<String,String> entry : reversedMap.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
		
		System.out.println(java.util.UUID.randomUUID());
	}

}
