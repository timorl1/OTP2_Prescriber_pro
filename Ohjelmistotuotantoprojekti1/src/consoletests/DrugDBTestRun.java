package consoletests;

import main.Drug;
import main.DrugDAO;
import main.Drugs;

/**
 *
 * @author joosiika
 */
public class DrugDBTestRun {

    
    public static void main(String[] args) {
        DrugDAO drugdb = new DrugDAO();
        Drug drug = drugdb.readDrug(123456);
        
        System.out.println(drug.getName());
        System.out.println(drug.getActiveAgents().get(0).getName());
    }
    
}
