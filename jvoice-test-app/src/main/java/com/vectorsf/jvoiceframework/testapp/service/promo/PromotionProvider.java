package com.vectorsf.jvoiceframework.testapp.service.promo;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service("promotionProvider")
public class PromotionProvider {
	
	private static final String PROMOTION_WORDING = "Esta promoción es falsa, pero ¿Acaso no lo son todas?";
	
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
