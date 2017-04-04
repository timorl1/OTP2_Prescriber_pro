package resources.app;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DatabaseDAO implements DatabaseDAO_IF{
    
    private Properties dbProperties;
    private String fileName;
    private ArrayList <String> returnArray;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
    public DatabaseDAO(Properties dbProperties, String fileName) {
        this.dbProperties = dbProperties;
        this.fileName = fileName;
        this.returnArray = new ArrayList();
    }

    @Override
    public Connection connectServer() {
        
        final String URL = "jdbc:mysql://" + dbProperties.getProperty("url");
	final String USERNAME = dbProperties.getProperty("username");
	final String PASSWORD = dbProperties.getProperty("password");
        
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
            System.err.println("Tietokantayhteyden luonti epäonnistui");
        }
        return conn;
    }
    
    @Override
    public Connection connectDB() {
        
        final String URL = "jdbc:mysql://" + dbProperties.getProperty("url") + "/" + dbProperties.getProperty("db");
	final String USERNAME = dbProperties.getProperty("username");
	final String PASSWORD = dbProperties.getProperty("password");
        
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
            System.err.println("Tietokantayhteyden luonti epäonnistui");
        }
        return conn;
    }
    
    @Override
    public ArrayList getDatabases() {
        this.conn = this.connectServer();
        this.returnArray = new ArrayList();
        this.stmt = null;
        this.rs = null;
        try {
            rs = conn.getMetaData().getCatalogs();
            while (rs.next()) {
                returnArray.add(rs.getString("TABLE_CAT"));
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
        return returnArray;
    }
    
    @Override
    public ArrayList getTables() {
        this.conn = this.connectDB();
        this.returnArray = new ArrayList();
        this.stmt = null;
        this.rs = null;
        try {
            rs = conn.getMetaData().getTables(null, null, "%", null);
            while (rs.next()) {
                returnArray.add(rs.getString(3));
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
        return this.returnArray;
    }

    @Override
    public ArrayList getDBFieldNames() {
        this.conn = this.connectDB();
        this.returnArray = new ArrayList();
        this.stmt = null;
        this.rs = null;
        String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + dbProperties.getProperty("table") + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                returnArray.add(rs.getString("COLUMN_NAME"));
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
        return this.returnArray;
    }

    @Override
    public Properties readDBProperties() {
        
        FileInputStream fis;
        
        try {
            fis = new FileInputStream(this.fileName);
            this.dbProperties.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.dbProperties;
    }

    @Override
    public boolean writeDBProperties(String comment) {
        
        FileOutputStream fos;
        
        try {
            fos = new FileOutputStream(this.fileName);
            this.dbProperties.store(fos, comment);
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
