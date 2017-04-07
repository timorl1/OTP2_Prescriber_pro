package calculator;

import resources.drug.Drug;
import resources.client.Patient;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 * 
 * Drug dosage calculator
 */
public interface DoseCalculator_IF {

    /**
     *Gets drugs optimal dose
     * @param patient is the patient that the calculator is used on
     * @param drug is the that is being prescribed
     * @return the optimal dose of the drug
     */
    public abstract double getOptimalDose(Patient patient, Drug drug);

    /**
     *Gets the highest recommended dose
     * @param patient is the patient that the calculator is used on
     * @param drug is the that is being prescribed
     * @return the highest recommended dose of the drug
     */
    public abstract double getMaxDose(Patient patient, Drug drug);

    /**
     *Calculates cumalative dosage of the drug
     * @param patient the patient that the calculator is used on
     * @param drug the drug that is being prescribed
     * @param dose the dosage that is being prescribed
     * @param timesADay how many times it should be taken in a day
     * @param duration half-life of the drug
     * @return time that the drug is in the body
     */
    public abstract double getCumulativeDose(Patient patient, Drug drug, double dose, int timesADay, int duration);
}
