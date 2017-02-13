package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joosiika
 */
public class Drugs {
    
    private List<Drug> druglist;

    public Drugs(List drugs) {
        this.druglist = drugs;
    }
    
    public void addDrug(Drug drug) {
        this.druglist.add(drug);
    }
    
    public Drug getDrug(int SN) {
        if (druglist.indexOf(SN) != -1) {
            return druglist.get(druglist.indexOf(SN));
        }
        return null;
    }
    
    public List getCollection() {
        return this.druglist;
    }
    
    public Drug forEach() {
        for (Drug drug : this.druglist) {
            return drug;
        }
        return null;
    }
}
