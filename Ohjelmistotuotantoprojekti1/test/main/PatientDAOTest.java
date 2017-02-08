/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Timo
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
        String SSN = "120635-124s";
        PatientDAO instance = new PatientDAO();
        Patient result = instance.readPatient(SSN);
        assertEquals("120635-124s", result.getSSN());
        assertEquals("Maija", result.getFirstName());
        assertEquals("Meikäläinen", result.getLastName());
        assertEquals("Female", result.getGender());
        assertEquals(165.0, result.getHeight(),0.1);
        assertEquals(55.0, result.getWeight(),0.1); 
        }

    /**
     * Test of readPatients method, of class PatientDAO.
     */
    @Test
    public void testReadPatients() throws Exception {
        System.out.println("readPatients");
        PatientDAO instance = new PatientDAO();
        Patient[] result = instance.readPatients();
        assertEquals("120635-124s", result[0].getSSN());
        assertEquals("Maija", result[0].getFirstName());
        assertEquals("Meikäläinen", result[0].getLastName());
        assertEquals("Female", result[0].getGender());
        assertEquals(165.0, result[0].getHeight(),0.1);
        assertEquals(55.0, result[0].getWeight(),0.1);        
    }
    
}
