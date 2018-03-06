/**
 * Copyright (c) 2017, Choucair Cárdenas Testing S.A.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.bancompartir.aurora.Banco;

/**
 * Contiene variables que pueden usarse para guardar información temporal del
 * cliente durante una ejecución.
 * <p>
 * Los métodos implementados en esta clase son de caracter <b>CONFIDENCIAL</b> y
 * de <b>USO EXCLUSIVO</b> del proyecto <b>BANCOMPARTIR - AURORA</b> y son
 * aplicables únicamente para el caso particular de la automatización ante el
 * cliente.
 * <p>
 * No debe ser replicado ante otros clientes.
 * 
 * @author cmurciag
 * @version 1.0
 * @since 11/12/2017
 * 
 * @see Bancompartir_DB
 * @see Bancompartir_Funciones
 */
public class Bancompartir_variables {

	// Variables
	private String strAnalista;
	private String strCasoDePrueba;
	private String strNumeroDeCuenta;
	private String strNumeroDeCliente;
	private String strAccionCuenta;

	// Constructor
	public Bancompartir_variables() {
	}

	// Encapsulamiento de variables
	public void setAnalista(String pAnalista) {
		strAnalista = pAnalista;
	}

	public String getAnalista() {
		return strAnalista;
	}

	public void setCasoDePrueba(String pCasoDePrueba) {
		strCasoDePrueba = pCasoDePrueba;
	}

	public String getCasoDePrueba() {
		return strCasoDePrueba;
	}

	public void setNumeroDeCuenta(String pNumeroDeCuenta) {
		strNumeroDeCuenta = pNumeroDeCuenta;
	}

	public String getNumeroDeCuenta() {
		return strNumeroDeCuenta;
	}

	public void setNumeroDeCliente(String pNumeroDeCliente) {
		strNumeroDeCliente = pNumeroDeCliente;
	}

	public String getNumeroDeCliente() {
		return strNumeroDeCliente;
	}

	public void setAccionCuenta(String pAccionCuenta) {
		strAccionCuenta = pAccionCuenta;
	}

	public String getAccionCuenta() {
		return strAccionCuenta;
	}

}
