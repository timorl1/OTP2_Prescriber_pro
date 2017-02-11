package main;

/**
 *
 * @author joosiika
 */
public interface DrugDAO_IF {
    public abstract Drug readDrug(String SN);
    public abstract Drugs readDrugs();
}
