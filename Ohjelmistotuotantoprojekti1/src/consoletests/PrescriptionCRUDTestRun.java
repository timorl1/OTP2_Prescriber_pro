package consoletests;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.ApplicationDAO;
import main.DatabaseDAO;
import main.Diagnose;
import main.Drug;
import main.DrugDAO;
import main.Drugs;
import main.Patient;
import main.PatientDAO;
import main.Prescription;

/**
 *
 * @author joosiika
 */
public class PrescriptionCRUDTestRun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationDAO appdb = new ApplicationDAO();
        DrugDAO drugdb = new DrugDAO();
        PatientDAO patientdb = new PatientDAO();
        Prescription prescription = new Prescription();
        Patient[] patients = null;
        String fieldName;
        
        System.out.println("Valitse potilas: ");
        try {
            patients = patientdb.readPatients();
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionCRUDTestRun.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (patients != null) {
            for (int i = 0; i < patients.length; i++) {
                System.out.println(i+1 + ". " + patients[i].getFirstName() + " " + patients[i].getLastName());
            }
            prescription.setPatient(patients[Reader.readInt()-1]);
            
            System.out.println("Valitse diagnoosi: ");
            List<Diagnose> diagnoses= prescription.getPatient().getDiagnoses().getCollection();
            int i = 1;
            for (Diagnose diagnose : diagnoses) {
                System.out.println(i + ". " + diagnose.getId());
                i++;
            }
            prescription.setDiagnose(diagnoses.get(Reader.readInt()-1));
            
            System.out.println("Valitse lääke: ");
            Drugs drugs = drugdb.readDrugs();
            List<Drug> druglist = drugs.getCollection();
            i = 1;
            for (Drug drug : druglist) {
                System.out.println(i + ". " + drug.getName());
                i++;
            }
            prescription.setDrug(druglist.get(Reader.readInt()-1));
            
            System.out.println("Anna annostus: ");
            prescription.setDose(Reader.readDouble());
            
            System.out.println("Montako kertaa päivässä: ");
            prescription.setTimesADay(Reader.readInt());
            
            System.out.println("Anna ohjeet: ");
            prescription.setInfo(Reader.readLine());
            
            System.out.println("Anna aloituspäivämäärä: ");
            prescription.setStartDate(Reader.readLine());
            
            System.out.println("Anna lopetuspäivämäärä: ");
            prescription.setEndDate(Reader.readLine());
            
            appdb.createPrescription(prescription);
        }
        
        
        
    }
    
}
