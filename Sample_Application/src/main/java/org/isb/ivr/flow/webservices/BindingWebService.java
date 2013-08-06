package org.isb.ivr.flow.webservices;

import java.rmi.MarshalException;

import org.isb.ivr.flow.webservices.jaxbbeans.binding.Op1;
import org.isb.ivr.flow.webservices.jaxbbeans.binding.Op1Response;
import org.springframework.webflow.execution.Event;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;

public class BindingWebService extends WebServiceGatewaySupport {
	
	public Event op1(Op1 op1) throws Exception, MarshalException{
		
		Op1Response response;
		
		try{
			response = (Op1Response)getWebServiceTemplate().marshalSendAndReceive(op1);
		} catch (SoapFaultClientException e){
			
			if ("01".equalsIgnoreCase(e.getSoapFault().getFaultCode().toString())){
				return new Event(this, "ExNoAccount");
			}
			throw new Exception("Soap Fault Exception capturada ");
			
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("Error recogiendo resultado del web service" );
		}
					
		return new Event(this, "OK");
	}


}
