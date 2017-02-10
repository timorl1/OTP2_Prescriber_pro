/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import java.sql.SQLException;
import main.Patient;
import main.PatientDAO;

/**
 *
 * @author Timo
 */
public class PatientDAOTest {

  
    public static void main(String[] args) throws SQLException {
        
        PatientDAO dao = new PatientDAO();
        Patient patient = new Patient();
        patient = dao.readPatient("120635-124s");
        System.out.println(patient.getGender());
        dao = new PatientDAO();
        for (Patient p : dao.readPatients()){
            System.out.println(p.getFirstName()+" "+p.getLastName());
        }
    }
    
}
