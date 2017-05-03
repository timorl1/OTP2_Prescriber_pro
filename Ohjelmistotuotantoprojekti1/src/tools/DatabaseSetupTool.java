package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DatabaseSetupTool {
    
    private final String[] dbTypes = {"Potilastiedot", "Diagnoosit","Työntekijät"};
    
    private Properties properties;
    //private DatabaseDAO_IF db;
    
    public DatabaseSetupTool(String type) {
        this.properties = new Properties();
        //this.db = new DatabaseDAO(properties, type + "db.properties");
        //this.properties = db.readDBProperties();
    }
    
    public List<String> getTypes() {
        List<String> nameList = new ArrayList();
	for (int i = 0; i < dbTypes.length; i++) {
            nameList.add(dbTypes[i]);
	}
	return nameList;
    }
}
