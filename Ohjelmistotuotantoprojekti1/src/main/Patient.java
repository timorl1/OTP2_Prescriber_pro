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
    
    private String SSN;
    private String firstName;
    private String lastName;
    private String gender;
    private double weight;
    private double height;
    private String[] allergies;

    public Patient(){};
    public Patient(String SSN, String firstName, String lastName, String gender, double weight, double height) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
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

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }
    
}
