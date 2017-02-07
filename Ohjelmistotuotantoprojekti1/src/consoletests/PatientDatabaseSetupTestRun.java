/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import main.PatientDBParameter;
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
        PatientDBParameter PDP = new PatientDBParameter();
        String fieldName;
        
        try {
            PDP = PDD.readPatientDBParameter();
        }
        catch (Exception e) {
            System.out.println("ei löydy");
        }
        
        if (PDP.getUrl() == null) {
            System.out.println("Tietokannan URL: ");
            PDP.setUrl(Reader.readLine());
            System.out.println("Tietokannan käyttäjätunnus: ");
            PDP.setUsername(Reader.readLine());
            System.out.println("Tietokannan salasana: ");
            PDP.setPassword(Reader.readLine());
            System.out.println("Tietotaulun nimi: ");
            PDP.setPatientTable(Reader.readLine());
            //Luo tietokantayhteys ja hae kenttien nimet
            String[] fieldNames = PDD.getDBFieldNames(PDD.createConnection(PDP.getUrl(), PDP.getUsername(), PDP.getPassword()), PDP);
            System.out.println("Valitse sosiaaliturvatunnuksen kentän nimi: ");
            for (int i = 0; i < fieldNames.length; i++) {
                System.out.println(i+1 + ". " + fieldNames[i]);
            }
            fieldName = fieldNames[Reader.readInt()-1];
            PDP.setSSNField(fieldName);
            System.out.println("Valitse etunimikentän nimi: ");
            for (int i = 0; i < fieldNames.length; i++) {
                System.out.println(i+1 + ". " + fieldNames[i]);
            }
            fieldName = fieldNames[Reader.readInt()-1];
            PDP.setFirstNameField(fieldName);
            System.out.println("Valitse sukunimikentän nimi: ");
            for (int i = 0; i < fieldNames.length; i++) {
                System.out.println(i+1 + ". " + fieldNames[i]);
            }
            fieldName = fieldNames[Reader.readInt()-1];
            PDP.setLastNameField(fieldName);
            System.out.println("Valitse sukupuolikentän nimi: ");
            for (int i = 0; i < fieldNames.length; i++) {
                System.out.println(i+1 + ". " + fieldNames[i]);
            }
            fieldName = fieldNames[Reader.readInt()-1];
            PDP.setGenderField(fieldName);
            System.out.println("Valitse painokentän nimi: ");
            for (int i = 0; i < fieldNames.length; i++) {
                System.out.println(i+1 + ". " + fieldNames[i]);
            }
            fieldName = fieldNames[Reader.readInt()-1];
            PDP.setWeightField(fieldName);
            System.out.println("Valitse pituuskentän nimi: ");
            for (int i = 0; i < fieldNames.length; i++) {
                System.out.println(i+1 + ". " + fieldNames[i]);
            }
            fieldName = fieldNames[Reader.readInt()-1];
            PDP.setHeightField(fieldName);
            System.out.println("Valitse allergiakentän nimi: ");
            for (int i = 0; i < fieldNames.length; i++) {
                System.out.println(i+1 + ". " + fieldNames[i]);
            }
            fieldName = fieldNames[Reader.readInt()-1];
            PDP.setAllergiesField(fieldName);
        }
        else {
            System.out.println(PDP);
        }
    }
    
}
