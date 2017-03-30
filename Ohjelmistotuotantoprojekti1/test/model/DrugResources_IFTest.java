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
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DrugResources_IFTest {
    private DrugResources_IF dres;
    
    public DrugResources_IFTest() {
        this.dres = new DrugResources();
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
     * Test of getDrugs method, of class DrugResources_IF.
     */
    @Test
    public void testGetDrugs() {
        System.out.println("getDrugs");
        int expResult = 123456;
        List<Drug> result = this.dres.getDrugs();
        assertEquals(expResult, result.get(0).getSN());
    }
    
}
