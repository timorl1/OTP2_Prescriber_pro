package resources.data;

import resources.data.Disease;

/**
 * Interface that defines methods for getting disease from database
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface DiseaseDAO_IF {

    /**
     * Gets disease by id from database
     * @param diseaseId Id of the disease
     * @return disease object
     */
    public abstract Disease getDisease(int diseaseId);
}