package com.bancompartir.aurora;

import java.util.List;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.bancompartir.aurora.Banco.Bancompartir_DB;
import com.bancompartir.aurora.Suites.DD_CuentasApertura;

public class Prec_CreacionCuentasDD {
	
	private void ejecutarCreacionCuentas() {
		Bancompartir_DB banco_db = new Bancompartir_DB();
		try {
			if (banco_db.hayCreacionCuentasPendientes()) {
				JUnitCore engine = new JUnitCore();
				engine.addListener(new TextListener(System.out));
				Result result = engine.run(DD_CuentasApertura.class);
				List<Failure> failures = result.getFailures();
				for (Failure failure : failures) {
					System.out.println(failure.getMessage());
				}
			} else {
				System.out.println("No hay cuentas pendientes por crear");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Prec_CreacionCuentasDD app = new Prec_CreacionCuentasDD();
		app.ejecutarCreacionCuentas();
	}
}
