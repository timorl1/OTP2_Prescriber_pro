/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import calculator.CalculatorStrategy;
import calculator.DoseCalculator;
import calculator.DuboisBSAConverter;
import calculator.MonstellerBSAConverter;
import resources.drug.Drug;
import resources.patient.Patient;
import resources.drug.DrugDAO;
import resources.drug.DrugDAO_IF;
import resources.patient.PatientDAO;
import resources.patient.PatientDAO_IF;
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
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DoseCalculatorTest {
    private final DrugDAO_IF db = new DrugDAO();
    private final PatientDAO_IF pb = new PatientDAO();
    private CalculatorStrategy mConv = new MonstellerBSAConverter();
    private CalculatorStrategy dConv = new DuboisBSAConverter();
    DoseCalculator dc;
    Patient patient;
    Drug drug;
    double dose;
    int timesADay;
    int duration;
    
    public DoseCalculatorTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.patient = pb.readPatient("123456-789a");
        this.drug = db.readDrug(123456);
        this.dc = new DoseCalculator();
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
        double expResult = 0.4875;
        double result = dc.getOptimalDose(this.patient, this.drug);
        assertEquals(expResult, result, 0.1);
    }
    
    /**
     * Test of getOptimalDose method with Monsteller formula, of class DoseCalculator.
     */
    @Test
    public void testGetOptimalDoseMonsteller() {
        System.out.println("getOptimalDoseMonsteller");
        dc.changeStrategy(mConv);
        double expResult = 0.4875;
        double result = dc.getOptimalDose(this.patient, this.drug);
        assertEquals(expResult, result, 0.1);
    }
    
    /**
     * Test of getOptimalDose method with Dubois formula, of class DoseCalculator.
     */
    @Test
    public void testGetOptimalDoseDubois() {
        System.out.println("getOptimalDoseDubois");
        dc.changeStrategy(dConv);
        double expResult = 0.4875;
        double result = dc.getOptimalDose(this.patient, this.drug);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getMaxDose method, of class DoseCalculator.
     */
    @Test
    public void testGetMaxDose() {
        System.out.println("getMaxDose");
        double expResult = 1.1375;
        double result = dc.getMaxDose(this.patient, this.drug);
        assertEquals(expResult, result, 0.1);
    }
    
    /**
     * Test of getMaxDose method Monsteller formula, of class DoseCalculator.
     */
    @Test
    public void testGetMaxDoseMonsteller() {
        System.out.println("getMaxDoseMonsteller");
        dc.changeStrategy(mConv);
        double expResult = 1.1375;
        double result = dc.getMaxDose(this.patient, this.drug);
        assertEquals(expResult, result, 0.1);
    }
    
    /**
     * Test of getMaxDose method Dubois formula, of class DoseCalculator.
     */
    @Test
    public void testGetMaxDoseDubois() {
        System.out.println("getMaxDoseDubois");
        dc.changeStrategy(dConv);
        double expResult = 1.1375;
        double result = dc.getMaxDose(this.patient, this.drug);
        assertEquals(expResult, result, 0.1);
    }
    
    /**
     * Test of getCumulativeDose method, of class DoseCalculator_IF.
     */
    @Test
    public void testGetCumulativeDose() {
        System.out.println("getCumulativeDose");
        this.timesADay = 4;
        this.dose = 0.4875;
        this.duration = 10;
        double expResult = 1.4625;
        double result = dc.getCumulativeDose(this.patient, this.drug, this.dose, this.timesADay, this.duration);
        assertEquals(expResult, result, 0.1);
    }
}
