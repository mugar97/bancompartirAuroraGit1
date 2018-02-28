package com.choucair.framework;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;



public class FW_FuncionesDB {
	
	public void crearTablaTemporal(Connection dbCon, String strNombre, String[] arrEncabezados, String[] arrTipos)throws SQLException {

		// TODO Llamar al procedimiento de crear, sin embargo ya está el procedimiento de insertar creando
	}
	
	public void crearTablaTemporal(Connection dbCon, String strNombre, String[] arrEncabezados) throws SQLException{
		String[] arrTipos = new String [arrEncabezados.length];
		Arrays.fill(arrTipos, "varchar2(500)");
		crearTablaTemporal(dbCon, strNombre, arrEncabezados, arrTipos);
	}
	
	public void insertarCreando(Connection dbCon, String strNombreTabla, String[] arrEncabezados, String[] arrValores) throws Exception {
		// Se llama el procedimiento almacenado AUT_PRC_INSERTAR_CREANDO para insertar en una tabla, que se crea en caso de no existir
		try
		{
			//System.out.println(arrEncabezados.length + "-" + arrValores.length);
			String strEncabezados = String.join(",", arrEncabezados);
			String strValores = String.join(",", arrValores);
			
			String sp_aut_prc_insertar_creando = "{call CHOUCAIR.AUT_PRC_INSERTAR_CREANDO(?,?,?,?)}";
			CallableStatement callbaseStatement = dbCon.prepareCall(sp_aut_prc_insertar_creando);
			
			callbaseStatement.setString("inNombreTabla", strNombreTabla);
			callbaseStatement.setString("inNombresColumnas", strEncabezados);
			callbaseStatement.setString("inValores", strValores);
			callbaseStatement.registerOutParameter("outSalida", Types.NVARCHAR);
			
			//Ejecuta el query
			callbaseStatement.execute();
			//System.out.println(callbaseStatement.getString("outSalida"));
		}
		catch (Exception e)
		{
			throw new Exception("Ocurrió un problema insertando los valores en la tabla solicitada: " + e.getMessage());
		}
		
	}
	
	public void insertarCreando(Connection dbCon, String strNombreTabla, Map<String, String> map) throws Exception {
		String[] keys = new String[map.size()];
		String[] values = new String[map.size()];
		int index = 0;
		for (Entry<String, String> mapEntry : map.entrySet()) {
		    keys[index] = mapEntry.getKey();
		    values[index] = mapEntry.getValue();
		    index++;
		}
		insertarCreando(dbCon,strNombreTabla,keys,values);
	}

	public void limpiarTablasTemporales(Connection dbCon) throws Exception {
		
		// Se llama el procedimiento almacenado AUT_PRC_BORRAR_TEMPORALES para borrar las tablas y secuencias creadas
		try
		{
			String sp_aut_prc_borrar_temporales = "{call CHOUCAIR.AUT_PRC_BORRAR_TEMPORALES()}";
			CallableStatement callbaseStatement = dbCon.prepareCall(sp_aut_prc_borrar_temporales);
			
			//Ejecuta el query
			callbaseStatement.execute();
		}
		catch (Exception e)
		{
			throw new Exception("Ocurrió un problema eliminando las tablas y secuencias temporales: " + e.getMessage());
		}
	}
	
}
