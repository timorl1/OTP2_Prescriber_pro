/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

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
public class MonstellerBSAConverterTest {
    
    public MonstellerBSAConverterTest() {
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
     * Test of convert method, of class MonstellerBSAConverter.
     */
    @Test
    public void testConvert() {
        System.out.println("convert");
        double weight = 180.0;
        double height = 80.0;
        int factor = 1;
        MonstellerBSAConverter instance = new MonstellerBSAConverter();
        double expResult = 2.0;
        double result = instance.convert(weight, height, factor);
        assertEquals(expResult, result, 0.001);
    }
    
}
