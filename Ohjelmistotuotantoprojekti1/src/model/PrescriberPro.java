package model;

import dao.DatabaseDAO;
import dao.DatabaseDAO_IF;
import dao.DiseaseDAO;
import dao.DiseaseDAO_IF;
import dao.DrugDAO;
import dao.DrugDAO_IF;
import dao.EmployeeDAO;
import dao.EmployeeDAO_IF;
import dao.PatientDAO;
import dao.PatientDAO_IF;
import dao.PrescriptionDAO;
import dao.PrescriptionDAO_IF;
import dao.UserDAO;
import dao.UserDAO_IF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PrescriberPro implements PrescriberPro_IF {
    
    private PatientDAO_IF patientDAO;
    private EmployeeDAO_IF employeeDAO;
    private PrescriptionDAO_IF prescriptionDAO;
    private DrugDAO_IF drugDAO;
    private DiseaseDAO_IF diseaseDAO;
    private UserDAO_IF userDAO;
    private DatabaseDAO_IF databaseDAO;
    
    private Map<String, Patient> patients;
    private Map<Integer, Employee> employees;
    private Map<String, User> users;

    public PrescriberPro() {
        this.patientDAO = new PatientDAO();
        this.employeeDAO = new EmployeeDAO();
        this.prescriptionDAO = new PrescriptionDAO();
        this.drugDAO = new DrugDAO();
        this.diseaseDAO = new DiseaseDAO();
        this.userDAO = new UserDAO();
        this.patientDAO.readPatients().forEach(p -> {
            this.patients.put(p.getSSN(), p);
        });
        this.userDAO.getUsers().forEach(u -> {
            this.users.put(u.getUsername(), u);
        });
        this.employeeDAO.readEmployees().forEach(e -> {
            this.employees.put(e.getUserID(), e);
        });
        
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> list = (ArrayList)this.patients.values();
        return list;
    }

    @Override
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        if (patient.getDiagnoses().isEmpty()) {
            List<Diagnose> diagnoses = this.patientDAO.readPatientDiagnoses(patient);
            diagnoses.forEach(d -> {
                d.setPatient(this.patients.get(d.getPatientId()));
                Doctor doctor = new Doctor();
                Employee employee = this.employees.get(d.getDoctorId());
            });
            return diagnoses;
        }
        return patient.getDiagnoses();
    }

    @Override
    public List<Prescription> getPatientPrescriptions(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prescription> getDoctorPrescriptions(Doctor doctor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getPatientDetails(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getDiagnoseDetails(Diagnose diagnose) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getPrescriptionDetails(Prescription prescription) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> getEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getEmployeeDetails(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getUserDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> getUserMessages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getMessageDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getDatabases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getDatabaseProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prescription createPrescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
