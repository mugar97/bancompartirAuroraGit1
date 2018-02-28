package com.choucair.framework;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;

public class FW_ConexionBD {

	//Constantes
	public enum tipo_conexion {
		novalue, oracle_bancompartir;
		
		public static tipo_conexion getValue(String str){
	        try{
	        	return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	
	//Variables
    private String str_Servidor= null;
    private String str_Puerto= null;
    private String str_Usuario= null;
    private String str_Password= null;
    private String str_Datapool= null;

    private Connection Cnn = null;
	
	//Objetos
	Properties prop = new Properties (); 
	
	//Constructores
	public FW_ConexionBD () {
		
	}
	
	public FW_ConexionBD (String strSelector) throws Exception {
		switch (tipo_conexion.getValue(strSelector)) {
		case oracle_bancompartir:
			prop.load(new FileInputStream("config.properties"));
			str_Datapool = prop.getProperty("mSID");
			str_Usuario = prop.getProperty("dbuser");
			str_Password = prop.getProperty("dbpassword");
			str_Servidor = prop.getProperty("server"); 
			str_Puerto = prop.getProperty("Port");
			
			break;
		default:
			break;
		}
	}
	
	
	public Connection getCon() {
		return Cnn;
	}
	
	public void registrarDriver() throws SQLException {
        OracleDriver oracleDriver = new oracle.jdbc.driver.OracleDriver();
        DriverManager.registerDriver(oracleDriver);
    }
	
	public void abrirConexion() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
        String strConnection = "jdbc:oracle:thin:@"+ str_Servidor + ":" + str_Puerto + ":" + str_Datapool;
        registrarDriver();
        Cnn = DriverManager.getConnection(strConnection, str_Usuario, str_Password);
	}

	public void cerrarConexion() throws Exception {
		Cnn.close();
	}
	
}
