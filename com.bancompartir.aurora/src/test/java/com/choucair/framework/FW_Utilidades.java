package com.choucair.framework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FW_Utilidades {
	
	
	/**
	 * Función para convertir un grupo de argumentos en un mapa, separando el
	 * nombre de la variable y el valor por un caracter. Cada argumento puede
	 * entrar como "Nombre|Valor" donde "|" sería el caracter splitedBy
	 * 
	 * @param arr
	 *            Arreglo de argumentos de entrada
	 * @param splitedBy
	 *            Caracter especial que separa el nombre de la variable a su
	 *            valor dentro de cada argumento, si es null se indizará con un
	 *            String incremental
	 * @return Mapa que contiene los valores de las variables indizadas por los
	 *         nombres de las mismas
	 */
	public Map<String, String> getVariablesMapFromArray(String[] arr, String splitedBy) {
		int index = 0;

		Map<String, String> map = new LinkedHashMap<>();

		for (String strVarAndValue : arr) {
			if (splitedBy != null && strVarAndValue.contains(splitedBy)) {
				String[] arrVarAndValue = strVarAndValue.replace("'", "").split(splitedBy, 2);
				map.put(arrVarAndValue[0], arrVarAndValue[1]);
				System.out.println(arrVarAndValue[0] + "<->" + arrVarAndValue[1]);
			} else {
				map.put(String.valueOf(index), strVarAndValue);
				System.out.println(String.valueOf(index) + "<->" + strVarAndValue);
				index++;
			}
		}

		return map;
	}

	/**
	 * Convierte un array de Strings a un Mapa indizado por un número incremental
	 * @param arr	Arreglo de Strings
	 * @return Mapa con los valores ingresados
	 */
	public Map<String, String> getVariablesMapFromArray(String[] arr) {
		return getVariablesMapFromArray(arr, null);
	}

	public Map<String, String> obtenerCamposDeArchivoXML(String strRutaAJob, Map<String, String> xPaths) {
		
		Map<String, String> mapCampos = new LinkedHashMap<String, String>();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(strRutaAJob);
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			Map<String, String> reversedMap = new LinkedHashMap<String, String>(xPaths);

			for (Map.Entry<String,String> entry : reversedMap.entrySet()) {
				mapCampos.put(entry.getKey(), xpath.compile(entry.getValue()).evaluate(doc));
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} 
		
		return mapCampos;
	}

	public String getPropertyFromFile(String strFile, String strProperty) {
		Properties prop = new Properties();
		InputStream input = null;
	
		try {
	
			input = new FileInputStream(strFile);
	
			// load a properties file
			prop.load(input);
	
			// get the property value and print it out
			return prop.getProperty(strProperty);
	
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public Map<String, String> obtenerCamposDeArchivoTXT(String strRutaArchivo, Map<String, String> keys) {
		Map<String, String> mapCampos = new LinkedHashMap<String, String>();
		
		Map<String, String> reversedMap = new LinkedHashMap<String, String>(keys);

		for (Map.Entry<String,String> entry : reversedMap.entrySet()) {
			mapCampos.put(entry.getKey(), obtenerValorPorContenidoTXT(strRutaArchivo,entry.getValue()));
		}
		
		return mapCampos;
	}

	public String obtenerValorPorContenidoTXT(String strRutaArchivo, String strGlue) {
		String out = null;
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(strRutaArchivo));
			String line;
			while((line = br.readLine()) != null){
				if(line.trim().contains(strGlue)) {
					out = line.replace(strGlue, "").trim();
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return out;
	}
}
