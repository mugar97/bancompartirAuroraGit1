package com.bancompartir.aurora;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class SOAPClientSAAJ {
	
	static String xml = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:v1='urn://bancompartir.com/services/accounts/accountgmfmng/v1.0' xmlns:v11='urn://bancompartir.com/accounts/event/v1' xmlns:com='urn://bancompartir.com/xsd/common'>"
			+"   <soapenv:Header/>"
			+"   <soapenv:Body>"
			+"      <v1:getGMFRequest>"
			+"         <v11:GMFInqRq>"
			+"            <com:RqUID>" + java.util.UUID.randomUUID() + "</com:RqUID>"
			+"            <com:NetworkTrnInfo>"
			+"               <com:NetworkOwner>123</com:NetworkOwner>"
			+"               <com:TerminalId>OnBase123</com:TerminalId>"
			+"               <com:NetworkRefId>ONBASE</com:NetworkRefId>"
			+"               <com:IPAdress>192.168.1.1</com:IPAdress>"
			+"            </com:NetworkTrnInfo>"
			+"            <com:CustId>"
			+"               <com:CustPermId>onbase1</com:CustPermId>"
			+"               <com:CustLoginId>user1ibs</com:CustLoginId>"
			+"            </com:CustId>"
			+"            <com:MaxRec>1</com:MaxRec>"
			+"            <com:ClientDt>2018-10-30T09:00:00</com:ClientDt>"
			+"            <com:IdentType>123</com:IdentType>"
			+"            <com:IdentNum>234</com:IdentNum>"
			+"         </v11:GMFInqRq>"
			+"      </v1:getGMFRequest>"
			+"   </soapenv:Body>"
			+"</soapenv:Envelope>";
	
    // SAAJ - SOAP Client Testing
    public static void main(String args[]) {
        /*
            The example below requests from the Web Service at:
             http://www.webservicex.net/uszip.asmx?op=GetInfoByCity


            To call other WS, change the parameters below, which are:
             - the SOAP Endpoint URL (that is, where the service is responding from)
             - the SOAP Action

            Also change the contents of the method createSoapEnvelope() in this class. It constructs
             the inner part of the SOAP envelope that is actually sent.
         */
        String soapEndpointUrl = "http://10.121.240.8:7800/services/accounts/AccountGMFMng";
        String soapAction = "urn://bancompartir.com/services/accounts/accountgmfmng/v1.0/getGMF";

        callSoapWebService(soapEndpointUrl, soapAction);
    }

    

    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        /*MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();*/
    	
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));

        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPBody body = soapMessage.getSOAPBody();
        body.addDocument(doc);
        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

}