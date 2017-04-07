
package drugresources;

import resources.drug.Drug;
import java.util.List;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface DrugResources_IF {

    /**
     * Gets list of drugs from DrugDAO
     * @return list of drug-objects
     */
    public abstract List<Drug> getDrugs();
}
