/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finder;

import java.util.List;
import resources.diagnose.Diagnose;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface DiagnoseChecker_IF {
    public abstract boolean hasNewDiagnose();
    public abstract List<Diagnose> getNewDiagnoses();
    public abstract void performCheck();
}
