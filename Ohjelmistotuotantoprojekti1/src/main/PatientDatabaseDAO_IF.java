package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author joosiika
 */
public interface PatientDatabaseDAO_IF {
    //Creates the connection to the Patient database
    public abstract Connection createConnection(String url, String username, String password);
    //Reads all field names from the Patient database and returns them as array of strings
    public abstract ArrayList getDBFieldNames(Connection connection);
    //Set a property to patientDBProperties
    public abstract Properties readPatientDBProperties();
    //Accesses the software's database and writes a PatientDBParameter-object into the patientdbparameter-table
    public abstract boolean writePatientDBProperties(Properties properties);
}
