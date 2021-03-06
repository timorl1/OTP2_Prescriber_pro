package resources.disease;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity(name="sairaus")
@Table(name="sairaus")
public class Disease {
    @Id
    @Column(name="sairausid")
    private int id;
    @Column(name="nimi")
    private String diseaseName;
    @Column(name="kuvaus")
    private String diseaseDesc;
    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name="rajoitteet", joinColumns = @JoinColumn(name = "sairausid"), inverseJoinColumns = @JoinColumn(name = "allergeeniID", referencedColumnName = "allergeeniID"))
    private List<Disease_Allergen> allergens = new ArrayList();
    
    public Disease(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseDesc() {
        return diseaseDesc;
    }

    public void setDiseaseDesc(String diseaseDesc) {
        this.diseaseDesc = diseaseDesc;
    }

    public List<Disease_Allergen> getAllergenList() {
        return allergens;
    }

    public void setAllergenList(List<Disease_Allergen> allergenList) {
        this.allergens = allergenList;
    }
    
    @Override
    public String toString() {
        return this.diseaseName;
    }
    
}
