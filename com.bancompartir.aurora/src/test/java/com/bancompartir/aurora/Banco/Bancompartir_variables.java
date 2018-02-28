package com.bancompartir.aurora.Banco;

public class Bancompartir_variables {

	//Variables
	private String strAnalista;
	private String strCasoDePrueba;
	private String strNumeroDeCuenta;
	private String strNumeroDeCliente;
	private String strAccionCuenta;
	
	//Constructor
	public Bancompartir_variables(){}
	
	//Encapsulamiento de variables
	public void setAnalista(String pAnalista){strAnalista = pAnalista;}
	public String getAnalista(){return strAnalista;}
	
	public void setCasoDePrueba(String pCasoDePrueba){strCasoDePrueba = pCasoDePrueba;}
	public String getCasoDePrueba(){return strCasoDePrueba;}
	
	public void setNumeroDeCuenta(String pNumeroDeCuenta){strNumeroDeCuenta = pNumeroDeCuenta;}
	public String getNumeroDeCuenta(){return strNumeroDeCuenta;}
	
	public void setNumeroDeCliente(String pNumeroDeCliente){strNumeroDeCliente = pNumeroDeCliente;}
	public String getNumeroDeCliente(){return strNumeroDeCliente;}
	
	public void setAccionCuenta(String pAccionCuenta){strAccionCuenta = pAccionCuenta;}
	public String getAccionCuenta(){return strAccionCuenta;}
	
}
