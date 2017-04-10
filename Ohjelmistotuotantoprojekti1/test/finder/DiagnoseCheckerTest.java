/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import resources.prescription.Prescription;
import resources.prescription.PrescriptionDAO;
import resources.user.UserDAO;
import resources.user.User_IF;
import resources.diagnose.Diagnose;
import resources.patient.Patient;
import resources.patient.PatientDAO;
import tools.DependencyBuilder;
import tools.DependencyBuilder_IF;

/**
 *
 * @author joosiika
 */
public class DiagnoseCheckerTest {
    PatientDAO pdao = new PatientDAO();
    UserDAO udao = new UserDAO();
    PrescriptionDAO presdao = new PrescriptionDAO();
    
    public DiagnoseCheckerTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasNewDiagnose method, of class DiagnoseChecker.
     */
    @Test
    public void testHasNewDiagnose() {
        System.out.println("hasNewDiagnose");
        DiagnoseChecker instance = null;
        boolean expResult = false;
        boolean result = instance.hasNewDiagnose();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewDiagnoses method, of class DiagnoseChecker.
     */
    @Test
    public void testGetNewDiagnoses() {
        System.out.println("getNewDiagnoses");
        Prescription prescription = presdao.readPrescription(1);
        Map<String, Patient> patients = new HashMap();
        pdao.readPatients().forEach(p -> {
            patients.put(p.getSSN(), p);
        });
        Map<Integer, User_IF> users = new HashMap();
        udao.getUsers().forEach(u -> {
            users.put(u.getUserID(), u);
        });
        DependencyBuilder_IF db = new DependencyBuilder(patients, users);
        db.buildPrescription(prescription);
        System.out.println(prescription.getPatient());
        DiagnoseChecker_IF instance = new DiagnoseChecker(prescription);
        List<Diagnose> expResult = null;
        List<Diagnose> result = instance.getNewDiagnoses();
        assertEquals(expResult, result);
    }

    /**
     * Test of performCheck method, of class DiagnoseChecker.
     */
    @Test
    public void testPerformCheck() {
        System.out.println("performCheck");
        DiagnoseChecker instance = null;
        instance.performCheck();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
