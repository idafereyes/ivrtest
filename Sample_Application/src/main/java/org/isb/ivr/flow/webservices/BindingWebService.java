package org.isb.ivr.flow.webservices;

import java.rmi.MarshalException;
import java.util.HashMap;
import java.util.Map;

import org.isb.ivr.flow.webservices.jaxbbeans.binding.Op1;
import org.isb.ivr.flow.webservices.jaxbbeans.binding.Op1Response;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;

public class BindingWebService extends WebServiceGatewaySupport {
	
	public Event op1(Op1 op1) throws Exception, MarshalException{
		
		Op1Response response;
				
		try{
			response = (Op1Response)getWebServiceTemplate().marshalSendAndReceive(op1);
		} catch (SoapFaultClientException e){
			
			return new Event(this, e.getSoapFault().getFaultStringOrReason());
			
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("Error recogiendo resultado del web service" );
		}
		
		Map<String,Op1Response> map = new HashMap<String,Op1Response>();
		map.put("response", response);
		AttributeMap attributeMap = new LocalAttributeMap(map);
		
		return new Event(this, "success", attributeMap);
	}


}
