package com.vectorsf.jvoiceframework.testapp.service.promotion;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service("promotionProvider")
public class PromotionProvider {
	
	private static final String PROMOTION_WORDING = "Contrate ahora nuestras nuevas tarjetas blah blah blah ...";
	
	public Promotion retrieve() {
		Promotion promotion = null;
		promotion = new Promotion(); // No se lo pedimos a Spring esto es un Mock!
		promotion.setWording(PROMOTION_WORDING);
		Calendar expiration = Calendar.getInstance();
		expiration.set(2050, 1, 1, 0, 0);
		promotion.setExpiration(expiration);
		return promotion;
	}
}
