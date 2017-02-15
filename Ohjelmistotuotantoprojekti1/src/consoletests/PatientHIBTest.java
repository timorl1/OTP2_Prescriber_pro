/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import java.sql.SQLException;
import model.Patient;
import model.Patients;
import dao.PatientDAO;
import dao.PatientHIB;
import java.util.List;

/**
 *
 * @author Timo
 */
public class PatientHIBTest {

  
    public static void main(String[] args) throws SQLException {
        
        PatientHIB dao = new PatientHIB();
        Patient patient = new Patient();
        patient = dao.readPatient("123456-789a");
        System.out.println(patient.getGender());
        Patients patients = dao.readPatients();
        List<Patient> plist = patients.getCollection();
        for (Patient p : plist){
            System.out.println(p.getFirstName()+" "+p.getLastName());
        }
    }
    
}
