package main;

import java.util.ArrayList;

/**
 *
 * @author joosiika
 */
public class Drugs {
    
    private ArrayList<Drug> druglist;

    public Drugs() {
        this.druglist = new ArrayList();
    }
    
    public void addDrug(Drug drug) {
        this.druglist.add(drug);
    }
    
    public Drug getDrug(int SN) {
        if (druglist.contains(SN)) {
            return druglist.get(druglist.indexOf(SN));
        }
        return null;
    }
}
