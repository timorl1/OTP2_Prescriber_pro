package main;

import java.util.ArrayList;

/**
 *
 * @author joosiika
 */
public class Drugs {
    
    private ArrayList<Drug> druglist;

    public Drugs(ArrayList drugs) {
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
}
