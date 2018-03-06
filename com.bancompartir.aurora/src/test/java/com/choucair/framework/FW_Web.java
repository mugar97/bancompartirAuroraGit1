package com.choucair.framework;

import java.util.LinkedHashSet;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.Serenity;

public class FW_Web {

	private Set<String> handles;
	private Set<String> id_handles;
	
	public FW_Web() {
		handles = new LinkedHashSet<String>();
		id_handles = new LinkedHashSet<String>();
	}
	
	/**
	 * Método que devuelve el índice en el cual se encuentra un objeto en un Set de objetos.
	 * Se utiliza para buscar el índice de una ventana en el id_handless y poder retornar el handle correspondiente
	 * @param set		Set de objetos
	 * @param value		Objeto que se busca en el Set
	 * @return			Indice del objeto en el Set
	 */
	public static int getIndex(Set<? extends Object> set, Object value) {
	   int result = 0;
	   for (Object entry:set) {
	     if (entry.equals(value)) return result;
	     result++;
	   }
	   return -1;
	}
	
	/**
	 * Agrega un identificador a la ventana inicial. Debe llamarse al iniciar el aplicativo, antes de abrir otras ventanas
	 * @param pHandles							Set de strings actual (Debe contener una ventana)
	 * @param strIdentificadorVentanaInicial	Identificador para la ventana principal
	 * @throws Exception	Hay más de una ventana abierta y no se sabe cuál es la principal 
	 */
	public void registrarVentanaInicial (Set<String> pHandles, String strIdentificadorVentanaInicial) throws Exception {
		if (pHandles.size() != 1) {
			handles = pHandles;
			id_handles.add(strIdentificadorVentanaInicial);
		} else {
			throw new Exception("No se puede registrar más de una ventana como Ventana Inicial.");
		}
	}
	
	public void registrarVentanaInicial (String pHandles, String strIdentificadorVentanaInicial) {
		handles.add(pHandles);
		id_handles.add(strIdentificadorVentanaInicial);
	}
	
	public void registrarVentanaInicial (WebDriver driver, String strIdentificadorVentanaInicial) throws Exception {
		registrarVentanaInicial(driver.getWindowHandle(),strIdentificadorVentanaInicial);
	}
	
	/**
	 * Agrega un identificador a la primera ventana nueva que se encuentre.
	 * @param pHandles							Set de las ventanas actuales
	 * @param strIdentificadorNuevaVentana		Identificador de nueva ventana
	 */
	public void registrarNuevaVentana(Set<String> pHandles, String strIdentificadorNuevaVentana) {
		for (String actual : pHandles) {
			if (!handles.contains(actual)) {
				handles.add(actual);
				id_handles.add(strIdentificadorNuevaVentana);
				break;
			}
		}
	}
	
	/**
	 * Agrega un identificador a la primera ventana nueva que se encuentre.
	 * @param driver WebDriver
	 * @param strIdentificadorNuevaVentana		Identificador de nueva ventana
	 */
	public void registrarNuevaVentana(WebDriver driver, String strIdentificadorNuevaVentana) {
		registrarNuevaVentana(driver.getWindowHandles(),strIdentificadorNuevaVentana);
	}
	
	/**
	 * Devuelve el handle de la ventana a partir de un identificador asignado
	 * @param strIdentificadorVentana	Identificador de ventana
	 * @return	Handle de ventana
	 * @throws Exception	El identificador no se ha registrado ni vinculado con una pantalla
	 */
	public String obtenerVentanaPorIdentificador(String strIdentificadorVentana) throws Exception {
		int index = getIndex(id_handles,strIdentificadorVentana);
		if (index != -1) {
			return (String) handles.toArray()[index];
		} else {
			throw new Exception("El identificador no es válido");
		}
	}
	
	/**
	 * Asigna el driver a una ventana etiquetada anteriormente por un identificador 
	 * @param driver			Driver web
	 * @param strIdentificador	Identificador de la ventana registrada anteriormente
	 * @throws Exception		Exepción al intentar obtener la ventana solicitada
	 */
	public void cambiarVentana(WebDriver driver, String strIdentificador) throws Exception {
		String windowHandle = obtenerVentanaPorIdentificador(strIdentificador);
		driver.switchTo().window(windowHandle);
	}

	
	public void olvidarVentanaPorIdentificador(String strIdentificadorVentana) throws Exception {
		int index = getIndex(id_handles,strIdentificadorVentana);
		if (index != -1) {
			handles.remove(handles.toArray()[index]);
			id_handles.remove(strIdentificadorVentana);
		} else {
			throw new Exception("El identificador no es válido");
		}
	}
	
	public void cerrarVentanaYEnfocaVentana(WebDriver driver,String strIdentificadorVentanaACerrar,String strIdentificadorVentanaAEnfocar) throws Exception {
		System.out.println("cerrando: " + strIdentificadorVentanaACerrar + ". enfocando: " + strIdentificadorVentanaAEnfocar);
		cambiarVentana(driver,strIdentificadorVentanaACerrar);
		driver.close();
		olvidarVentanaPorIdentificador(strIdentificadorVentanaACerrar);
		cambiarVentana(driver,strIdentificadorVentanaAEnfocar);
	}

	public void clickDerecho(WebDriver driver, WebElement elemento) {
		Actions action = new Actions(driver).contextClick(elemento); 
		action.build().perform();
	}
	
	public void tomarCapturasVentanas(WebDriver driver) {
		String hi = driver.getWindowHandle();
		Set<String> hs = driver.getWindowHandles();	
		for (String h : hs) {
			driver.switchTo().window(h);
			Serenity.takeScreenshot();
		}
		driver.switchTo().window(hi);
	}

}
