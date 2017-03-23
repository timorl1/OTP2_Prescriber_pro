package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
/**
 * Defines database properties
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface DatabaseDAO_IF {

    /**
     * Creates and returns a connection to a server
     * @return connection to a server
     */
    public abstract Connection connectServer();

    /**
     * Reads database names from the server
     * @return database names as array of strings
     */
    public abstract ArrayList getDatabases();


    /**
     * Creates a connection to a server
     * @return connection to a server
     */
    public abstract Connection connectDB();

    /**
     * Reads all table names from a database
     * @return array of strings
     */
    public abstract ArrayList getTables();

    /**
     * Reads all field names from a table
     * @return field names from a table as array of strings
     */
    public abstract ArrayList getDBFieldNames();

    /**
     * Accesses a .properties file
     * @return properties-object
     */
    public abstract Properties readDBProperties();
   

    /**
     * Accesses a .properties file and writes the properties-object
     * @param comment Comment
     * @return true if successful, false if not
     */
    public abstract boolean writeDBProperties(String comment);
}
