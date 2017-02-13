package consoletests;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.ApplicationDAO;
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
        char select;
        ApplicationDAO appdb = new ApplicationDAO();
        DrugDAO drugdb = new DrugDAO();
        PatientDAO patientdb = new PatientDAO();
        Prescription prescription = new Prescription();
        Patient[] patients = null;
        String fieldName;
        
        do {
            System.out.println("\n\t\t\t1. Lisää resepti.");
            System.out.println("\t\t\t2. Näytä potilaan reseptit.");
            System.out.println("\t\t\t3. Muokkaa reseptiä.");
            System.out.println("\t\t\t4. Poista resepti.");
            System.out.println("\t\t\t5. Lopeta.");
            System.out.print("\n\n"); // tehdään tyhjiä rivejä
            select = Reader.readChar();
            switch(select) {
                case'1':
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
                        System.out.println(prescription.getPatient().getFirstName());


                        System.out.println("Valitse diagnoosi: ");
                        patientdb = new PatientDAO();
                        int i = 1;
                        try {
                            List<Diagnose> diagnoses = patientdb.getPatientDiagnoses(prescription.getPatient()).getCollection();
                            for (Diagnose diagnose : diagnoses) {
                                System.out.println(i + ". " + diagnose.getId());
                                i++;
                            }
                            prescription.setDiagnose(diagnoses.get(Reader.readInt()-1));
                        } catch (SQLException ex) {
                            Logger.getLogger(PrescriptionCRUDTestRun.class.getName()).log(Level.SEVERE, null, ex);
                        }

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
                        prescription.setDose(Reader.readLine());

                        System.out.println("Montako kertaa päivässä: ");
                        prescription.setTimesADay(Reader.readInt());

                        System.out.println("Anna ohjeet: ");
                        prescription.setInfo(Reader.readLine());

                        System.out.println("Anna aloituspäivämäärä: ");
                        prescription.setStartDate(Reader.readLine());

                        System.out.println("Anna lopetuspäivämäärä: ");
                        prescription.setEndDate(Reader.readLine());
                        
                        prescription.setUsername("admin");

                        appdb.createPrescription(prescription);
                    }
                    break;
                case'2':
                    Patient patient = null;
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
                        patient = patients[Reader.readInt()-1];
                        System.out.println(patient.getFirstName());
                    }
                    System.out.println("Potilaan reseptit: ");
                        patientdb = new PatientDAO();
                        int i = 1;
                        List<Prescription> prescriptions = appdb.getPrescriptionsByPatient(patient).getCollection();
                        for (Prescription p : prescriptions) {
                            System.out.println(i + ". " + p.getId());
                            System.out.println("  " + p.getPatient());
                            System.out.println("  " + p.getDoc());
                            System.out.println("  " + p.getDiagnose());
                            System.out.println("  " + p.getDose());
                            System.out.println("  " + p.getTimesADay());
                            System.out.println("  " + p.getInfo());
                            i++;
                        }
                    char x = Reader.readChar();
                    break;
                case'3':
                    System.out.println("Valitse muokattava resepti: ");
                        i = 1;
                    prescriptions = appdb.readPrescriptions().getCollection();
                    for (Prescription p : prescriptions) {
                        System.out.println(i + ". " + p.getId());
                        System.out.println("  " + p.getPatient());
                        System.out.println("  " + p.getDoc());
                        System.out.println("  " + p.getDiagnose());
                        System.out.println("  " + p.getDose());
                        System.out.println("  " + p.getTimesADay());
                        System.out.println("  " + p.getInfo());
                        i++;
                    }
                    prescription = prescriptions.get(Reader.readInt()-1);
                    
                    System.out.println("Valitse lääke: ");
                    Drugs drugs = drugdb.readDrugs();
                    List<Drug> druglist = drugs.getCollection();
                    i = 1;
                    for (Drug drug : druglist) {
                        System.out.println(i + ". " + drug.getName());
                        i++;
                    }
                    prescription.setDrug(druglist.get(Reader.readInt() - 1));

                    System.out.println("Anna annostus: ");
                    prescription.setDose(Reader.readLine());

                    System.out.println("Montako kertaa päivässä: ");
                    prescription.setTimesADay(Reader.readInt());

                    System.out.println("Anna ohjeet: ");
                    prescription.setInfo(Reader.readLine());

                    System.out.println("Anna aloituspäivämäärä: ");
                    prescription.setStartDate(Reader.readLine());

                    System.out.println("Anna lopetuspäivämäärä: ");
                    prescription.setEndDate(Reader.readLine());

                    prescription.setUsername("admin");

                    appdb.updatePrescription(prescription);
                case'4':
                    System.out.println("Valitse poistettava resepti: ");
                        i = 1;
                    prescriptions = appdb.readPrescriptions().getCollection();
                    for (Prescription p : prescriptions) {
                        System.out.println(i + ". " + p.getId());
                        System.out.println("  " + p.getPatient());
                        System.out.println("  " + p.getDoc());
                        System.out.println("  " + p.getDiagnose());
                        System.out.println("  " + p.getDose());
                        System.out.println("  " + p.getTimesADay());
                        System.out.println("  " + p.getInfo());
                        i++;
                    }
                    prescription = prescriptions.get(Reader.readInt()-1);
                    appdb.deletePrescription(prescription);
            }
        }
        while(select != 5);
        appdb.finalize();
        
        
    }
    
}
