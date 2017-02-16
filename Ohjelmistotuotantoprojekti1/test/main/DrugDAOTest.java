/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.DrugDAO;
import java.util.List;

import model.Drug;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Johanna
 */
public class DrugDAOTest {
    
    public DrugDAOTest(){
    }
    
    @BeforeClass
    public static void setUpClass(){}
    
    @AfterClass
    public static void tearDownClass(){}
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of readDrug method, of class DrugDAO.
     */
    @Test
    public void testReadDrug() throws Exception {
        System.out.println("Read drug");
        int SN = 123456;
        DrugDAO instance = new DrugDAO();
        Drug result = instance.readDrug(SN);
        assertEquals(123456, result.getSN());
        assertEquals("Burana", result.getName());
        assertEquals(400.0, result.getRecommendedDose(),0.1);
        assertEquals(1000.0, result.getMaxDose(),0.1);
        assertEquals("mg", result.getUnit());
    } 
    
     /**
     * Test of readDrugss method, of class DrugDAO.
     */
    @Test
    public void testReadPatients() throws Exception {
        System.out.println("readDrugs");
        DrugDAO instance = new DrugDAO();
        List<Drug> result = instance.readDrugs();
        assertEquals(123465, result.get(1).getSN());
        assertEquals("Duact", result.get(1).getName());
        assertEquals(60.0, result.get(1).getRecommendedDose(),0.1);
        assertEquals(200.0, result.get(1).getMaxDose(),0.1);
        assertEquals("mg", result.get(1).getUnit());
        
       
    } 
    
}
