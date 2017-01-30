/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author joosiika
 */
public class Patient {
    
    private final String SSN;
    private String firstName;
    private String lastName;
    private String sex;
    private double weight;
    private double height;
    private String[] diagnoses;
    private String[] allergies;

    public Patient(String SSN, String firstName, String lastName, String sex, double weight, double height, String[] diagnoses, String[] allergies) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.diagnoses = diagnoses;
        this.allergies = allergies;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String[] getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String[] diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }
    
}
