/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import java.sql.SQLException;
import resources.patient.Patient;
import resources.patient.PatientDAO;
import resources.patient.PatientHIB;
import java.util.List;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PatientHIBTest {

  
    public static void main(String[] args) throws SQLException {
        
        PatientHIB dao = new PatientHIB();
        Patient patient = new Patient();
        patient = dao.readPatient("123456-789a");
        System.out.println(patient.getGender());
        List<Patient> patients = dao.readPatients();
        for (Patient p : patients){
            System.out.println(p.getFirstName()+" "+p.getLastName());
        }
    }
    
}
