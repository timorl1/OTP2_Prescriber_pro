/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PatientDAO;
import dao.PatientDAO_IF;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joosiika
 */
public class ClientResources_IFTest {
    private ClientResources_IF cres;
    private PatientDAO_IF pb;
    
    public ClientResources_IFTest() {
        this.cres = new ClientResources();
        this.pb = new PatientDAO();
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
     * Test of getPatients method, of class ClientResources_IF.
     */
    @Test
    public void testGetPatients() {
        System.out.println("getPatients");
        int expResult = 2;
        List<Patient> result = cres.getPatients();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getPatientDiagnoses method, of class ClientResources_IF.
     */
    @Test
    public void testGetPatientDiagnoses() {
        System.out.println("getPatientDiagnoses");
        Patient patient = this.pb.readPatient("123456-789a");
        int expResult = 1;
        List<Diagnose> result = this.cres.getPatientDiagnoses(patient);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getDiagnoseDetails method, of class ClientResources_IF.
     */
    @Test
    public void testGetDiagnoseDetails() {
        System.out.println("getDiagnoseDetails");
        Patient patient = this.pb.readPatient("123456-789a");
        Diagnose diagnose = this.cres.getPatientDiagnoses(patient).get(0);
        String expResult = "Äkkikuolema";
        Diagnose result = this.cres.getDiagnoseDetails(diagnose);
        assertEquals(expResult, result.getDisease().getDiseaseName());
    }

    /**
     * Test of getPatientPrescriptions method, of class ClientResources_IF.
     */
    @Test
    public void testGetPatientPrescriptions() {
        System.out.println("getPatientPrescriptions");
        Patient patient = this.pb.readPatient("123456-789a");
        String expResult = "Burana";
        List<Prescription> result = this.cres.getPatientPrescriptions(patient);
        assertEquals(expResult, result.get(0).getDrug().getName());
    }

    /**
     * Test of getPrescriptionDetails method, of class ClientResources_IF.
     */
    @Test
    public void testGetPrescriptionDetails() {
        System.out.println("getPrescriptionDetails");
        Patient patient = this.pb.readPatient("123456-789a");
        Prescription prescription = this.cres.getPatientPrescriptions(patient).get(0);
        int expResult = prescription.getDiagnoseID();
        Prescription result = this.cres.getPrescriptionDetails(prescription);
        assertEquals(expResult, result.getDiagnose().getId());
    }

    /**
     * Test of getEmployees method, of class ClientResources_IF.
     */
    @Test
    public void testGetEmployees() {
        System.out.println("getEmployees");
        int expResult = 4;
        List<Employee> result = this.cres.getEmployees();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getUsers method, of class ClientResources_IF.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        ClientResources_IF instance = new ClientResources_IFImpl();
        List<User_IF> expResult = null;
        List<User_IF> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserPriviledges method, of class ClientResources_IF.
     */
    @Test
    public void testSetUserPriviledges() {
        System.out.println("setUserPriviledges");
        User_IF user = null;
        ClientResources_IF instance = new ClientResources_IFImpl();
        instance.setUserPriviledges(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lockUser method, of class ClientResources_IF.
     */
    @Test
    public void testLockUser() {
        System.out.println("lockUser");
        User_IF user = null;
        ClientResources_IF instance = new ClientResources_IFImpl();
        instance.lockUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewPrescription method, of class ClientResources_IF.
     */
    @Test
    public void testAddNewPrescription() {
        System.out.println("addNewPrescription");
        User_IF user = null;
        ClientResources_IF instance = new ClientResources_IFImpl();
        Prescription expResult = null;
        Prescription result = instance.addNewPrescription(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of savePrescription method, of class ClientResources_IF.
     */
    @Test
    public void testSavePrescription() {
        System.out.println("savePrescription");
        Prescription prescription = null;
        ClientResources_IF instance = new ClientResources_IFImpl();
        boolean expResult = false;
        boolean result = instance.savePrescription(prescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrescriptionsByDoctor method, of class ClientResources_IF.
     */
    @Test
    public void testGetPrescriptionsByDoctor() {
        System.out.println("getPrescriptionsByDoctor");
        User_IF user = null;
        ClientResources_IF instance = new ClientResources_IFImpl();
        List<Prescription> expResult = null;
        List<Prescription> result = instance.getPrescriptionsByDoctor(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ClientResources_IFImpl implements ClientResources_IF {

        public List<Patient> getPatients() {
            return null;
        }

        public Patient getPatientDetails(Patient patient) {
            return null;
        }

        public List<Diagnose> getPatientDiagnoses(Patient patient) {
            return null;
        }

        public Diagnose getDiagnoseDetails(Diagnose diagnose) {
            return null;
        }

        public List<Prescription> getPatientPrescriptions(Patient patient) {
            return null;
        }

        public Prescription getPrescriptionDetails(Prescription prescription) {
            return null;
        }

        public List<Employee> getEmployees() {
            return null;
        }

        public Employee getEmployeeDetails(Employee employee) {
            return null;
        }

        public List<User_IF> getUsers() {
            return null;
        }

        public User_IF getUserDetails(User_IF user) {
            return null;
        }

        public void setUserPriviledges(User_IF user) {
        }

        public void lockUser(User_IF user) {
        }

        public Prescription addNewPrescription(User_IF user) {
            return null;
        }

        public boolean savePrescription(Prescription prescription) {
            return false;
        }

        public List<Prescription> getPrescriptionsByDoctor(User_IF user) {
            return null;
        }
    }
    
}
