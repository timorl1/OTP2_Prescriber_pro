package main;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Johanna
 */
@Entity(name="prescription")
@Table(name="prescription")
public class Prescription {
    @Id
    @Column(name="prescriptionID")
    private int id;
    @Column(name="patientID")
    private String patientID;
    @Transient
    private Patient patient;
    @Column(name="doctorID")
    private int doctorID;
    @Transient
    private Doctor doctor;
    @Column(name="drugID")
    private int drugID;
    @Transient
    private Drug drug;
    @Column(name="diagnoseID")
    private int diagnoseID;
    @Transient
    private Diagnose diagnose;
    @Column(name="dose")
    private String dose;
    @Column(name="timesADay")
    private int timesADay;
    @Column(name="info")
    private String info;
    @Column(name="startDate")
    private String startDate;
    @Column(name="endDate")
    private String endDate;
    @Column(name="creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name="username")
    private String username;

    public Prescription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.patientID = patient.getSSN();
    }

    public Doctor getDoc() {
        return doctor;
    }

    public void setDoc(Doctor doc) {
        this.doctor = doc;
        this.doctorID = doc.getId();
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
        this.drugID = drug.getSN();
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
        this.diagnoseID = diagnose.getId();
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getTimesADay() {
        return timesADay;
    }

    public void setTimesADay(int timesADay) {
        this.timesADay = timesADay;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
