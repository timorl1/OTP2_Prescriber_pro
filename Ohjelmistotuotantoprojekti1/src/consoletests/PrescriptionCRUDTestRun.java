package consoletests;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.app.PrescriptionDAO;
import resources.client.Diagnose;
import resources.data.Drug;
import resources.data.DrugDAO;
import resources.client.Patient;
import resources.client.PatientDAO;
import java.util.ArrayList;
import resources.app.Prescription;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionCRUDTestRun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char select;
        PrescriptionDAO appdb = new PrescriptionDAO();
        DrugDAO drugdb = new DrugDAO();
        PatientDAO patientdb = new PatientDAO();
        Prescription prescription = new Prescription();
        List<Patient> patients = new ArrayList();
        String fieldName;
        
        do {
            System.out.println("\n\t\t\t1. Lisää resepti.");
            System.out.println("\t\t\t2. Näytä potilaan reseptit.");
            System.out.println("\t\t\t3. Muokkaa reseptiä.");
            System.out.println("\t\t\t4. Poista resepti.");
            System.out.println("\t\t\t5. Lopeta.");
            System.out.print("\n\n"); // tehdään tyhjiä rivejä
            select = Reader.readChar();
            switch (select) {
                case '1':
                    System.out.println("Valitse potilas: ");
                    patients = patientdb.readPatients();
                    if (patients != null) {
                        for (int i = 0; i < patients.size(); i++) {
                            System.out.println(i + 1 + ". " + patients.get(i).getFirstName() + " " + patients.get(i).getLastName());
                        }
                        prescription.setPatient(patients.get(Reader.readInt() - 1));
                        System.out.println(prescription.getPatient().getFirstName());

                        System.out.println("Valitse diagnoosi: ");
                        patientdb = new PatientDAO();
                        int i = 1;
                        List<Diagnose> diagnoses = patientdb.readPatientDiagnoses(prescription.getPatient());
                        for (Diagnose diagnose : diagnoses) {
                            System.out.println(i + ". " + diagnose.getId());
                            i++;
                        }
                        prescription.setDiagnose(diagnoses.get(Reader.readInt() - 1));

                        System.out.println("Valitse lääke: ");
                        List<Drug> drugs = drugdb.readDrugs();
                        i = 1;
                        for (Drug drug : drugs) {
                            System.out.println(i + ". " + drug.getName());
                            i++;
                        }
                        prescription.setDrug(drugs.get(Reader.readInt() - 1));

                        System.out.println("Anna annostus: ");
                        prescription.setDose(Reader.readDouble());

                        System.out.println("Montako kertaa päivässä: ");
                        prescription.setTimesADay(Reader.readInt());

                        System.out.println("Anna ohjeet: ");
                        prescription.setInfo(Reader.readLine());

                        System.out.println("Anna aloituspäivämäärä: ");
                        //prescription.setStartDate(Reader.readLine());

                        System.out.println("Anna lopetuspäivämäärä: ");
                        //prescription.setEndDate(Reader.readLine());

                        prescription.setUsername("admin");

                        appdb.createPrescription(prescription);
                    }
                    break;
                case '2':
                    Patient patient = null;
                    System.out.println("Valitse potilas: ");
                    patients = patientdb.readPatients();
                    if (patients != null) {
                        for (int i = 0; i < patients.size(); i++) {
                            System.out.println(i + 1 + ". " + patients.get(i).getFirstName() + " " + patients.get(i).getLastName());
                        }
                        patient = patients.get(Reader.readInt() - 1);
                        System.out.println(patient.getFirstName());
                    }
                    System.out.println("Potilaan reseptit: ");
                    patientdb = new PatientDAO();
                    int i = 1;
                    List<Prescription> prescriptions = appdb.getPrescriptionsByPatient(patient);
                    for (Prescription p : prescriptions) {
                        System.out.println(i + ". " + p.getId());
                        System.out.println("  " + p.getPatient());
                        System.out.println("  " + p.getDoctor());
                        System.out.println("  " + p.getDiagnose());
                        System.out.println("  " + p.getDose());
                        System.out.println("  " + p.getTimesADay());
                        System.out.println("  " + p.getInfo());
                        i++;
                    }
                    char x = Reader.readChar();
                    break;
                case '3':
                    System.out.println("Valitse muokattava resepti: ");
                    i = 1;
                    prescriptions = appdb.readPrescriptions();
                    for (Prescription p : prescriptions) {
                        System.out.println(i + ". " + p.getId());
                        System.out.println("  " + p.getPatient());
                        System.out.println("  " + p.getDoctor());
                        System.out.println("  " + p.getDiagnose());
                        System.out.println("  " + p.getDose());
                        System.out.println("  " + p.getTimesADay());
                        System.out.println("  " + p.getInfo());
                        i++;
                    }
                    prescription = prescriptions.get(Reader.readInt() - 1);

                    System.out.println("Valitse lääke: ");
                    List<Drug> drugs = drugdb.readDrugs();
                    i = 1;
                    for (Drug drug : drugs) {
                        System.out.println(i + ". " + drug.getName());
                        i++;
                    }
                    prescription.setDrug(drugs.get(Reader.readInt() - 1));

                    System.out.println("Anna annostus: ");
                    prescription.setDose(Reader.readDouble());

                    System.out.println("Montako kertaa päivässä: ");
                    prescription.setTimesADay(Reader.readInt());

                    System.out.println("Anna ohjeet: ");
                    prescription.setInfo(Reader.readLine());

                    System.out.println("Anna aloituspäivämäärä: ");
                    //prescription.setStartDate(Reader.readLine());

                    System.out.println("Anna lopetuspäivämäärä: ");
                    //prescription.setEndDate(Reader.readLine());

                    prescription.setUsername("admin");

                    appdb.updatePrescription(prescription);
                case '4':
                    System.out.println("Valitse poistettava resepti: ");
                    i = 1;
                    prescriptions = appdb.readPrescriptions();
                    for (Prescription p : prescriptions) {
                        System.out.println(i + ". " + p.getId());
                        System.out.println("  " + p.getPatient());
                        System.out.println("  " + p.getDoctor());
                        System.out.println("  " + p.getDiagnose());
                        System.out.println("  " + p.getDose());
                        System.out.println("  " + p.getTimesADay());
                        System.out.println("  " + p.getInfo());
                        i++;
                    }
                    prescription = prescriptions.get(Reader.readInt() - 1);
                    appdb.deletePrescription(prescription);
                case '5':
                    break;
            }
        } while (select != '5');
        System.exit(0);
        
        
    }
    
}
