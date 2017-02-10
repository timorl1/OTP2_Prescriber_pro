/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author joosiika
 */
public class PatientDatabaseDAO implements PatientDatabaseDAO_IF{
    
    private static Properties defaultProperties;
    private ArrayList <String> fieldNames;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    @Override
    public Connection createConnection(String url, String username, String password) {
        
        final String URL = "jdbc:mysql://" + url;
	final String USERNAME = username;
	final String PASSWORD = password;
        
        this.conn = null;
        
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
    public ArrayList getDBFieldNames(Connection conn) {
        this.fieldNames = new ArrayList();
        this.stmt = null;
        this.rs = null;
        String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + defaultProperties.getProperty("tableName") + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                fieldNames.add(rs.getString("COLUMN_NAME"));
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
    public Properties readPatientDBProperties() {
        
        defaultProperties = new Properties();
        FileInputStream fis;
        
        try {
            fis = new FileInputStream("default.properties");
            defaultProperties.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultProperties;
    }

    @Override
    public boolean writePatientDBProperties(Properties properties) {
        
        defaultProperties = properties;
        FileOutputStream fos;
        
        try {
            fos = new FileOutputStream("default.properties");
            this.defaultProperties.store(fos, "Patient Database Settings");
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
