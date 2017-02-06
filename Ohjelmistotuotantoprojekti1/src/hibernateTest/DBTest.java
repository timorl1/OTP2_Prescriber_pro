package hibernateTest;

import javax.persistence.*;
/**
 *
 * @author Timo
 */
@Entity
@Table(name="testtable")
public class DBTest{
    @Id
    @Column(name="id")
    private int id;

    public DBTest() {
    }

    public DBTest(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
