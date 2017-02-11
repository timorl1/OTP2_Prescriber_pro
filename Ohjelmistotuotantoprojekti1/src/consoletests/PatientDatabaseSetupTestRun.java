/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import java.util.ArrayList;
import java.util.Properties;
import main.DatabaseDAO;

/**
 *
 * @author joosiika
 */
public class PatientDatabaseSetupTestRun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Properties PDP = new Properties();
        DatabaseDAO PDD = new DatabaseDAO(PDP, "db.properties");
        Properties DDP = new Properties();
        DatabaseDAO DDD = new DatabaseDAO(DDP, "db.properties");
        PDP = PDD.readDBProperties();
        DDP = DDD.readDBProperties();
        String fieldName;
        
        if (PDP.isEmpty()) {
            System.out.println("Tietokannan URL: ");
            PDP.setProperty("url", Reader.readLine());
            System.out.println("Tietokannan käyttäjätunnus: ");
            PDP.setProperty("username", Reader.readLine());
            System.out.println("Tietokannan salasana: ");
            PDP.setProperty("password", Reader.readLine());
            PDD.writeDBProperties("Patient DB Settings");
            //Luo tietokantayhteys ja hae kenttien nimet
            ArrayList<String>fieldNames = PDD.getDatabases();
            
            System.out.println("Valitse tietokanta: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("db", fieldNames.get(Reader.readInt()-1));
            
            fieldNames = PDD.getTables();
            
            System.out.println("Valitse tietotaulu: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("table", fieldNames.get(Reader.readInt()-1));
            
            fieldNames = PDD.getDBFieldNames();
            
            System.out.println("Valitse sosiaaliturvatunnuksen kentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("SSN", fieldNames.get(Reader.readInt()-1));
            
            System.out.println("Valitse etunimikentän kentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("firstName", fieldNames.get(Reader.readInt()-1));
            
            System.out.println("Valitse sukunimikentän kentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("lastName", fieldNames.get(Reader.readInt()-1));
            
            System.out.println("Valitse sukupuolikentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("gender", fieldNames.get(Reader.readInt()-1));
            
            System.out.println("Valitse painokentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("weight", fieldNames.get(Reader.readInt()-1));
            
            System.out.println("Valitse pituuskentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("height", fieldNames.get(Reader.readInt()-1));
            
            PDD.writeDBProperties("Patient DB Settings");
        }
        if (DDP.get("diagnoseID") == null) {
            System.out.println("Tietokannan URL: ");
            DDP.setProperty("url", Reader.readLine());
            System.out.println("Tietokannan käyttäjätunnus: ");
            DDP.setProperty("username", Reader.readLine());
            System.out.println("Tietokannan salasana: ");
            DDP.setProperty("password", Reader.readLine());
            DDD.writeDBProperties("Diagnose DB Settings");
            //Luo tietokantayhteys ja hae kenttien nimet
            ArrayList<String>fieldNames = DDD.getDatabases();
            
            System.out.println("Valitse tietokanta: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            DDP.setProperty("db", fieldNames.get(Reader.readInt()-1));
            
            fieldNames = DDD.getTables();
            
            System.out.println("Valitse tietotaulu: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            DDP.setProperty("table", fieldNames.get(Reader.readInt()-1));
            
            fieldNames = DDD.getDBFieldNames();
            
            System.out.println("Valitse tunniste kentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            DDP.setProperty("diagnoseID", fieldNames.get(Reader.readInt()-1));
            
            System.out.println("Valitse sairaustunnistekentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            DDP.setProperty("diseaseID", fieldNames.get(Reader.readInt()-1));
            
            System.out.println("Valitse potilastunnistekentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            DDP.setProperty("patientID", fieldNames.get(Reader.readInt()-1));
            
            System.out.println("Valitse lääkäritunnistekentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            DDP.setProperty("doctorID", fieldNames.get(Reader.readInt()-1));
            
            DDD.writeDBProperties("Diagnose DB Settings");
        }
        else {
            System.out.println(PDP);
            System.out.println(DDP);
        }
    }
    
}
