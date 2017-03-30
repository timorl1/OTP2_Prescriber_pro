package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
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
    private List<Drug> drugsCommon = new ArrayList();
    @ManyToMany (mappedBy="rareAdverseEffects")
    private List<Drug> drugsRare = new ArrayList();

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

    public List<Drug> getDrugsCommon() {
        return drugsCommon;
    }

    public void setDrugsCommon(List<Drug> drugsCommon) {
        this.drugsCommon = drugsCommon;
    }

    public List<Drug> getDrugsRare() {
        return drugsRare;
    }

    public void setDrugsRare(List<Drug> drugsRare) {
        this.drugsRare = drugsRare;
    }
}
