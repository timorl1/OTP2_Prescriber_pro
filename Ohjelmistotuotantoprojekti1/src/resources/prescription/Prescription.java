package resources.prescription;

import java.io.Serializable;
import resources.patient.Patient;
import javax.persistence.*;
import java.util.Date;
import resources.user.User_IF;
import resources.diagnose.Diagnose;
import resources.drug.Drug;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity(name="prescription")
@Table(name="prescription")
public class Prescription implements Serializable, Cloneable {
    
    private static final long serialVersionUID = 1L;
    
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
        if (this.patient != null) {
            this.patientID = this.patient.getSSN();
        }
        else {
            this.patientID = null;
        }
    }

    public User_IF getDoctor() {
        return user;
    }

    public void setDoctor(User_IF user) {
        this.user = user;
        if (this.user != null) {
            this.doctorID = this.user.getUserID();
            this.username = this.user.getUsername();
        }
        else {
            this.doctorID = 0;
            this.username = null;
        }
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
        if (this.drug != null) {
            this.drugID = drug.getSN();
        }
        else {
            this.drugID = 0;
        }
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
        if (this.diagnose != null) {
            this.diagnoseID = diagnose.getId();
        }
        else {
            this.diagnoseID = 0;
        }
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
        return this.id + ": " + this.patient + ", " + this.diagnose + ", " + this.creationDate;
    }
    
    @Override
    public Prescription clone() {
        Prescription prescription = new Prescription();
        prescription.id = this.id;
        prescription.creationDate = this.creationDate;
        prescription.doctorID = this.doctorID;
        prescription.user = this.user;
        prescription.username = this.username;
        prescription.patientID = this.patientID;
        prescription.patient = this.patient;
        prescription.diagnoseID = this.diagnoseID;
        prescription.diagnose = this.diagnose;
        prescription.drugID = this.drugID;
        prescription.drug = this.drug;
        prescription.dose = this.dose;
        prescription.timesADay = this.timesADay;
        prescription.info = this.info;
        prescription.startDate = this.startDate;
        prescription.endDate = this.endDate;
        return prescription;
    }

}
