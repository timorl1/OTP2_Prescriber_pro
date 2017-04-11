package resources.diagnose;

import resources.user.User_IF;
import resources.patient.Patient;
import java.sql.Timestamp;
import resources.disease.Disease;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class Diagnose {
    private int id;
    private int diseaseID;
    private Disease disease;
    private String epicrisis;
    private Timestamp creationDate;
    private Timestamp resolutionDate;
    private String patientId;
    private Patient patient;
    private int doctorId;
    private User_IF doctor;
    
    
    public Diagnose() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }
    
    public String getEpicrisis() {
        return epicrisis;
    }

    public void setEpicrisis(String epicrisis) {
        this.epicrisis = epicrisis;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Timestamp resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public User_IF getDoctor() {
        return doctor;
    }

    public void setDoctor(User_IF doctor) {
        this.doctor = doctor;
    }
    
    @Override
    public String toString() {
        return this.id + ", " + this.disease + ", " + this.creationDate;
    }
    
}
