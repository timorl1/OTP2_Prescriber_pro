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
@Entity (name="vaikuttava_aine")
@Table (name="vaikuttava_aine")
public class ActiveAgent {
    private int id;
    private String name;
    private double maxDose;
    private double recommendedDose;
    //@ManyToMany (mappedBy="activeAgents")
    private List<DrugActiveAgent> drugActiveAgents = new ArrayList();

    public ActiveAgent() {
    }

    public ActiveAgent(int id, String name, double maxDose, double recommendedDose) {
        this.id = id;
        this.name = name;
        this.maxDose = maxDose;
        this.recommendedDose = recommendedDose;
    }
    
    public void addDrug(DrugActiveAgent drugActiveAgent) {
        this.drugActiveAgents.add(drugActiveAgent);
    }

    @Id
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="nimi")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="maxannos")
    public double getMaxDose() {
        return maxDose;
    }

    public void setMaxDose(double maxDose) {
        this.maxDose = maxDose;
    }

    @Column(name="suositeltuannos")
    public double getRecommendedDose() {
        return recommendedDose;
    }

    public void setRecommendedDose(double recommendedDose) {
        this.recommendedDose = recommendedDose;
    }

    @OneToMany(mappedBy="activeAgent")
    public List<DrugActiveAgent> getDrugActiveAgents() {
        return drugActiveAgents;
    }

    public void setDrugActiveAgents(List<DrugActiveAgent> drugActiveAgents) {
        this.drugActiveAgents = drugActiveAgents;
    }
}
