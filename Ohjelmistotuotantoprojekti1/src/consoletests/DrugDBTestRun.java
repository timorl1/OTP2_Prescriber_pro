package consoletests;

import java.util.List;
import model.Drug;
import dao.DrugDAO;

/**
 *
 * @author joosiika
 */
public class DrugDBTestRun {

    
    public static void main(String[] args) {
        DrugDAO drugdb = new DrugDAO();
        List<Drug> drugs = drugdb.readDrugs();
        drugdb.finalize();
        System.out.println(drugs.get(0).getName() + ", " + drugs.get(0).getDrugActiveAgents().get(0).getActiveAgent().getName());
        System.out.println("that's all folks!");
    }
    
}
