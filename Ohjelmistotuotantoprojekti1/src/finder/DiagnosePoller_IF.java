/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finder;

import java.util.List;
import resources.client.Diagnose;
import resources.client.Patient;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface DiagnosePoller_IF {
    public abstract boolean hasNewDiagnose();
    public abstract List<Diagnose> getNewDiagnoses();
}
