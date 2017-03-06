package dao;

import java.util.List;
import model.Diagnose;
import model.Disease;

/**
 *
 * @author Timo
 */
public interface DiseaseDAO_IF {
    public abstract Disease getDisease(int diseaseId);
}
