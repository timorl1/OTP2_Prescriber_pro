/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Employee;
import java.util.List;

/**
 * Interface that defines methods for getting info on employees from database
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface EmployeeDAO_IF {

    /**
     * Gets employee by userID from database
     * @param userID user's id number
     * @return employee object
     */
    public abstract Employee readEmployee(int userID);

    /**
     * Gets a list of all the employees from database
     * @return list of employee objects
     */
    public abstract List<Employee> readEmployees();
    
}
