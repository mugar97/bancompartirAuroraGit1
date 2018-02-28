package com.bancompartir.aurora.Steps;

import com.bancompartir.aurora.PageObjects.PruebasPage;
import static org.hamcrest.Matchers.equalTo;

import net.serenitybdd.rest.SerenityRest;

public class PruebasSteps {

	PruebasPage pruebasPage;
	
	public void precondicion() {
		pruebasPage.precondicion();
	}

	public void ejecuto_paso() {
		pruebasPage.ejecuto_paso();
	}

	public void verifico() {
		pruebasPage.verifico();
	}

	String strURL = "https://api.github.com/users/";
	//String strURL = "http://10.121.240.8:7800/services/accounts/AccountGMFMng/";
	
	public void consumirElServicioBase() {
		SerenityRest.given().get(strURL + "eugenp");
	}

	public void verificarElEstado() {
		SerenityRest.then().statusCode(200);
	}

	public void validarValorEnRespuesta() {
		SerenityRest.then().assertThat().body("login", equalTo("eugenp"));
	}
}
