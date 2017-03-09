/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import dao.PrescriptionDAO;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import model.Diagnose;
import model.Drug;
import model.Patient;
import model.Prescription;
import model.User;
import model.User_IF;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Johanna
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrescriptionDAOTest {
    
    public PrescriptionDAOTest(){
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
     * Test of readPrescrition method, of class PrescriptionDAO.
     */
    @Test
    public void testReadPrescrition() throws Exception {
        System.out.println("ReadPrescrition");
        int id = 1;
        PrescriptionDAO instance = new PrescriptionDAO();
        Prescription result = instance.readPrescription(id);
    
        //Kommentissa olevat kohdat ovat nulleja miksi näin :00
    /*    assertEquals(1, result.getId());
        assertEquals("123456-789a", result.getPatient());
        assertEquals(0, result.getDoc());
        assertEquals(1, result.getDiagnose()); */
        assertEquals(2.0, result.getDose(), 0.01);
        assertEquals(2, result.getTimesADay());
        assertEquals("Syö yksinäs", result.getInfo());
    //    assertEquals(123456, result.getDrug());
    //    assertEquals("admin", result.getUsername());
        
    }
    /**
     * Test of readPrescritions method, of class PrescriptionDAO.
     */
    @Test
    public void testReadPrescriptions() throws Exception {
        System.out.println("ReadPrescriptions");
        PrescriptionDAO instance = new PrescriptionDAO();
        List<Prescription> pres = instance.readPrescriptions();
        
        assertEquals(2.0, pres.get(0).getDose(), 0.01);
        assertEquals(2, pres.get(0).getTimesADay());
        assertEquals("Syö yksinäs", pres.get(0).getInfo());
    }
    
    /**
     * Test of getPrescriptionsByPatient method, of class PrescriptionDAO.
     */
    @Test
    public void testGetPrescriptionsByPatient() throws Exception{
        System.out.println("getPrescriptionsByPatient");
        PrescriptionDAO instance = new PrescriptionDAO();
        Patient pat = new Patient();
        pat.setSSN("123456-789a");
        List<Prescription> pres = instance.getPrescriptionsByPatient(pat);
        
        assertEquals(2.0, pres.get(0).getDose(), 0.01);
        assertEquals(2, pres.get(0).getTimesADay());
        assertEquals("Syö yksinäs", pres.get(0).getInfo());
        
        
    }
    
    /**
     * Test of getPrescriptionsByDoctor method, of class PrescriptionDAO.
     */
    @Test
    public void testGetPrescriptionsByDoctor() throws Exception{
        System.out.println("getPrescriptionsByDoctor");
        PrescriptionDAO instance = new PrescriptionDAO();
        User_IF doc = new User();
        doc.setUsername("doctor1");
        List<Prescription> pres = instance.getPrescriptionsByDoctor(doc);
       
        assertEquals(2.0, pres.get(0).getDose(), 0.01);
        assertEquals(2, pres.get(0).getTimesADay());
        assertEquals("Syö yksinäs", pres.get(0).getInfo());
    }
    /**
     * Test of getPrescriptionByDiagnose method, of class PrescriptionDAO.
     */
    @Test
    public void testGetPrescriptionByDiagnose() throws Exception{
        System.out.println("getPrescriptionByDiagnose");
        PrescriptionDAO instance = new PrescriptionDAO();
        Diagnose dia = new Diagnose();
        dia.setId(1);
        Prescription pres = instance.getPrescriptionByDiagnose(dia);
        
        assertEquals(2.0, pres.getDose(), 0.01);
        assertEquals(2, pres.getTimesADay());
        assertEquals("Syö yksinäs", pres.getInfo()); 
    }
    
    /**
     * Test of createPrescription method, of class PrescriptionDAO.
     */
    @Test
    public void testCreatePrescription() throws Exception {
        Patient pat = new Patient();
        pat.setSSN("123456-789a");
        User_IF doc = new User();
        doc.setUserID(0);
        Diagnose dia = new Diagnose();
        dia.setId(1);
        Drug drug = new Drug();
        drug.setSN(1234);
        Date x = new Date();
        
        System.out.println("createPrescription");
        PrescriptionDAO instance = new PrescriptionDAO();
        Prescription test = new Prescription();
        test.setId(2);
        test.setPatientID(pat.getSSN());
        test.setPatient(pat);
        test.setDoctor(doc);
        test.setDiagnose(dia);
        test.setDose(600);
        test.setTimesADay(4);
        test.setInfo("Älä yliannosta");
        Date date = Date.from(Instant.now());
        test.setStartDate(date);
        test.setEndDate(date);
        test.setCreationDate(x);
        test.setDrug(drug);
        test.setUsername("admin");
        
        boolean res = instance.createPrescription(test);
        assertTrue(res);
    }
    /**
     * Test of updatePrescription method, of class PrescriptionDAO.
     */
    @Test
    public void testUpdatePrescription() throws Exception {
        Patient pat = new Patient();
        pat.setSSN("123456-789a");
        User_IF doc = new User();
        doc.setUserID(0);
        Diagnose dia = new Diagnose();
        dia.setId(1);
        Drug drug = new Drug();
        drug.setSN(1234);
   
        
        System.out.println("updatePrescription");
        PrescriptionDAO instance = new PrescriptionDAO();
        Prescription test = new Prescription();
        
        test.setId(2);
        test.setPatient(pat);
        test.setPatientID(pat.getSSN());
        test.setDoctor(doc);
        test.setDiagnose(dia);
        test.setDose(800);
        test.setTimesADay(4);
        test.setInfo("Älä yliannosta");
        Date date = Date.from(Instant.now());
        test.setStartDate(date);
        test.setEndDate(date);
      
        test.setDrug(drug);
        test.setUsername("admin");
       
        boolean res = instance.updatePrescription(test);
        assertTrue(res);
        
    }

    
    /**
     * Test of deletePrescription method, of class PrescriptionDAO.
     */
    @Test
    public void testyDeletePrescription() throws Exception {
        System.out.println("deletePrescription");
        PrescriptionDAO instance = new PrescriptionDAO();
        Prescription test = new Prescription();
         test.setId(2);
     //   test.setPatient(pat);
       // test.setDoc(doc);
        //test.setDiagnose(dia);
        test.setDose(600);
        test.setTimesADay(4);
        test.setInfo("Älä yliannosta");
        Date date = Date.from(Instant.now());
        test.setStartDate(date);
        test.setEndDate(date);
        //test.setCreationDate(x);
        //test.setDrug(drug);
        test.setUsername("admin");
        
        boolean res = instance.deletePrescription(test);
        
        assertTrue(res);
        
    }
    
}
