package model;

import javax.persistence.*;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity(name="lääkkeen_vaikuttava_aine")
@Table(name="lääkkeen_vaikuttava_aine")
public class DrugActiveAgent {
    
    private int id;
    private double concentration;
    private Drug drug;
    private ActiveAgent activeAgent;

    @Id
    @Column(name="lääkkeen_vaikuttava_aine_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="pitoisuus")
    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lääke_tuotenumero")
    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vaikuttava_aine_id")
    public ActiveAgent getActiveAgent() {
        return activeAgent;
    }

    public void setActiveAgent(ActiveAgent activeAgent) {
        this.activeAgent = activeAgent;
    }
    
}
