package dao;

import java.util.List;
import model.Drug;

/**
 *
 * @author joosiika
 */
public interface DrugDAO_IF {

    /**
     *
     * @param SN
     * @return
     */
    public abstract Drug readDrug(int SN);

    /**
     *
     * @return
     */
    public abstract List<Drug> readDrugs();
}
