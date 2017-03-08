package model;

import model.Patient;
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
    private User_IF user;
    @Column(name="drugID")
    private int drugID;
    @Transient
    private Drug drug;
    @Column(name="diagnoseID")
    private int diagnoseID;
    @Transient
    private Diagnose diagnose;
    @Column(name="dose")
    private double dose;
    @Column(name="timesADay")
    private int timesADay;
    @Column(name="info")
    private String info;
    @Column(name="startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name="endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
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
        this.setPatientID(this.patient.getSSN());
    }

    public User_IF getDoctor() {
        return user;
    }

    public void setDoctor(User_IF user) {
        this.user = user;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }


    public int getDrugID() {
        return drugID;
    }

    public void setDrugID(int drugID) {
        this.drugID = drugID;
    }

    public int getDiagnoseID() {
        return diagnoseID;
    }

    public void setDiagnoseID(int diagnoseID) {
        this.diagnoseID = diagnoseID;
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

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
    
    @Override
    public String toString() {
        return this.id + ", " + this.patient + ", " + this.diagnose + ", " + this.creationDate;
    }

}
