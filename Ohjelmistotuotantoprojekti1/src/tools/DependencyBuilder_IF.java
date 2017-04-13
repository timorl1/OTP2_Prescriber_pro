package tools;

import resources.diagnose.Diagnose;
import resources.prescription.Prescription;
import resources.user.User_IF;
import resources.patient.Patient;
import java.util.List;

/**
 *Collects the data that is needed to make new prescription
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface DependencyBuilder_IF {

    /**
     *Builds all the data that is needed for the prescription from patient
     * @param patient object that is saved into prescription
     * @return patient object
     */
    public abstract Patient buildPatient(Patient patient);

    /**
     *Builds doctors presciptions
     * @param doctor current doctor that is logged in
     * @return list of presciption objects made by current doctor
     */
    public abstract List<Prescription> buildDoctorPrescriptions(User_IF doctor);

    /**
     *Builds diagnose from various external databases
     * @param diagnose object that the data is saved on
     * @return built diagnose
     */
    public abstract Diagnose buildDiagnose(Diagnose diagnose);

    /**
     *Builds all the data that is needed to make the new prescription
     * @param prescription object that the data is saved on
     * @return built prescription
     */
    public abstract Prescription buildPrescription(Prescription prescription);
}
