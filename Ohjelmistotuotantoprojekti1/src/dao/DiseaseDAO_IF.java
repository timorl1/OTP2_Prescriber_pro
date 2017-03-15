package dao;

import model.Disease;

/**
 * Interface that defines methods for getting disease from database
 * @author Timo
 */
public interface DiseaseDAO_IF {

    /**
     * Gets disease by id from database
     * @param diseaseId Id of the disease
     * @return disease object
     */
    public abstract Disease getDisease(int diseaseId);
}
