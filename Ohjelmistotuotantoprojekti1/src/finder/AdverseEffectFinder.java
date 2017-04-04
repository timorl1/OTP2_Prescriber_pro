/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finder;

import resources.client.Diagnose;
import resources.app.Prescription;
import java.util.ArrayList;
import java.util.List;


public class AdverseEffectFinder implements AdverseEffectFinder_IF {

    @Override
    public List<Diagnose> findMatch(Prescription prescription) {
        List<Diagnose> suspects = this.findSuspects(prescription);
        if (!suspects.isEmpty()) {
            List<Diagnose> matches = new ArrayList();
            prescription.getDrug().getCommonAdverseEffects().forEach(ae -> {
                suspects.forEach(d -> {
                    if (ae.getDiseaseID() == d.getDiseaseID()) {
                        matches.add(d);
                    }
                });
            });
            return matches;
        }
        return null;
    }

    @Override
    public List<Diagnose> findSuspects(Prescription prescription) {
        List<Diagnose> suspects = new ArrayList();
        prescription.getPatient().getDiagnoses().forEach(d -> {
            if (d.getCreationDate().after(prescription.getStartDate())) {
                suspects.add(d);
            }
        });
        return suspects;
    }
    
}
