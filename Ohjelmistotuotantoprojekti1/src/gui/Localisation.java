/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Johanna
 */
public class Localisation {
    
    private Localisation(){}
    //private String language;
    //private String country; 
    
    private static Localisation INSTANCE = null;
    
    private String language;
    private String country;
    private Locale currentLocal;
    private ResourceBundle text;  
    
    public static Localisation getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Localisation();
        }
        return INSTANCE;
    }
    public Locale chooseLanguage(String l, String c){
        this.country = c;
        this.language = l;
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
