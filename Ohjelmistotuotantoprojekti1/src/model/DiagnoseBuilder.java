package model;

import dao.DiseaseDAO;
import dao.DiseaseDAO_IF;
import dao.PatientDAO;
import dao.PatientDAO_IF;

/**
 *
 * @author joosiika
 */
public class DiagnoseBuilder implements DiagnoseBuilder_IF {
    private final PatientDAO_IF patientdb;
    private final DiseaseDAO_IF diseasedb;
    private final PatientBuilder_IF pb;
    
    private Diagnose diagnose;

    public DiagnoseBuilder() {
        this.patientdb = new PatientDAO();
        this.diseasedb = new DiseaseDAO();
        this.pb = new PatientBuilder();
    }

    @Override
    public Patient getDiagnosePatient(Diagnose diagnose) {
        return this.pb.buildPatient(this.patientdb.readPatient(diagnose.getPatientId()));
    }

    @Override
    public User_IF getDiagnoseDoctor(Diagnose diagnose) {
        User_IF doctor = new User();
        doctor.setFirstName("Place");
        doctor.setLastName("Holder");
        return doctor;
    }
    
    @Override
    public Disease getDiagnoseDisease(Diagnose diagnose) {
        return this.diseasedb.getDisease(diagnose.getDiseaseID());
    }

    @Override
    public Diagnose buildDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
        this.diagnose.setPatient(this.getDiagnosePatient(this.diagnose));
        this.diagnose.setDoctor(this.getDiagnoseDoctor(this.diagnose));
        this.diagnose.setDisease(this.getDiagnoseDisease(this.diagnose));
        return this.diagnose;
    }
}
