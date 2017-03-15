
package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface DrugResources_IF {

    /**
     * Gets list of drugs from DrugDAO
     * @return list of drug-objects
     */
    public abstract List<Drug> getDrugs();
}
