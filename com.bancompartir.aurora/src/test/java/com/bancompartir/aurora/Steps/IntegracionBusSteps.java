package com.bancompartir.aurora.Steps;

import static org.hamcrest.Matchers.equalTo;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class IntegracionBusSteps extends PageObject {

	private static final long serialVersionUID = 1L;
	String strURL = "https://api.github.com/users/";
	
	@Step
	public void comprobrarPrecondicion() {
		SerenityRest.when().get(strURL + "eugenp");
	}

	@Step
	public void realizarPrueba() {
		SerenityRest.then().assertThat().body("login", equalTo("eugenp"));
	}

	@Step
	public void validarResultado() {
		SerenityRest.then().statusCode(200);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
