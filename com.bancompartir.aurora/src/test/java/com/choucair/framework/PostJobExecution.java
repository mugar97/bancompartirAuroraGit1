package com.choucair.framework;

import java.util.Map;

import com.bancompartir.aurora.Banco.Bancompartir_DB;

public class PostJobExecution {

	public static void main(String[] args) {
		FW_Utilidades util = new FW_Utilidades();
		Bancompartir_DB banco = new Bancompartir_DB();
		
		Map<String,String> vars = util.getVariablesMapFromArray(args, ":");
		
		try {
			banco.guardarHistorialFinalBuild(vars);
			banco.guardarHistorialFinalResults(vars);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
