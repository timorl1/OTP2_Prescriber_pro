/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import resources.disease.DiseaseDAO;
import resources.disease.DiseaseDAO_IF;
import java.util.List;
import resources.drug.Allergen;
import resources.disease.Disease;
import resources.disease.Disease_Allergen;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DiseaseTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DiseaseDAO_IF dao = new DiseaseDAO();
        Disease d = new Disease();
        d = dao.getDisease(1);
        System.out.println(d.getId()+", "+d.getDiseaseName()+", "+d.getDiseaseDesc());
        for(Disease_Allergen dlist : d.getAllergenList()){
            System.out.println(dlist);
        }
        System.exit(0);
    }
    
}
