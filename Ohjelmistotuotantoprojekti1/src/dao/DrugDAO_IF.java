package dao;

import java.util.List;
import model.Drug;

/**
 * Interface that defines methods for getting info on drugs from database
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface DrugDAO_IF {

    /**
     * Gets drug by id from database
     * @param SN serial number for drug
     * @return drug object
     */
    public abstract Drug readDrug(int SN);

    /**
     * Gets all the drugs from database
     * @return list of drug objects
     */
    public abstract List<Drug> readDrugs();
}
