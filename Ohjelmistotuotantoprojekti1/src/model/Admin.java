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
public class Admin extends User{
      
    
    public Admin(){     
    }
    
    public Admin(int id, String username, String password, int priviledges, String email){
        super(id, username, password, priviledges, email);
    }
    
}

