/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.disease;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity(name="allergeenit")
@Table(name="allergeenit")
public class Disease_Allergen {
    @Id
    @Column(name="allergeeniID")
    private int id;
    @Column(name="nimi")
    private String name;
    @ManyToMany (mappedBy="allergens")
    private List<Disease> diseases = new ArrayList();
    

    public Disease_Allergen() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    
    
    
    @Override
    public String toString() {
        return this.getName();
    }
    
}
