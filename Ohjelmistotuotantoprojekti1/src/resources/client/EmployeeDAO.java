/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.client;

import resources.app.DatabaseDAO;
import resources.client.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class EmployeeDAO implements EmployeeDAO_IF {
    
    private Properties properties = new Properties();
    private DatabaseDAO parameters = new DatabaseDAO(properties, "employeedb.properties");
    Connection connection = null;
    //private Doctor placeholder = new Doctor();

  
    public EmployeeDAO() {
    }

    @Override
    protected void finalize() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get single Employees from database identified by Employee id
    @Override
    public Employee readEmployee(int userID) {
        properties = parameters.readDBProperties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + properties.getProperty("url") + "/" + properties.getProperty("db"), properties.getProperty("username"), properties.getProperty("password"));
        }catch (SQLException e) {
            System.out.println("Connection failed");
            try {
                System.out.println("Trying to connect with Jenkins");
                connection = DriverManager.getConnection("jdbc:mysql://10.114.32.151:3306/sairaaladb", "jenkins",
                "jenkins");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e2) {
            System.out.println("JDBC-driver failed");
        }
        Employee pat = null;
        
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sqlSelect = "SELECT * FROM " + properties.getProperty("table") + " where " + properties.getProperty("userID") + " = ?";
            statement = connection.prepareStatement(sqlSelect);
            statement.setInt(1, userID);
            rs = statement.executeQuery();
            while (rs.next()) {
                int userid = rs.getInt(properties.getProperty("userID"));
                String firstName = rs.getString(properties.getProperty("firstName"));
                String lastName = rs.getString(properties.getProperty("lastName"));
                String email = rs.getString(properties.getProperty("email"));
                String title = rs.getString(properties.getProperty("title"));
                pat = new Employee();
                pat.setUserID(userid);
                pat.setFirstName(firstName);
                pat.setLastName(lastName);
                pat.setEmail(email);
                pat.setTitle(title);
                
            }

        } catch (SQLException e) {
            System.err.println("viesti: " + e.getMessage());
            System.err.println("virhekoodi: " + e.getErrorCode());
            System.err.println("sql-tilakoodi : " + e.getSQLState());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch(Exception e) {
                
            }
        }
        return pat;
    }

    //Gets all employees from database and return it as Employee list
    @Override
    public List<Employee> readEmployees() {
        properties = parameters.readDBProperties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + properties.getProperty("url") + "/" + properties.getProperty("db"), properties.getProperty("username"), properties.getProperty("password"));
        }catch (SQLException e) {
            System.out.println("Connection failed");
            try {
                System.out.println("Trying to connect with Jenkins");
                connection = DriverManager.getConnection("jdbc:mysql://10.114.32.151:3306/sairaaladb", "jenkins",
                "jenkins");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e2) {
            System.out.println("JDBC-driver failed");
        }
        List<Employee> lista = new ArrayList();
        Statement statement = null;
        ResultSet rs = null;
        try {
            String sqlSelect = "SELECT * FROM " + properties.getProperty("table");
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlSelect);
            while (rs.next()) {
                int userid = rs.getInt(properties.getProperty("userID"));
                String firstName = rs.getString(properties.getProperty("firstName"));
                String lastName = rs.getString(properties.getProperty("lastName"));
                String email = rs.getString(properties.getProperty("email"));
                String title = rs.getString(properties.getProperty("title"));
                Employee p = new Employee();
                p.setUserID(userid);
                p.setFirstName(firstName);
                p.setLastName(lastName);
                p.setEmail(email);
                p.setTitle(title);
                lista.add(p);

            }
        } catch (SQLException e) {
            System.err.println("viesti: " + e.getMessage());
            System.err.println("virhekoodi: " + e.getErrorCode());
            System.err.println("sql-tilakoodi : " + e.getSQLState());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch(Exception e) {
                
            }
        }
        return lista;
    }
}