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
public class PatientDBParameter {
    private String url;
    private String username;
    private String password;
    private String patientTable;
    private String SSNField;
    private String firstNameField;
    private String lastNameField;
    private String genderField;
    private String weightField;
    private String heightField;
    private String allergiesField;
    
    public PatientDBParameter() {
    }

    public PatientDBParameter(String url, String username, String password, String patientTable, String SSNField, String firstNameField, String lastNameField, String genderField, String weightField, String heightField, String allergiesField) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.patientTable = patientTable;
        this.SSNField = SSNField;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.genderField = genderField;
        this.weightField = weightField;
        this.heightField = heightField;
        this.allergiesField = allergiesField;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPatientTable() {
        return patientTable;
    }

    public void setPatientTable(String patientTable) {
        this.patientTable = patientTable;
    }

    public String getSSNField() {
        return SSNField;
    }

    public void setSSNField(String SSNField) {
        this.SSNField = SSNField;
    }

    public String getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(String firstNameField) {
        this.firstNameField = firstNameField;
    }

    public String getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(String lastNameField) {
        this.lastNameField = lastNameField;
    }

    public String getGenderField() {
        return genderField;
    }

    public void setGenderField(String genderField) {
        this.genderField = genderField;
    }

    public String getWeightField() {
        return weightField;
    }

    public void setWeightField(String weightField) {
        this.weightField = weightField;
    }

    public String getHeightField() {
        return heightField;
    }

    public void setHeightField(String heightField) {
        this.heightField = heightField;
    }

    public String getAllergiesField() {
        return allergiesField;
    }

    public void setAllergiesField(String allergiesField) {
        this.allergiesField = allergiesField;
    }
}
