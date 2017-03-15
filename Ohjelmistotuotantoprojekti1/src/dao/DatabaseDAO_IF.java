package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author joosiika
 */
public interface DatabaseDAO_IF {
    //Creates and returns a connection to a server

    /**
     *
     * @return
     */
    public abstract Connection connectServer();
    //Reads database names from the server and returns them as array of strings

    /**
     *
     * @return
     */
    public abstract ArrayList getDatabases();
    //Creates and returns a connection to a server

    /**
     *
     * @return
     */
    public abstract Connection connectDB();
    //Reads all table names from a database and returns the as array of strings

    /**
     *
     * @return
     */
    public abstract ArrayList getTables();
    //Reads all field names from a table and returns them as array of strings

    /**
     *
     * @return
     */
    public abstract ArrayList getDBFieldNames();
    //Accesses a .properties file and returns a properties-object

    /**
     *
     * @return
     */
    public abstract Properties readDBProperties();
    //Accesses a .properties file and writes the properties-object

    /**
     *
     * @param comment
     * @return
     */
    public abstract boolean writeDBProperties(String comment);
}
