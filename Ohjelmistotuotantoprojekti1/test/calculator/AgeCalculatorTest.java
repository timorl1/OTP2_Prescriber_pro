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
public class AgeCalculatorTest {
    
    public AgeCalculatorTest() {
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
     * Test of calculateAge method, of class AgeCalculator.
     */
    @Test
    public void testCalculateAge1900() {
        System.out.println("calculateAge");
        String SSN = "123455-789a";
        AgeCalculator instance = AgeCalculator.getInstance();
        int expResult = 62;
        int result = instance.calculateAge(SSN);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculateAge method, of class AgeCalculator.
     */
    @Test
    public void testCalculateAge2000() {
        System.out.println("calculateAge");
        String SSN = "123402A789a";
        AgeCalculator instance = AgeCalculator.getInstance();
        int expResult = 15;
        int result = instance.calculateAge(SSN);
        assertEquals(expResult, result);
    }
    
}
