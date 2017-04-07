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
import resources.client.DiagnoseDAO;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DiagnoseDAOTest {
    
    public DiagnoseDAOTest() {
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
     * Test of readPatientDiagnoses method, of class PatientDAO.
     */
    @Test 
    public void testReadPatientDiagnoses() throws Exception {
        System.out.println("readPatientDiagnoses");
        Patient pat = new Patient();
        pat.setSSN("123456-789a");
        DiagnoseDAO instance = new DiagnoseDAO();
        List<Diagnose> result = instance.readPatientDiagnoses(pat);
        
        assertEquals(1, result.get(0).getId());
        assertEquals(1, result.get(0).getDiseaseID());
        assertEquals("Potilaalla havaittu äkillinen hengen menetys", result.get(0).getEpicrisis());
    }
}
