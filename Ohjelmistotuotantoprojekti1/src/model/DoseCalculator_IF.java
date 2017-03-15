package model;

/**
 *
 * @author joosiika
 * 
 * Drug dosage calculator
 */
public interface DoseCalculator_IF {

    /**
     *
     * @param patient
     * @param drug
     * @return
     */
    public abstract double getOptimalDose(Patient patient, Drug drug);

    /**
     *
     * @param patient
     * @param drug
     * @return
     */
    public abstract double getMaxDose(Patient patient, Drug drug);

    /**
     *
     * @param patient
     * @param drug
     * @param dose
     * @param timesADay
     * @param duration
     * @return
     */
    public abstract double getCumulativeDose(Patient patient, Drug drug, double dose, int timesADay, int duration);
}
