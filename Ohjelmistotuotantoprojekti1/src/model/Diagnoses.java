/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public class Diagnoses {
    private List<Diagnose> diagnoselist;

    public Diagnoses(List diagnoselist) {
        this.diagnoselist = diagnoselist;
    }
    
    public void addDiagnose(Diagnose diagnose) {
        this.diagnoselist.add(diagnose);
    }
    
    public Diagnose getDiagnose(int id) {
        if (diagnoselist.indexOf(id) != -1) {
            return diagnoselist.get(diagnoselist.indexOf(id));
        }
        return null;
    }
    
    public List getCollection() {
        return this.diagnoselist;
    }
    
    public Diagnose forEach() {
        for (Diagnose diagnose : this.diagnoselist) {
            return diagnose;
        }
        return null;
    }
}
