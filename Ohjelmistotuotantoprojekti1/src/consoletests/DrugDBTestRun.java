package consoletests;

import java.util.List;
import main.ActiveAgent;
import main.Drug;
import main.DrugDAO;
import main.Drugs;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author joosiika
 */
public class DrugDBTestRun {

    
    public static void main(String[] args) {
        DrugDAO drugdb = new DrugDAO();
        Drugs drugs = drugdb.readDrugs();
        List<Drug> drugCollection = drugs.getCollection();
        drugdb.finalize();
        System.out.println(drugCollection.get(0).getName() + ", " + drugCollection.get(0).getActiveAgents().get(0).getName());
        System.out.println("that's all folks!");
    }
    
}
