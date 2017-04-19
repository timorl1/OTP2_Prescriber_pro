/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Johanna
 */
public class Localisation {
    
    private Localisation(){}
    
    private static Localisation INSTANCE = null;
    
    private String language;
    private String country;
    private Locale currentLocal;
    private ResourceBundle text;  
    private final List<String> languageList = Arrays.asList("eng", "sve");

    public List<String> getLanguageList() {
        return languageList;
    }
    
    public synchronized static Localisation getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Localisation();
        }
        return INSTANCE;
    }
    public Locale chooseLanguage(String l){
        switch (l) {
            case "eng":
                this.country = "GB";
                this.language = "en";
                break;
            case "sve":
                this.country = "SE";
                this.language = "sv";
                break;
        }
        currentLocal = currentLocal = new Locale(language, country);
        return currentLocal;
    }
    
    
    public ResourceBundle language(){               
        this.text = ResourceBundle.getBundle("MessagesBundle" +"_"+ this.language+"_"+ this.country, currentLocal);
        return text;
    }
    /*
    public ResourceBundle languageENG(){
        this.text = ResourceBundle.getBundle("MessagesBundle_en_GB", currentLocal);
        return text;
    } */
 
    
}
