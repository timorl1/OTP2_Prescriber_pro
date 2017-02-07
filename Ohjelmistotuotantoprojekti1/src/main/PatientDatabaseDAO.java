/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.*;
/**
 *
 * @author joosiika
 */
public class PatientDatabaseDAO implements PatientDatabaseDAO_IF{

    @Override
    public Connection createConnection(String url, String username, String password) {
        
        final String URL = "jdbc:mysql://" + url;
	final String USERNAME = username;
	final String PASSWORD = password;
        
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.err.println("JDBC-ajurin lataus epäonnistui");
            System.exit(-1);
        }

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Tietokanta yhteyden luonti epäonnistui");
        }
        return conn;
    }

    @Override
    public String[] getDBFieldNames(Connection conn, PatientDBParameter PDP) {
        String[] fieldNames = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + PDP.getPatientTable() + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            fieldNames = new String[20];
            int i = 0;
            while (rs.next()) {
                fieldNames[i] = rs.getString("COLUMN_NAME");
                i++;
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                        rs.close();
                }
                if (stmt != null) {
                        stmt.close();
                }
            }
            catch (Exception e) {
                    e.printStackTrace();
            }
        }
        return fieldNames;
    }

    @Override
    public PatientDBParameter readPatientDBParameter() {
        
        final String URL = "jdbc:mysql://localhost/patient";
	final String USERNAME = "root";
	final String PASSWORD = "66067251isojoo";
        
        Connection conn = null;
        PatientDBParameter PDP = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.err.println("JDBC-ajurin lataus epäonnistui");
            System.exit(-1);
        }

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Tietokanta yhteyden luonti epäonnistui");
        }
        
        String[] result = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM patientdbparameter";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                PDP = new PatientDBParameter(rs.getString("url"), rs.getString("user"), rs.getString("password"), rs.getString("tableName"),rs.getString("SSN"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("weight"), rs.getString("height"), rs.getString("allergies"));
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                        rs.close();
                }
                if (stmt != null) {
                        stmt.close();
                }
            }
            catch (Exception e) {
                    e.printStackTrace();
            }
        }
        return PDP;
    }

    @Override
    public boolean writePatientDBParameter(PatientDBParameter PDP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
