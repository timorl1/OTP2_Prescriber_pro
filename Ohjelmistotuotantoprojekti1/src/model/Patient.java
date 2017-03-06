package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joosiika
 */
public class Patient {
    
    private String SSN;
    private String firstName;
    private String lastName;
    private String gender;
    private double weight;
    private double height;
    private List<Diagnose> diagnoses = new ArrayList();
    private List<Prescription> prescriptions = new ArrayList();

    public Patient(){
    }
    
    public void setSSN(String SSN){
        this.SSN = SSN;
    }
    
    public String getSSN() {
        return SSN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
    
    @Override
    public String toString() {
        return this.SSN + ": " + this.lastName + ", " + this.firstName;
    }
    
}
