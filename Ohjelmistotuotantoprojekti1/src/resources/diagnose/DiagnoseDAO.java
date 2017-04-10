/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.diagnose;

import resources.patient.Patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import resources.database.DatabaseDAO;


public class DiagnoseDAO implements DiagnoseDAO_IF {
    
    private Properties properties = new Properties();
    private DatabaseDAO parameters = new DatabaseDAO(properties, "db.properties");
    Connection connection = null;
    
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

    @Override
    public int getPatientDiagnoseCount(Patient patient) {
        properties = parameters.readDBProperties();
        int count = 0;
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
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT COUNT(*) FROM diagnoosi where " + properties.getProperty("SSN") + " = ?";
        try {
            statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, patient.getSSN());
            rs = statement.executeQuery();
            while(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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
        return count;
    }
    
}
