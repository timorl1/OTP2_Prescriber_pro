/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
    public ClientResources_IFTest() {
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
        ClientResources_IF instance = new ClientResources();
        int expResult = 2;
        int result = instance.getPatients().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPatientDetails method, of class ClientResources_IF.
     */
    @Test
    public void testGetPatientDetails() {
        System.out.println("getPatientDetails");
        ClientResources_IF instance = new ClientResources();
        Patient patient = instance.getPatients().get(0);
        int expResult = 0;
        Patient result = instance.getPatientDetails(patient);
    }

    /**
     * Test of getPatientDiagnoses method, of class ClientResources_IF.
     */
    @Test
    public void testGetPatientDiagnoses() {
        System.out.println("getPatientDiagnoses");
        Patient patient = null;
        ClientResources_IF instance = new ClientResources();
        List<Diagnose> expResult = null;
        List<Diagnose> result = instance.getPatientDiagnoses(patient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiagnoseDetails method, of class ClientResources_IF.
     */
    @Test
    public void testGetDiagnoseDetails() {
        System.out.println("getDiagnoseDetails");
        Diagnose diagnose = null;
        ClientResources_IF instance = new ClientResources();
        Diagnose expResult = null;
        Diagnose result = instance.getDiagnoseDetails(diagnose);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPatientPrescriptions method, of class ClientResources_IF.
     */
    @Test
    public void testGetPatientPrescriptions() {
        System.out.println("getPatientPrescriptions");
        Patient patient = null;
        ClientResources_IF instance = new ClientResources();
        List<Prescription> expResult = null;
        List<Prescription> result = instance.getPatientPrescriptions(patient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrescriptionDetails method, of class ClientResources_IF.
     */
    @Test
    public void testGetPrescriptionDetails() {
        System.out.println("getPrescriptionDetails");
        Prescription prescription = null;
        ClientResources_IF instance = new ClientResources();
        Prescription expResult = null;
        Prescription result = instance.getPrescriptionDetails(prescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployees method, of class ClientResources_IF.
     */
    @Test
    public void testGetEmployees() {
        System.out.println("getEmployees");
        ClientResources_IF instance = new ClientResources();
        List<Employee> expResult = null;
        List<Employee> result = instance.getEmployees();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeDetails method, of class ClientResources_IF.
     */
    @Test
    public void testGetEmployeeDetails() {
        System.out.println("getEmployeeDetails");
        Employee employee = null;
        ClientResources_IF instance = new ClientResources();
        Employee expResult = null;
        Employee result = instance.getEmployeeDetails(employee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class ClientResources_IF.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        ClientResources_IF instance = new ClientResources();
        List<User> expResult = null;
        //List<User> result = instance.getUsers();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserDetails method, of class ClientResources_IF.
     */
    @Test
    public void testGetUserDetails() {
        System.out.println("getUserDetails");
        User user = null;
        ClientResources_IF instance = new ClientResources();
        User expResult = null;
        //User result = instance.getUserDetails(user);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserPriviledges method, of class ClientResources_IF.
     */
    @Test
    public void testSetUserPriviledges() {
        System.out.println("setUserPriviledges");
        User user = null;
        ClientResources_IF instance = new ClientResources();
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
        User user = null;
        ClientResources_IF instance = new ClientResources();
        instance.lockUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
