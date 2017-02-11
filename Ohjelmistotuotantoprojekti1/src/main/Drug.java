package main;

import java.util.ArrayList;

/**
 *
 * @author joosiika
 */
public class Drug {
    private int SN;
    private String name;
    private ArrayList<String> activeAgents;
    private ArrayList<String> allergens;
    private double recommendedDose;
    private double maxDose;
    private String unit;
    private ArrayList<String> commonAdverseEffects;
    private ArrayList<String> rareAdverseEffects;

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

    public ArrayList<String> getActiveAgents() {
        return activeAgents;
    }

    public void setActiveAgents(ArrayList<String> activeAgents) {
        this.activeAgents = activeAgents;
    }

    public ArrayList<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<String> allergens) {
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

    public ArrayList<String> getCommonAdverseEffects() {
        return commonAdverseEffects;
    }

    public void setCommonAdverseEffects(ArrayList<String> commonAdverseEffects) {
        this.commonAdverseEffects = commonAdverseEffects;
    }

    public ArrayList<String> getRareAdverseEffects() {
        return rareAdverseEffects;
    }

    public void setRareAdverseEffects(ArrayList<String> rareAdverseEffects) {
        this.rareAdverseEffects = rareAdverseEffects;
    }
}
