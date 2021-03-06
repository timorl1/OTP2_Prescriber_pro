package tools;

import resources.diagnose.Diagnose;
import resources.prescription.Prescription;
import resources.user.User_IF;
import resources.patient.Patient;
import resources.disease.DiseaseDAO;
import resources.disease.DiseaseDAO_IF;
import resources.drug.DrugDAO;
import resources.drug.DrugDAO_IF;
import resources.patient.PatientDAO;
import resources.patient.PatientDAO_IF;
import resources.prescription.PrescriptionDAO;
import resources.prescription.PrescriptionDAO_IF;
import java.util.List;
import java.util.Map;
import resources.diagnose.DiagnoseDAO;
import resources.diagnose.DiagnoseDAO_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DependencyBuilder implements DependencyBuilder_IF {
    
    private DiseaseDAO_IF diseaseDAO;
    private PatientDAO_IF patientDAO;
    private DiagnoseDAO_IF diagnoseDAO;
    private PrescriptionDAO_IF prescriptionDAO;
    private DrugDAO_IF drugDAO;
    
    private Patient patient;
    private User_IF doctor;
    private Diagnose diagnose;
    private Prescription prescription;
    private Map<String, Patient> patients;
    private Map<Integer, User_IF> users;
    
    public DependencyBuilder(Map<String, Patient> patients, Map<Integer, User_IF> users) {
        this.diseaseDAO = new DiseaseDAO();
        this.patientDAO = new PatientDAO();
        this.diagnoseDAO = new DiagnoseDAO();
        this.drugDAO = new DrugDAO();
        this.prescriptionDAO = new PrescriptionDAO();
        this.patients = patients;
        this.users = users;
    }
    
    @Override
    public Patient buildPatient(Patient patient) {
        this.patient = patient;
        List<Diagnose> diagnoses = this.diagnoseDAO.readPatientDiagnoses(this.patient);
        diagnoses.forEach(d -> this.buildDiagnose(d));
        this.patient.setDiagnoses(diagnoses);
        List<Prescription> prescriptions = this.prescriptionDAO.getPrescriptionsByPatient(this.patient);
        prescriptions.forEach(pres -> this.buildPrescription(pres));
        this.patient.setPrescriptions(prescriptions);
        return this.patient;
    }
    
    @Override
    public Diagnose buildDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
        if (this.diagnose.getPatient() == null) {
            this.diagnose.setPatient(this.patients.get(this.diagnose.getPatientId()));
            this.diagnose.setDoctor(this.users.get(this.diagnose.getDoctorId()));
            this.diagnose.setDisease(this.diseaseDAO.getDisease(this.diagnose.getDiseaseID()));
        }
        return this.diagnose;
    }
    
    @Override
    public Prescription buildPrescription(Prescription prescription) {
        this.prescription = prescription;
        if (this.prescription.getPatient() == null) {
            this.prescription.setPatient(this.patients.get(this.prescription.getPatientID()));
            this.prescription.setDoctor(this.users.get(this.prescription.getDoctorID()));
            this.prescription.setDrug(this.drugDAO.readDrug(this.prescription.getDrugID()));
            this.prescription.setDiagnose(this.buildDiagnose(this.diagnoseDAO.readDiagnose(this.prescription.getDiagnoseID())));
        }
        return this.prescription;
    }

    @Override
    public List<Prescription> buildDoctorPrescriptions(User_IF doctor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
