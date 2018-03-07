package com.bancompartir.aurora;

import java.util.List;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.bancompartir.aurora.Banco.Bancompartir_DB;
import com.bancompartir.aurora.Suites.DD_ClientesAdicionInfoFinanciera;

public class Prec_ActualizacionClientesDD {

	private void ejecutarConsultaClientesMasivos() {
		Bancompartir_DB banco_db = new Bancompartir_DB();
		try {

			// Ejecución de pruebas DataDriven
			if (banco_db.hayConsultasClientesPendientes()) {
				// Ejecutar Consulta de clientes DataDriven
				JUnitCore engine = new JUnitCore();
				engine.addListener(new TextListener(System.out));
				Result result = engine.run(DD_ClientesAdicionInfoFinanciera.class);
				List<Failure> failures = result.getFailures();
				for (Failure failure : failures) {
					System.out.println(failure.getMessage());
				}
			} else {
				System.out.println("No hay clientes programados para ejecución");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Prec_ActualizacionClientesDD app = new Prec_ActualizacionClientesDD();
		app.ejecutarConsultaClientesMasivos();
	}
}
