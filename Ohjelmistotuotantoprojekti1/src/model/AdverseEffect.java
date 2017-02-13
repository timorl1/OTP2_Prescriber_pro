package model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author joosiika
 */
@Entity (name="haittavaikutus")
@Table (name="haittavaikutus")
public class AdverseEffect {
    
    @Id
    @Column(name="id")
    private int id;
    @Column(name="sairaus_id")
    private int diseaseID;
    @Column(name="nimi")
    private String name;
    @ManyToMany (mappedBy="commonAdverseEffects")
    private List<Drug> drugs;
    @ManyToMany (mappedBy="rareAdverseEffects")
    private List<Drug> drugs2;

    public AdverseEffect() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
