/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Employee;
import java.util.List;

/**
 *
 * @author Paula
 */
public interface EmployeeDAO_IF {

    /**
     *
     * @param userID
     * @return
     */
    public abstract Employee readEmployee(int userID);

    /**
     *
     * @return
     */
    public abstract List<Employee> readEmployees();
    
}
