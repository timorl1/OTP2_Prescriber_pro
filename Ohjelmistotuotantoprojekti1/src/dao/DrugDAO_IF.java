package dao;

import java.util.List;
import model.Drug;

/**
 *
 * @author joosiika
 */
public interface DrugDAO_IF {
    public abstract Drug readDrug(int SN);
    public abstract List<Drug> readDrugs();
}
