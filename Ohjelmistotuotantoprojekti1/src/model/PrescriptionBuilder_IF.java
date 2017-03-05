package model;

/**
 *
 * @author joosiika
 */
public interface PrescriptionBuilder_IF {
    public abstract Patient getPrescriptionPatient(Prescription prescription);
    public abstract Doctor getPrescriptionDoctor(Prescription prescription);
    public abstract Diagnose getPrescriptionDiagnose(Prescription prescription);
    public abstract Drug getPrescriptionDrug(Prescription prescription);
    public abstract Prescription buildPrescription(Prescription prescription);
}
