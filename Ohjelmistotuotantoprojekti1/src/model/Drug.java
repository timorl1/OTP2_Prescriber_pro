package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author joosiika
 */
@Entity (name="lääke")
@Table (name="lääke")
public class Drug {
    private int SN;
    private String name;
    //@ManyToMany (cascade = {CascadeType.ALL})
    //@JoinTable(name="lääkeaine", joinColumns = @JoinColumn(name = "lääke_tuotenumero"), inverseJoinColumns = @JoinColumn(name = "lääkeaine_id", referencedColumnName = "id"))
    private List<DrugActiveAgent> drugActiveAgents = new ArrayList();
    private List<Allergen> allergens = new ArrayList();
    /*@Column (name="suositeltuannos")
    private double recommendedDose;
    @Column (name="maxannos")
    private double maxDose;*/
    private String unit;
    private List<AdverseEffect> commonAdverseEffects = new ArrayList();
    private List<AdverseEffect> rareAdverseEffects = new ArrayList();

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

    /*public double getRecommendedDose() {
        return recommendedDose;
    }

    public void setRecommendedDose(double recommendedDose) {
        this.recommendedDose = recommendedDose;
    }

    public double getMaxDose() {
        return maxDose;
    }

    public void setMaxDose(double maxDose) {
        this.maxDose = maxDose;
    }*/
    
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
    
    @Override
    public String toString() {
        return this.name + " " + this.drugActiveAgents.get(0).getConcentration() + "mg" + ", " + this.drugActiveAgents.get(0).getActiveAgent().getName();
    }
}
