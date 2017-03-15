package dao;

import java.util.List;
import model.Diagnose;
import model.Disease;

/**
 *
 * @author Timo
 */
public interface DiseaseDAO_IF {

    /**
     *
     * @param diseaseId
     * @return
     */
    public abstract Disease getDisease(int diseaseId);
}
