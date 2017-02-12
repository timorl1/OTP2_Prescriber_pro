package main;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author joosiika
 */
@Entity (name="lääke")
@Table (name="lääke")
public class Drug {
    @Id
    @Column (name="tuotenumero")
    private int SN;
    @Column (name="nimi")
    private String name;
    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name="lääkeaine", joinColumns = @JoinColumn(name = "lääke_tuotenumero"), inverseJoinColumns = @JoinColumn(name = "lääkeaine_id", referencedColumnName = "id"))
    private List<ActiveAgent> activeAgents;
    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name="lääkkeen_allergeeni", joinColumns = @JoinColumn(name = "lääke_tuotenumero"), inverseJoinColumns = @JoinColumn(name = "allergeeni_id", referencedColumnName = "id"))
    private List<Allergen> allergens;
    @Column (name="suositeltuannos")
    private double recommendedDose;
    @Column (name="maxannos")
    private double maxDose;
    @Column (name="yksikkö")
    private String unit;
    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name="yleinen", joinColumns = @JoinColumn(name = "lääke_tuotenumero"), inverseJoinColumns = @JoinColumn(name = "haittavaikutus_id", referencedColumnName = "id"))
    private List<AdverseEffect> commonAdverseEffects;
    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name="harvinainen", joinColumns = @JoinColumn(name = "lääke_tuotenumero"), inverseJoinColumns = @JoinColumn(name = "haittavaikutus_id", referencedColumnName = "id"))
    private List<AdverseEffect> rareAdverseEffects;

    public Drug() {
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<ActiveAgent> getActiveAgents() {
        return activeAgents;
    }

    public void setActiveAgents(List<ActiveAgent> activeAgents) {
        this.activeAgents = activeAgents;
    }

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }

    public double getRecommendedDose() {
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
    }
    
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<AdverseEffect> getCommonAdverseEffects() {
        return commonAdverseEffects;
    }

    public void setCommonAdverseEffects(List<AdverseEffect> commonAdverseEffects) {
        this.commonAdverseEffects = commonAdverseEffects;
    }

    public List<AdverseEffect> getRareAdverseEffects() {
        return rareAdverseEffects;
    }

    public void setRareAdverseEffects(List<AdverseEffect> rareAdverseEffects) {
        this.rareAdverseEffects = rareAdverseEffects;
    }
}
