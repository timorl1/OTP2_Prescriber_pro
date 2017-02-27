package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author joosiika
 */
@Entity (name="allergeeni")
@Table (name="allergeeni")
public class Allergen {
    
    @Id
    @Column(name="id")
    private int id;
    @Column(name="nimi")
    private String name;
    @ManyToMany (mappedBy="allergens")
    private List<Drug> drugs = new ArrayList();
    @ManyToMany (mappedBy="disease")
    private List<Disease> disease = new ArrayList();


    public Allergen() {
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

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
    
        public List<Disease> getDisease() {
        return disease;
    }

    public void setDisease(List<Disease> disease) {
        this.disease = disease;
    }

    
    @Override
    public String toString() {
        return this.getName();
    }
}
