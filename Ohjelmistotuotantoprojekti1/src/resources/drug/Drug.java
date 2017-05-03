package resources.drug;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import resources.drug.AdverseEffect;
import resources.drug.Allergen;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity (name="lääke")
@Table (name="lääke")
public class Drug {
    private int SN;
    private String name;
    private List<DrugActiveAgent> drugActiveAgents = new ArrayList();
    private List<Allergen> allergens = new ArrayList();
    private String unit;
    private List<AdverseEffect> commonAdverseEffects = new ArrayList();
    private List<AdverseEffect> rareAdverseEffects = new ArrayList();
    private List<CrossReaction> crossReactions = new ArrayList();

    public Drug() {
    }

    public Drug(int SN, String name, String unit) {
        this.SN = SN;
        this.name = name;
        this.unit = unit;
    }
    
    public void addActiveAgent(DrugActiveAgent drugActiveAgent) {
        this.drugActiveAgents.add(drugActiveAgent);
    }

    @Id
    @Column (name="tuotenumero")
    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
    }

    @Column (name="nimi")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @OneToMany(mappedBy="drug")
    public List<DrugActiveAgent> getDrugActiveAgents() {
        return drugActiveAgents;
    }

    public void setDrugActiveAgents(List<DrugActiveAgent> drugActiveAgents) {
        this.drugActiveAgents = drugActiveAgents;
    }

    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name="lääkkeen_allergeeni", joinColumns = @JoinColumn(name = "lääke_tuotenumero"), inverseJoinColumns = @JoinColumn(name = "allergeeni_id", referencedColumnName = "id"))
    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }
    
    @Column (name="yksikkö")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name="yleinen", joinColumns = @JoinColumn(name = "lääke_tuotenumero"), inverseJoinColumns = @JoinColumn(name = "haittavaikutus_id", referencedColumnName = "id"))
    public List<AdverseEffect> getCommonAdverseEffects() {
        return commonAdverseEffects;
    }

    public void setCommonAdverseEffects(List<AdverseEffect> commonAdverseEffects) {
        this.commonAdverseEffects = commonAdverseEffects;
    }

    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name="harvinainen", joinColumns = @JoinColumn(name = "lääke_tuotenumero"), inverseJoinColumns = @JoinColumn(name = "haittavaikutus_id", referencedColumnName = "id"))
    public List<AdverseEffect> getRareAdverseEffects() {
        return rareAdverseEffects;
    }

    public void setRareAdverseEffects(List<AdverseEffect> rareAdverseEffects) {
        this.rareAdverseEffects = rareAdverseEffects;
    }
   
    @OneToMany(mappedBy="aine2")
    public List<CrossReaction> getCrossReactions() {
        return crossReactions;
    }

    public void setCrossReactions(List<CrossReaction> crossReactions) {
        this.crossReactions = crossReactions;
    }
    @Override
    public String toString() {
        return this.name + " " + this.drugActiveAgents.get(0).getConcentration() + "mg" + ", " + this.drugActiveAgents.get(0).getActiveAgent().getName();
    }
}
