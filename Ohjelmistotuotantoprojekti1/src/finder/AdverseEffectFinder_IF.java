/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finder;

import resources.client.Diagnose;
import resources.app.Prescription;
import java.util.List;

/**
 *
 * @author joosiika
 */
public interface AdverseEffectFinder_IF {
    public abstract List<Diagnose> findSuspects(Prescription prescription);
    public abstract List<Diagnose> findMatch(Prescription prescription);
}
