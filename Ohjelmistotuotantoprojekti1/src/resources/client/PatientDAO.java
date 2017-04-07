/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.client;

import resources.app.DatabaseDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import resources.client.Patient;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PatientDAO implements PatientDAO_IF {

    private Properties properties = new Properties();
    private DatabaseDAO parameters = new DatabaseDAO(properties, "db.properties");
    Connection connection = null;

    
    public PatientDAO() {
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

    //Get single patient from database identified by Social security number
    @Override
    public Patient readPatient(String SSN) {
        properties = parameters.readDBProperties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + properties.getProperty("url") + "/" + properties.getProperty("db"), properties.getProperty("username"), properties.getProperty("password"));
        }catch (SQLException e) {
            System.out.println("Connection failed.");
            try {
                System.out.println("Trying to connect with Jenkins");
                connection = DriverManager.getConnection("jdbc:mysql://10.114.32.151:3306/sairaaladb", "jenkins",
                "jenkins");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC-driver failed.");
        }
        
        Patient pat = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sqlSelect = "SELECT * FROM " + properties.getProperty("table") + " where " + properties.getProperty("SSN") + " = ?";
            statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, SSN);
            rs = statement.executeQuery();
            while (rs.next()) {
                String ssn = rs.getString(properties.getProperty("SSN"));
                String firstName = rs.getString(properties.getProperty("firstName"));
                String lastName = rs.getString(properties.getProperty("lastName"));
                String gender = rs.getString(properties.getProperty("gender"));
                double weight = rs.getDouble(properties.getProperty("weight"));
                double height = rs.getDouble(properties.getProperty("height"));
                pat = new Patient();
                pat.setSSN(ssn);
                pat.setFirstName(firstName);
                pat.setLastName(lastName);
                pat.setGender(gender);
                pat.setWeight(weight);
                pat.setHeight(height);
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

    //Gets all patients from database and return it as Patient array
    @Override
    public List<Patient> readPatients() {
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
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC-driver failed");
        }
        List<Patient> lista = new ArrayList();
        Statement statement = null;
        ResultSet rs = null;
        try {
            String sqlSelect = "SELECT * FROM " + properties.getProperty("table");
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlSelect);
            while (rs.next()) {
                String ssn = rs.getString(properties.getProperty("SSN"));
                String firstName = rs.getString(properties.getProperty("firstName"));
                String lastName = rs.getString(properties.getProperty("lastName"));
                String gender = rs.getString(properties.getProperty("gender"));
                double weight = rs.getDouble(properties.getProperty("weight"));
                double height = rs.getDouble(properties.getProperty("height"));
                Patient p = new Patient();
                p.setSSN(ssn);
                p.setFirstName(firstName);
                p.setLastName(lastName);
                p.setGender(gender);
                p.setWeight(weight);
                p.setHeight(height);
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
