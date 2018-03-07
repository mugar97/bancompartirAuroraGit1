package com.bancompartir.aurora;

import java.sql.ResultSet;

import com.bancompartir.aurora.Banco.Bancompartir_DB;
import com.bancompartir.aurora.Banco.Bancompartir_Funciones;


public class ActualizarDD {

	public void actualizarClientesMasivos() {
		Bancompartir_Funciones banco_fnc = new Bancompartir_Funciones();
		Bancompartir_DB banco_db = new Bancompartir_DB();

		try {

			// Cargar csv's de clientes creados, subirlos a la Base de datos y
			// moverlos de la carpeta origen a la destino
			if (banco_fnc.hayCsvClientesPendientes()) {
				banco_fnc.cargarCsvClientesCreados();
				System.out.println("Se agregaron Clientes a la cola");
			} else {
				System.out.println("No hay clientes pendientes por poner en cola");
			}

			// Ejecutar los pasos para consulta de clientes (El cual lee los "ejecutados" en falso)
			if (banco_db.hayConsultasClientesPendientes()) {
				// Llenar CSV para DataDriven
				String archivo = "src\\test\\resources\\datadriven\\CLIENTES_DD.csv";
				ResultSet rs = banco_db.consultarClientesPendientes();
				banco_db.actualizarCsvDataDriven(archivo, rs);
				System.out.println("Se armó el CSV CLIENTES_DD");
			} else {
				System.out.println("No hay clientes pendientes por ejecutar");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarCuentasPendientes() {
		Bancompartir_Funciones banco_fnc = new Bancompartir_Funciones();
		Bancompartir_DB banco_db = new Bancompartir_DB();

		try {

			// cargar csv's de cuentas solicitadas, subirlas a la Base de datos
			// y moverlos de la carpeta origen a la destino
			if (banco_fnc.hayCsvCuentasPendientes()) {
				banco_fnc.cargarCsvCuentasCreadas();
				System.out.println("Se agregaron Cuentas a la cola");
			} else {
				System.out.println("No hay cuentas pendientes por poner en cola");
			}

			// Ejecutar los pasos para consulta de cuentas (El cual lee los
			// "ejecutados" en falso)
			if (banco_db.hayCreacionCuentasPendientes()) {
				// Llenar CSV para DataDriven
				String archivo = "src\\test\\resources\\datadriven\\CUENTAS_DD.csv";
				ResultSet rs = banco_db.consultarCuentasPendientes();
				banco_db.actualizarCsvDataDriven(archivo, rs);
				System.out.println("Se armó el CSV CUENTAS_DD");
			} else {
				System.out.println("No hay cuentas pendientes por crear");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ActualizarDD app = new ActualizarDD();
		app.actualizarClientesMasivos();
		app.actualizarCuentasPendientes();
	}
}
