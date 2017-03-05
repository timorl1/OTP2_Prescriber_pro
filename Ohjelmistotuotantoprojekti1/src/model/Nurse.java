/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Paula
 */
public class Nurse extends User {
    
    private String SNN;
    private String firstName;
    private String lastName;
    
    public Nurse(){
        
    }    
    
    public Nurse (int id, String username,String password, String firstname, String lastname,
            String email, int priviledges){
        super(id, username, password, priviledges, email);
        this.firstName = firstname;
        this.lastName = lastname;
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
}