/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Timo
 */
public class PatientDAO implements PatientDAO_IF{
    
    private PatientDatabaseDAO parameters = new PatientDatabaseDAO();
    private Patient patient;
    Connection connection = null;
    private Properties properties = parameters.readPatientDBProperties();
    /*final String URL = "jdbc:mysql://localhost/patient";
    final String USER = "app";
    final String PASSWD = "appdb";*/
    
    //Set database parameters, what to get
    public PatientDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
            }catch(Exception e){
                System.err.print("Ajuria ei löytynyt");
                System.exit(0);
            }
    }
    /*public PatientDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(parameters.getUrl(), parameters.getUsername(), parameters.getPassword());
            }catch(Exception e){
                System.err.print("Ajuria ei löytynyt");
                System.exit(0);
            }
    }*/
    @Override
    protected void finalize(){
	try{
            if(connection != null){
                connection.close();
            }
            }catch(Exception e){
            	e.printStackTrace();
            }
	}
    
    //Get single patient from database with SSN
    @Override
    public Patient readPatient(String SSN) throws SQLException{
        Patient pat = null;
        PreparedStatement statement = null;
	ResultSet rs = null;
            try {
            	//String sqlSelect = "SELECT * FROM patient where hetu = ?";
                String sqlSelect = "SELECT * FROM "+properties.getProperty("tableName")+" where "+properties.getProperty("SSN")+" = ?";
		statement = connection.prepareStatement(sqlSelect);
		statement.setString(1, SSN);
                rs = statement.executeQuery();
		while(rs.next()){
			/*String ssn = rs.getString("hetu");
			String firstName = rs.getString("etunimi");
			String lastName = rs.getString("sukunimi");
                        String gender = rs.getString("sukupuoli");
                        double weight = rs.getDouble("paino");
                        double height = rs.getDouble("pituus");*/
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

		}catch(SQLException e){
			System.err.println("viesti: "+ e.getMessage() );
			System.err.println("virhekoodi: "+ e.getErrorCode());
			System.err.println("sql-tilakoodi : "+ e.getSQLState());
                }finally{
                    if(rs!=null){
			rs.close();
                    }
		if(statement!=null){
			statement.close();
		}
		if(connection != null){
			connection.close();
			}
		}
        return pat;
    }
    
    //Get all patients from database and return it as patient array
    @Override
    public Patient[] readPatients() throws SQLException{
        ArrayList<Patient> lista = new ArrayList<Patient>();
		Statement statement = null;
		ResultSet rs = null;
		try {
                    //String sqlSelect = "SELECT * FROM patient";
                    String sqlSelect = "SELECT * FROM "+properties.getProperty("tableName");
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sqlSelect);

		while(rs.next()){
                    /*String ssn = rs.getString("hetu");
                    String firstName = rs.getString("etunimi");
                    String lastName = rs.getString("sukunimi");
                    String gender = rs.getString("sukupuoli");
                    double weight = rs.getDouble("paino");
                    double height = rs.getDouble("pituus");*/
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

		}catch(SQLException e){
			System.err.println("viesti: "+ e.getMessage() );
			System.err.println("virhekoodi: "+ e.getErrorCode());
			System.err.println("sql-tilakoodi : "+ e.getSQLState());
                }finally{
                    if(rs!=null){
			rs.close();
                    }
		if(statement!=null){
			statement.close();
		}
		if(connection != null){
			connection.close();
			}
		}
	Patient[] paluuLista = new Patient[lista.size()];
	return (Patient[])lista.toArray(paluuLista);
    }
    
}
