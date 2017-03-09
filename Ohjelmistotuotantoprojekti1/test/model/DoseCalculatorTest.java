/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DrugDAO;
import dao.DrugDAO_IF;
import java.util.ArrayList;
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
public class DoseCalculatorTest {
    DrugDAO_IF db;
    
    public DoseCalculatorTest() {
        this.db = new DrugDAO();
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
     * Test of getOptimalDose method, of class DoseCalculator.
     */
    @Test
    public void testGetOptimalDose() {
        System.out.println("getOptimalDose");
        Patient patient = new Patient();
        patient.setSSN("123456-789a");
        patient.setFirstName("Potilas");
        patient.setLastName("Saarinen");
        patient.setGender("Mies");
        patient.setHeight(180.5);
        patient.setWeight(85.2);
        Drug drug = db.readDrug(123456);
        DoseCalculator_IF instance = new DoseCalculator();
        double expResult = 0.639;
        double result = instance.getOptimalDose(patient, drug);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getMaxDose method, of class DoseCalculator.
     */
    @Test
    public void testGetMaxDose() {
        System.out.println("getOptimalDose");
        Patient patient = new Patient();
        patient.setSSN("123456-789a");
        patient.setFirstName("Potilas");
        patient.setLastName("Saarinen");
        patient.setGender("Mies");
        patient.setHeight(180.5);
        patient.setWeight(85.2);
        Drug drug = db.readDrug(123456);
        DoseCalculator_IF instance = new DoseCalculator();
        double expResult = 1.491;
        double result = instance.getMaxDose(patient, drug);
        assertEquals(expResult, result, 0.1);
    }
    
}
