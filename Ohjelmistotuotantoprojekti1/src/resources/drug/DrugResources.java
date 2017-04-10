/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.drug;

import resources.drug.Drug;
import resources.drug.DrugDAO;
import resources.drug.DrugDAO_IF;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DrugResources implements DrugResources_IF {
    private DrugDAO_IF drugDAO;
    private Map<Integer, Drug> drugs;
    
    public DrugResources() {
        this.drugDAO = new DrugDAO();
        this.drugs = new HashMap();
        this.drugDAO.readDrugs().forEach(drug ->{
            this.drugs.put(drug.getSN(), drug);
        });
    }
    
    @Override
    public List<Drug> getDrugs() {
        List<Drug> drugList = new ArrayList();
        drugList.addAll(drugs.values());
        return drugList;
    }
}
