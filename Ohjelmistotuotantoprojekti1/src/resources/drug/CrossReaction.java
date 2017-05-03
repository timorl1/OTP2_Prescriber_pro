/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.drug;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity (name="yhteisvaikutus")
@Table (name="yhteisvaikutus")
public class CrossReaction {
    
    
    private int id;
    private Drug aine1;
    private Drug aine2;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aine1")
    public Drug getAine1() {
        return aine1;
    }

    public void setAine1(Drug aine1) {
        this.aine1 = aine1;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aine2")
    public Drug getAine2() {
        return aine2;
    }

    public void setAine2(Drug aine2) {
        this.aine2 = aine2;
    }
    
    @Id
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
 
