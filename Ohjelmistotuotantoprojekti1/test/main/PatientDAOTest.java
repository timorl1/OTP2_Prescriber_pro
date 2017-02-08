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
    Patient [] test = new Patient[1];
    
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
        test [0] = new Patient("120635-124s","Maija","Meikäläinen","Female",165.0,55.0);
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
        String expResult = "Female";
        String result = instance.readPatient(SSN).getGender();
        assertEquals(expResult, result);
        }

    /**
     * Test of readPatients method, of class PatientDAO.
     */
    @Test
    public void testReadPatients() throws Exception {
        System.out.println("readPatients");
        PatientDAO instance = new PatientDAO();
        Patient[] expResult = test;
        Patient[] result = instance.readPatients();
        assertArrayEquals(expResult, result);
    }
    
}
