package main;

import javax.persistence.*;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author joosiika
 */
@Entity
@Immutable
@Table (name="vaikuttava_aine")
public class ActiveAgent {
    
    @Id
    @Column(name="aineID")
    private int id;
    @Column(name="nimi")
    private String name;

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
}
