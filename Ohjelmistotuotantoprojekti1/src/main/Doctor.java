/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Johanna
 */
public class Doctor extends User{
    
    private String firstName;
    private String lastName;
    private final String privilege = "CRUD";
    
    
    public Doctor(){}
    
    public Doctor (String username, String firstname, String lastname){
        super(username);
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

    public String getPrivilege(){
        return privilege;
    }
    
    
}
