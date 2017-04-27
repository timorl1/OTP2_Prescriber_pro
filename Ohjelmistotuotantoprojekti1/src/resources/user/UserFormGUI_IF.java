/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.user;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import resources.employee.Employee;

/**
 * Interface that defines methods for greating new user
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface UserFormGUI_IF {

    /**
     * Cancels the new user creation
     * @return Cansel if pushed
     */
    public abstract Button getCancelButton();

    /**
     * Saves the new user informations in the database
     * @return Saved user informations
     */
    public abstract Button getSaveButton();

    /**
     * Gets selected employees informations to the field
     * @return employee informations
     */
    public abstract TextField getEmployeeField();

    /**
     * Gets selevted employee informations from the database
     * @return employee informations
     */
    public abstract Employee getEmployee();

    /**
     * Gets selected user information from User_IF
     * @return users information
     */
    public abstract User_IF getUser();
    
}
