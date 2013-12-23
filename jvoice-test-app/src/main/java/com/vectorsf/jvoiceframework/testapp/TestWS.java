package com.vectorsf.jvoiceframework.testapp;

import java.util.Locale;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;









import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;
import com.vectorsf.jvoiceframework.ws.test.HelloRequest;
import com.vectorsf.jvoiceframework.ws.test.HelloService;
import com.vectorsf.jvoiceframework.ws.test.ObjectFactory;


@Controller
public class TestWS {
	
	@Autowired
	private WebServiceProvider webServiceProvider;
	
	@RequestMapping(value = "/testWS", method = RequestMethod.GET)
	public String testWS(Locale locale, Model model) {
		try {
			
			HelloService helloService = webServiceProvider.getClient(HelloService.class, "OtherHelloServiceSOAP");

			ObjectFactory of = new ObjectFactory();
			HelloRequest hr = of.createHelloRequest();
			hr.setWho("YO!");
			
			
			
			model.addAttribute("result", helloService.sayHello(hr));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "testWS";
	}

}
