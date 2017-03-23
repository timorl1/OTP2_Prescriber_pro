/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import model.Diagnose;
import model.Patient;

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
    
    //Get single diagnose from database identified by diagnose id
    @Override
    public Diagnose readDiagnose(int diagnoseID) {
        properties = parameters.readDBProperties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + properties.getProperty("url") + "/" + properties.getProperty("db"), properties.getProperty("username"), properties.getProperty("password"));
        }catch (SQLException e) {
            System.out.println("Yhteyden muodostaminen epäonnistui");
            try {
                System.out.println("Yritetään muodostaa yhteys Jenkinsillä");
                connection = DriverManager.getConnection("jdbc:mysql://10.114.32.151:3306/sairaaladb", "jenkins",
                "jenkins");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC-ajurin lataus epäonnistui");
        }
        
        Diagnose dia = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT * FROM diagnoosi where diagnoosiID = ?";
        try {
            statement = connection.prepareStatement(sqlSelect);
            statement.setInt(1, diagnoseID);
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("diagnoosiID");
                int diseaseID = rs.getInt("sairaustunniste");
                String epicrisis = rs.getString("epikriisi");
                Timestamp creationDate = rs.getTimestamp("luontipäivä");
                Timestamp resolutionDate = rs.getTimestamp("päättymispäivä");
                String patientID = rs.getString("hetu");
                int doctorID = rs.getInt("työntekijänumero");
                dia = new Diagnose();
                dia.setId(id);
                dia.setPatientId(patientID);
                dia.setDoctorId(doctorID);
                dia.setDiseaseID(diseaseID);
                dia.setEpicrisis(epicrisis);
                dia.setCreationDate(creationDate);
                dia.setResolutionDate(resolutionDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        return dia;
    }

    // Reads patients diagnoses from database
    @Override
    public List<Diagnose> readPatientDiagnoses(Patient pat) {
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
        
        Diagnose dia = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Diagnose> diagnoses = new ArrayList();
        String sqlSelect = "SELECT * FROM diagnoosi where " + properties.getProperty("SSN") + " = ?";
        try {
            statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, pat.getSSN());
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("diagnoosiID");
                int diseaseID = rs.getInt("sairaustunniste");
                String epicrisis = rs.getString("epikriisi");
                Timestamp creationDate = rs.getTimestamp("luontipäivä");
                Timestamp resolutionDate = rs.getTimestamp("päättymispäivä");
                String patientID = rs.getString("hetu");
                int doctorID = rs.getInt("työntekijänumero");
                dia = new Diagnose();
                dia.setId(id);
                dia.setPatientId(patientID);
                dia.setDoctorId(doctorID);
                dia.setDiseaseID(diseaseID);
                dia.setEpicrisis(epicrisis);
                dia.setCreationDate(creationDate);
                dia.setResolutionDate(resolutionDate);
                diagnoses.add(dia);
            }
            /*if (!diagnoses.isEmpty()) {
                pat.setDiagnoses(diagnoses);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        return diagnoses;
    }

}
