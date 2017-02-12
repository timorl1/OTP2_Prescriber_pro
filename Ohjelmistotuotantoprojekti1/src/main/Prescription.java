/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Timestamp;

/**
 *
 * @author Johanna
 */
public class Prescription {
    
    private int id;
    private Patient patient;
    private Doctor doctor;
    private Drug drug;
    private Diagnose diagnose;  
    private double dose;
    private int timesADay;
    private String info;
    private String startDate;
    private String endDate;
    private Timestamp creationDate;

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
    }

    public Doctor getDoc() {
        return doctor;
    }

    public void setDoc(Doctor doc) {
        this.doctor = doc;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
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
    
    public Timestamp getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

}
