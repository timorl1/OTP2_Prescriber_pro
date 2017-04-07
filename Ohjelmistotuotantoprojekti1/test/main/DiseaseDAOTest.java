/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import resources.disease.DiseaseDAO;
import resources.disease.Disease;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DiseaseDAOTest {
    
    public DiseaseDAOTest(){}
    
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
     * Test of readDisease method, of class DiseaseDAO.
     */
    @Test
    public void testReadDisease(){
        System.out.println("readDisease");
        int id = 1;
        DiseaseDAO instance = new DiseaseDAO();
        Disease result = instance.getDisease(id);
        
        assertEquals(1, result.getId());
        assertEquals("Äkkikuolema", result.getDiseaseName());
        assertEquals("Saattaa potilas kupsahtaa", result.getDiseaseDesc());
       
    }
    
    
}
