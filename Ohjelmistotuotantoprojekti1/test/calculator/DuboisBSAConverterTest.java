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
public class DuboisBSAConverterTest {
    
    public DuboisBSAConverterTest() {
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
     * Test of convert method, of class DuboisBSAConverter.
     */
    @Test
    public void testConvert() {
        System.out.println("convert");
        double weight = 80.0;
        double height = 180.0;
        int factor = 1;
        DuboisBSAConverter instance = new DuboisBSAConverter();
        double expResult = 1.997;
        double result = instance.convert(weight, height, factor);
        assertEquals(expResult, result, 0.001);
    }
    
}
