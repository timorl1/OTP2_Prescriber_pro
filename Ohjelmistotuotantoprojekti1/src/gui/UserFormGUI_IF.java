/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import model.User_IF;
import model.Employee;

/**
 *
 * @author Paula
 */
public interface UserFormGUI_IF {
    public abstract Button getCancelButton();
    public abstract Button getSaveButton();
    public abstract Text getEmployeeField();
    public abstract Employee getEmployee();
    public abstract User_IF getUser();
    
}
