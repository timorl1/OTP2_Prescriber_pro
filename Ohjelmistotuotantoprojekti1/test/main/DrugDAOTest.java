/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import resources.drug.DrugDAO;
import java.util.List;

import resources.drug.Drug;
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
public class DrugDAOTest {
    private final DrugDAO instance = new DrugDAO();
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
        
        Drug result = instance.readDrug(SN);
        assertEquals(123456, result.getSN());
        assertEquals("Burana", result.getName());
        assertEquals(3.0, result.getDrugActiveAgents().get(0).getActiveAgent().getRecommendedDose(),0.1);
        assertEquals(7.0, result.getDrugActiveAgents().get(0).getActiveAgent().getMaxDose(),0.1);
        assertEquals(4.0, result.getDrugActiveAgents().get(0).getActiveAgent().getHalfTime(),0.1);
        assertEquals("kpl", result.getUnit());
    } 
    
     /**
     * Test of readDrugss method, of class DrugDAO.
     */
    @Test
    public void testReadDrugs() throws Exception {
        System.out.println("readDrugs");
        DrugDAO instance = new DrugDAO();
        List<Drug> result = instance.readDrugs();
        assertEquals(4455, result.get(0).getSN());
        assertEquals("Faverin", result.get(0).getName());
        assertEquals(1.0, result.get(0).getDrugActiveAgents().get(0).getActiveAgent().getRecommendedDose(),0.1);
        assertEquals(2.0, result.get(0).getDrugActiveAgents().get(0).getActiveAgent().getMaxDose(),0.1);
        assertEquals(10.0, result.get(0).getDrugActiveAgents().get(0).getActiveAgent().getHalfTime(),0.1);
        assertEquals("kpl", result.get(0).getUnit());
        
       
    } 
    
}
