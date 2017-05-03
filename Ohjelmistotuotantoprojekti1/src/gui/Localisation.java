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
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class Localisation {
    
    private Localisation(){}
    
    private static Localisation INSTANCE = null;
    
    private String language;
    private String country;
    private String selectedLanguage = "English";
    private Locale currentLocal;
    private ResourceBundle text;  
    private final List<String> languageList = Arrays.asList("English", "Swedish");

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
            case "English":
                this.country = "GB";
                this.language = "en";
                break;
            case "Swedish":
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
    
    public String getSelectedLanguage() {
        return selectedLanguage;
    }
     public void setSelectedLanguage(String lan) {
        this.selectedLanguage =lan;
    }
    /*
    public ResourceBundle languageENG(){
        this.text = ResourceBundle.getBundle("MessagesBundle_en_GB", currentLocal);
        return text;
    } */
 
    
}
