
package com.vectorsf.jvoiceframework.ws.test;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "HelloService", targetNamespace = "http://bluedash.net/ws")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloService {


    /**
     * 
     * @param request
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://bluedash.net/ws/sayHello")
    @WebResult(name = "helloResponse", targetNamespace = "http://bluedash.net/ws", partName = "response")
    public String sayHello(
        @WebParam(name = "helloRequest", targetNamespace = "http://bluedash.net/ws", partName = "request")
        HelloRequest request);

}
