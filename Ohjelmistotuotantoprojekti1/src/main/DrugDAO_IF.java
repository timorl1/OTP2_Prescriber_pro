package main;

import org.hibernate.Session;

/**
 *
 * @author joosiika
 */
public interface DrugDAO_IF {
    public abstract Drug readDrug(int SN);
    public abstract Drugs readDrugs();
}
