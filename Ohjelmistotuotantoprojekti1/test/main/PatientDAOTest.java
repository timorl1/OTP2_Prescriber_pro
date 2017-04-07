/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import resources.client.Patient;
import resources.client.PatientDAO;
import java.util.List;
import resources.client.Diagnose;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PatientDAOTest {
    
    public PatientDAOTest() {
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
     * Test of readPatient method, of class PatientDAO.
     */
    @Test
    public void testReadPatient() throws Exception {
        System.out.println("readPatient");
        String SSN = "123456-789a";
        PatientDAO instance = new PatientDAO();
        Patient result = instance.readPatient(SSN);
        assertEquals("123456-789a", result.getSSN());
        assertEquals("Potilas", result.getFirstName());
        assertEquals("Saarinen", result.getLastName());
        assertEquals("Mies", result.getGender());
        assertEquals(160.0, result.getHeight(),0.1);
        assertEquals(65.0, result.getWeight(),0.1); 
        }

    /**
     * Test of readPatients method, of class PatientDAO.
     */
    @Test
    public void testReadPatients() throws Exception {
        System.out.println("readPatients");
        PatientDAO instance = new PatientDAO();
        List<Patient> result = instance.readPatients();
        assertEquals("123456-789a", result.get(0).getSSN());
        assertEquals("Potilas", result.get(0).getFirstName());
        assertEquals("Saarinen", result.get(0).getLastName());
        assertEquals("Mies", result.get(0).getGender());
        assertEquals(160.0, result.get(0).getHeight(),0.1);
        assertEquals(65.0, result.get(0).getWeight(),0.1);        
    }
}
