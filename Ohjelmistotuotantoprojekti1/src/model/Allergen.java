package model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author joosiika
 */
@Entity (name="allergeeni")
@Table (name="allergeeni")
public class Allergen {
    
    @Id
    @Column(name="id")
    private int id;
    @Column(name="nimi")
    private String name;
    @ManyToMany (mappedBy="allergens")
    private List<Drug> drugs;

    public Allergen() {
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
}
