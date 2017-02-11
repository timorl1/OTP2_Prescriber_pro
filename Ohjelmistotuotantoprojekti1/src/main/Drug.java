package main;

import java.util.ArrayList;
import javax.persistence.*;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author joosiika
 */
@Entity
@Immutable
@Table (name="lääke")
public class Drug {
    @Id
    @Column (name="tuotenumero")
    private int SN;
    @Column (name="nimi")
    private String name;
    @ManyToMany (mappedBy="lääkeaine", cascade = {CascadeType.ALL})
    private ArrayList<ActiveAgent> activeAgents;
    @ManyToMany (mappedBy="lääkkeen_allergeeni", cascade = {CascadeType.ALL})
    private ArrayList<Allergen> allergens;
    @Column (name="suositeltuannos")
    private double recommendedDose;
    @Column (name="maxannos")
    private double maxDose;
    @Column (name="yksikkö")
    private String unit;
    @ManyToMany (mappedBy="yleinen", cascade = {CascadeType.ALL})
    private ArrayList<AdverseEffect> commonAdverseEffects;
    @ManyToMany (mappedBy="harvinainen", cascade = {CascadeType.ALL})
    private ArrayList<AdverseEffect> rareAdverseEffects;

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

    public ArrayList<ActiveAgent> getActiveAgents() {
        return activeAgents;
    }

    public void setActiveAgents(ArrayList<ActiveAgent> activeAgents) {
        this.activeAgents = activeAgents;
    }

    public ArrayList<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<Allergen> allergens) {
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

    public ArrayList<AdverseEffect> getCommonAdverseEffects() {
        return commonAdverseEffects;
    }

    public void setCommonAdverseEffects(ArrayList<AdverseEffect> commonAdverseEffects) {
        this.commonAdverseEffects = commonAdverseEffects;
    }

    public ArrayList<AdverseEffect> getRareAdverseEffects() {
        return rareAdverseEffects;
    }

    public void setRareAdverseEffects(ArrayList<AdverseEffect> rareAdverseEffects) {
        this.rareAdverseEffects = rareAdverseEffects;
    }
}
