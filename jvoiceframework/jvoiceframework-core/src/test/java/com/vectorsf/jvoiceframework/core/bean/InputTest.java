package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InputTest {
    
    static boolean  BARGEIN = true;
    static boolean  FLUSH = true;
    static boolean  CATCH_HANGUP = true;
    static String SRC_TEXT = "testAudio";
    static String WORDING_TEXT = "it is a wording test";
    static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
    
    private Grammar grammar1 = new Grammar();
    private Grammar grammar2 = new Grammar();
    private Grammar grammar3 = new Grammar();
    
    private AudioItem mainAudioItem1 = new AudioItem();
    private AudioItem mainAudioItem2 = new AudioItem();
    
    private AudioItem noInputAudioItem1 = new AudioItem();
    private AudioItem noInputAudioItem2 = new AudioItem();
    
    private AudioItem noMatchAudioItem1 = new AudioItem();
    private AudioItem noMatchAudioItem2 = new AudioItem();
    
    @Test
    public void testInputComponent(){
        
        //Given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(SCAN_BASE_PACKAGE);
        context.refresh();
        
        //When
        Input input = (Input)context.getBean(Input.class);
        
        input.setName("Prueba");
        assertEquals("Checking input name", input.getName(), "Prueba");
        
        input.setBargein(false);
        assertFalse("Checking input bargein to false", input.isBargein());
        input.setBargein(true);
        assertTrue("Checking input bargein to true", input.isBargein());
        
        input.setMaxAttempts(5);
        assertEquals("Checking input max attempts", input.getMaxAttempts(), 5);
        
        input.setMaxNoInput(2);
        assertEquals("Checking input max no input", input.getMaxNoInput(), 2);
        
        input.setMaxNoMatch(3);
        assertEquals("Checking input max no match", input.getMaxNoMatch(), 3);
        
        Map<String, String> properties = input.getProperties();
        properties.put("uno", "valor de uno");
        properties.put("dos", "valor de dos");
        assertEquals("Checking input properties size ", input.getProperties().size(), 2);
        assertNotNull("Checking input existing property", input.getProperties().get("uno"));        
        assertNull("Checking input not existing property", input.getProperties().get("tres"));
        
        //grammars
        List<Grammar> grammars = input.getGrammars();
        grammar1.setSrc("builtin:touchtone");
        grammar1.setType("digits");
        grammars.add(grammar1);
        grammar2.setSrc("builtin:otra");
        grammar2.setType("letters");
        grammars.add(grammar2);
        grammar3.setSrc("/grammars/");
        grammar3.setSrc("cities.bnf");
        grammars.add(grammar3);
        assertEquals("Checking input grammar list size", input.getGrammars().size(), 3);
        assertEquals("Checking input grammar src", input.getGrammars().get(0).getSrc(), "builtin:touchtone");
        assertEquals("Checking input grammar type", input.getGrammars().get(0).getType(), "digits");
        
        //audios
        mainAudioItem1.setSrc("MAIN-AUDIO-01");
        mainAudioItem1.setWording("Locución de prueba 01");
        mainAudioItem1.setCond("attempts==1");
        input.getMainAudios().add(mainAudioItem1);
        mainAudioItem2.setSrc("MAIN-AUDIO-02");
        mainAudioItem2.setWording("Locución de prueba 02");
        mainAudioItem2.setCond("attempts==2");
        input.getMainAudios().add(mainAudioItem2);
        assertEquals("Checking input main audios list size", input.getMainAudios().size(), 2);
        assertEquals("Checking input main audio src", input.getMainAudios().get(0).getSrc(), "MAIN-AUDIO-01");
        assertEquals("Checking input main audio type", input.getMainAudios().get(0).getWording(), "Locución de prueba 01");
        assertEquals("Checking input main audio cond", input.getMainAudios().get(0).getCond(), "attempts==1");

        noInputAudioItem1.setSrc("NO-INPUT-AUDIO-01");
        noInputAudioItem1.setWording("Locución de prueba 01");
        noInputAudioItem1.setCond("attempts==1");
        input.getNoInputAudios().add(noInputAudioItem1);
        noInputAudioItem2.setSrc("NO-INPUT-AUDIO-02");
        noInputAudioItem2.setWording("Locución de prueba 02");
        noInputAudioItem2.setCond("attempts==2");
        input.getNoInputAudios().add(noInputAudioItem2);
        assertEquals("Checking input no input audios list size", input.getNoInputAudios().size(), 2);
        assertEquals("Checking input no input audio src", input.getNoInputAudios().get(0).getSrc(), "NO-INPUT-AUDIO-01");
        assertEquals("Checking input no input audio type", input.getNoInputAudios().get(0).getWording(), "Locución de prueba 01");
        assertEquals("Checking input no input audio cond", input.getNoInputAudios().get(0).getCond(), "attempts==1");

        noMatchAudioItem1.setSrc("NO-INPUT-AUDIO-01");
        noMatchAudioItem1.setWording("Locución de prueba 01");
        noMatchAudioItem1.setCond("attempts==1");
        input.getNoMatchAudios().add(noMatchAudioItem1);
        noMatchAudioItem2.setSrc("NO-INPUT-AUDIO-02");
        noMatchAudioItem2.setWording("Locución de prueba 02");
        noMatchAudioItem2.setCond("attempts==2");
        input.getNoMatchAudios().add(noMatchAudioItem2);
        assertEquals("Checking input no match audios list size", input.getNoMatchAudios().size(), 2);
        assertEquals("Checking input no match audio src", input.getNoMatchAudios().get(0).getSrc(), "NO-INPUT-AUDIO-01");
        assertEquals("Checking input no match audio type", input.getNoMatchAudios().get(0).getWording(), "Locución de prueba 01");
        assertEquals("Checking input no match audio cond", input.getNoMatchAudios().get(0).getCond(), "attempts==1");

        input.getEvents().add("Evento1");
        input.getEvents().add("Evento2");
        input.getEvents().add("Evento3");
        input.getEvents().add("Evento4");
        assertEquals("Checking input events size", input.getEvents().size(), 4);
        assertEquals("Checking input event 2", input.getEvents().get(1), "Evento2");
        
        //Finally
        context.close();
        
    }
}