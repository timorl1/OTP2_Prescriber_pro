/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import calculator.DoseStatus;
import resources.drug.Drug;
import resources.prescription.PrescriptionEditor;
import resources.prescription.Prescription;
import resources.patient.Patient;
import resources.disease.DiseaseDAO;
import resources.disease.DiseaseDAO_IF;
import resources.drug.DrugDAO;
import resources.drug.DrugDAO_IF;
import resources.patient.PatientDAO;
import resources.patient.PatientDAO_IF;
import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import resources.diagnose.DiagnoseDAO;
import resources.diagnose.DiagnoseDAO_IF;
import resources.prescription.PrescriptionEditor_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionMaker_IFTest {
    DrugDAO_IF db;
    PatientDAO_IF pb;
    DiagnoseDAO_IF dd;
    DiseaseDAO_IF disb;
    PrescriptionEditor_IF pm;
    Patient patient;
    Drug drug;
    Prescription prescription;
    
    public PrescriptionMaker_IFTest() {
        this.db = new DrugDAO();
        this.pb = new PatientDAO();
        this.dd = new DiagnoseDAO();
        this.disb = new DiseaseDAO();
        this.pm = new PrescriptionEditor();
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
        this.patient.setDiagnoses(this.dd.readPatientDiagnoses(this.patient));
        this.patient.getDiagnoses().forEach(d -> d.setDisease(this.disb.getDisease(d.getDiseaseID())));
        this.drug = db.readDrug(123456);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of evaluateDose method, of class PrescriptionEditor_IF.
     */
    @Test
    public void testEvaluateDoseOverdose() {
        System.out.println("evaluateDoseOverdose");
        this.prescription = new Prescription();
        this.prescription.setPatient(this.patient);
        this.prescription.setDrug(this.drug);
        this.prescription.setDose(1.8);
        this.prescription.setTimesADay(0);
        this.prescription.setStartDate(null);
        this.prescription.setEndDate(null);
        this.pm.editPrescription(this.prescription);
        DoseStatus expResult = DoseStatus.OVERDOSE;
        DoseStatus result = pm.evaluateDose();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluateDose method, of class PrescriptionEditor_IF.
     */
    @Test
    public void testEvaluateDoseCumulativeOverdose() {
        System.out.println("evaluateDoseCumulativeOverdose");
        this.prescription = new Prescription();
        this.prescription.setPatient(this.patient);
        this.prescription.setDrug(this.drug);
        this.prescription.setDose(0.4875);
        this.prescription.setTimesADay(4);
        this.prescription.setStartDate(null);
        this.prescription.setEndDate(null);
        this.pm.editPrescription(this.prescription);
        DoseStatus expResult = DoseStatus.CUMULATIVE_OVERDOSE;
        DoseStatus result = pm.evaluateDose();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluateDose method, of class PrescriptionEditor_IF.
     */
    @Test
    public void testEvaluateDoseDuration() {
        System.out.println("evaluateDoseDoseDuration");
        this.prescription = new Prescription();
        this.prescription.setPatient(this.patient);
        this.prescription.setDrug(this.drug);
        this.prescription.setDose(0.38);
        this.prescription.setTimesADay(4);
        this.prescription.setStartDate(Date.from(Instant.parse("2017-03-01T00:00:00.00Z")));
        this.prescription.setEndDate(Date.from(Instant.parse("2017-03-30T00:00:00.00Z")));
        this.pm.editPrescription(this.prescription);
        DoseStatus expResult = DoseStatus.CUMULATIVE_OVERDOSE;
        DoseStatus result = pm.evaluateDose();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOptimalDose method, of class PrescriptionEditor_IF.
     */
    @Test
    public void testGetOptimalDose() {
        System.out.println("getOptimalDose");
        this.prescription = new Prescription();
        this.prescription.setPatient(this.patient);
        this.prescription.setDrug(this.drug);
        this.pm.editPrescription(this.prescription);
        double expResult = 0.4875;
        double result = pm.getOptimalDose();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of isAllergic method, of class PrescriptionEditor_IF.
     */
    @Test
    public void testIsAllergic() {
        System.out.println("isAllergic");
        this.prescription = new Prescription();
        this.prescription.setPatient(this.patient);
        this.prescription.setDrug(this.drug);
        this.pm.editPrescription(this.prescription);
        String expResult = "Kala";
        List<String> result = pm.isAllergic();
        String resString = result.get(0);
        assertEquals(expResult, resString);
    }
    
    @Test
    public void testCrossReaction() {
        System.out.println("crossReaction");
        this.prescription = new Prescription();
        this.prescription.setPatient(this.patient);
        this.drug = db.readDrug(4455);
        this.prescription.setDrug(this.drug);
        this.pm.editPrescription(this.prescription);
        String expResult = "Faverin";
        HashMap<String, String> result = pm.crossReaction();
        String resString = result.get("Burana");
        assertEquals(expResult, resString);
    }
}
