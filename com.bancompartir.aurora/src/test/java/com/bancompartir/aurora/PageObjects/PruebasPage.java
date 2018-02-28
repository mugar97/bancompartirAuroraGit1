package com.bancompartir.aurora.PageObjects;

import net.serenitybdd.core.pages.PageObject;

public class PruebasPage extends PageObject {

	public void precondicion() {
		System.out.println("ejecutando precondicion...");
	}

	public void ejecuto_paso() {
		System.out.println("ejecutando paso...");
	}

	public void verifico() {
		System.out.println("ejecutando verificación...");
	}

}
