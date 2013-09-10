package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EndTest {

    private final static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
    private final static String NAME = "Prueba";

    @Test
    public void testEndComponent(){
        
        //Given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(SCAN_BASE_PACKAGE);
        context.refresh();
        
        //When
        End end = (End)context.getBean(End.class);
        
        assertNotNull("Checking if end is not null", end);
        
        end.setName(NAME);
        assertEquals("Checking end name", end.getName(), NAME);
        
        context.close();
    }
}
