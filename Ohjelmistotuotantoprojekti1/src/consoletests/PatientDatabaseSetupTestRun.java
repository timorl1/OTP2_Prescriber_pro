/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import java.util.ArrayList;
import java.util.Properties;
import main.PatientDatabaseDAO;

/**
 *
 * @author joosiika
 */
public class PatientDatabaseSetupTestRun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PatientDatabaseDAO PDD = new PatientDatabaseDAO();
        Properties PDP = PDD.readPatientDBProperties();
        String fieldName;
        
        if (PDP.isEmpty()) {
            System.out.println("Tietokannan URL: ");
            PDP.setProperty("url", Reader.readLine());
            System.out.println("Tietokannan käyttäjätunnus: ");
            PDP.setProperty("username", Reader.readLine());
            System.out.println("Tietokannan salasana: ");
            PDP.setProperty("password", Reader.readLine());
            System.out.println("Tietotaulun nimi: ");
            PDP.setProperty("tableName", Reader.readLine());
            PDD.writePatientDBProperties(PDP);
            //Luo tietokantayhteys ja hae kenttien nimet
            ArrayList<String>fieldNames = PDD.getDBFieldNames(PDD.createConnection(PDP.getProperty("url"), PDP.getProperty("username"), PDP.getProperty("password")));
            
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
            
            System.out.println("Valitse allergiakentän nimi: ");
            for (int i = 0; i < fieldNames.size(); i++) {
                System.out.println(i+1 + ". " + fieldNames.get(i));
            }
            PDP.setProperty("allergies", fieldNames.get(Reader.readInt()-1));
            
            PDD.writePatientDBProperties(PDP);
        }
        else {
            System.out.println(PDP);
        }
    }
    
}
