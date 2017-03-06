package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author joosiika
 */
@Entity (name="vaikuttava_aine")
@Table (name="vaikuttava_aine")
public class ActiveAgent {
    
    @Id
    @Column(name="id")
    private int id;
    @Column(name="nimi")
    private String name;
    @ManyToMany (mappedBy="activeAgents")
    private List<Drug> drugs = new ArrayList();

    public ActiveAgent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
