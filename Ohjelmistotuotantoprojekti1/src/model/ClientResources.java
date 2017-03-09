package model;

import dao.EmployeeDAO;
import dao.EmployeeDAO_IF;
import dao.PatientDAO;
import dao.PatientDAO_IF;
import dao.PrescriptionDAO;
import dao.PrescriptionDAO_IF;
import dao.UserDAO;
import dao.UserDAO_IF;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.converter.DoubleStringConverter;

/**
 *
 * @author joosiika, Timo
 * This class collects all the data for application
 */
public class ClientResources implements ClientResources_IF {
    private PatientDAO_IF patientDAO;
    private EmployeeDAO_IF employeeDAO;
    private UserDAO_IF userDAO;
    private PrescriptionDAO_IF prescriptionDAO;
    private DependencyBuilder_IF builder;
    //private PatientBuilder_IF patientBuilder;
    //private DiagnoseBuilder_IF diagnoseBuilder;
    //private PrescriptionBuilder_IF prescriptionBuilder;
    private DoseCalculator_IF doseCalculator;
    private DoubleStringConverter dsc;
    
    private Map<String, Patient> patients;
    private Map<Integer, Employee> employees;
    private Map<Integer, User_IF> users;
    
    public ClientResources() {
        this.patientDAO = new PatientDAO();
        this.employeeDAO = new EmployeeDAO();
        this.userDAO = new UserDAO();
        this.prescriptionDAO = new PrescriptionDAO();
        //this.patientBuilder = new PatientBuilder();
        //this.diagnoseBuilder = new DiagnoseBuilder();
        //this.prescriptionBuilder = new PrescriptionBuilder();
        this.doseCalculator = new DoseCalculator();
        this.dsc = new DoubleStringConverter();
        this.patients = new HashMap();
        this.patientDAO.readPatients().forEach((patient) -> {
            this.patients.put(patient.getSSN(), patient);
        });
        this.employees = new HashMap();
        this.employeeDAO.readEmployees().forEach((employee) -> {
            this.employees.put(employee.getUserID(), employee);
        });
        this.users = new HashMap();
        this.userDAO.getUsers().forEach(u -> {
            this.users.put(u.getUserID(), u);
        });
        this.builder = new DependencyBuilder(this.patients, this.users);
    }

    @Override
    public List<Patient> getPatients() {
        return patientDAO.readPatients();
    }
    
    @Override
    public Patient getPatientDetails(Patient patient) {
        return this.builder.buildPatient(patient);
    }
    
    @Override
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        List<Diagnose> diagnoses = this.builder.buildPatient(patient).getDiagnoses();
        diagnoses.forEach(this.builder::buildDiagnose);
        return diagnoses;
    }

    @Override
    public List<Prescription> getPatientPrescriptions(Patient patient) {
        List<Prescription> prescriptions = this.builder.buildPatient(patient).getPrescriptions();
        prescriptions.forEach(this.builder::buildPrescription);
        return prescriptions;
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeDAO.readEmployees();
    }

    @Override
    public Employee getEmployeeDetails(Employee employee) {
        return employee;
    }
    
    @Override
    public List<User_IF> getUsers() {
        return this.userDAO.getUsers();
    }

    @Override
    public User_IF getUserDetails(User_IF user) {
        return user;
    }

    @Override
    public Prescription getPrescriptionDetails(Prescription prescription) {
        return this.builder.buildPrescription(prescription);
    }

    @Override
    public Diagnose getDiagnoseDetails(Diagnose diagnose) {
        return this.builder.buildDiagnose(diagnose);
    }

    @Override
    public void setUserPriviledges(User_IF user) {
        if (this.employees.get(user.getUserID()).getTitle().equalsIgnoreCase("Hoitaja")) {
            user.setUsertype(1);
        } else if (this.employees.get(user.getUserID()).getTitle().equalsIgnoreCase("Lääkäri")) {
            user.setUsertype(2);
        } else if (this.employees.get(user.getUserID()).getTitle().equalsIgnoreCase("Ylläpitäjä")) {
            user.setUsertype(3);
        }
        this.userDAO.updateUser(user);
    }

    @Override
    public void lockUser(User_IF user) {
        user.setUsertype(0);
        this.userDAO.updateUser(user);
    }

    @Override
    public DoseStatus evaluateDose(Patient patient, Drug drug, double dose) {
        if (dose == 0 || patient == null || drug == null) {
            return DoseStatus.NULL;
        }
        if (dose < this.doseCalculator.getOptimalDose(patient, drug)/2) {
            return DoseStatus.INSUFFICIENT;
        }
        else if (dose >= this.doseCalculator.getOptimalDose(patient, drug)/2 && dose < this.doseCalculator.getMaxDose(patient, drug)/2) {
            return DoseStatus.OPTIMAL;
        }
        else if (dose >= this.doseCalculator.getMaxDose(patient, drug)/2 && dose < this.doseCalculator.getMaxDose(patient, drug)*0.75) {
            return DoseStatus.OVER_OPTIMAL;
        }
        else if (dose >= this.doseCalculator.getMaxDose(patient, drug)*0.75 && dose <= this.doseCalculator.getMaxDose(patient, drug)) {
            return DoseStatus.RISK_LIMIT;
        }
        else {
            return DoseStatus.OVERDOSE;
        }
        
    }

    @Override
    public Prescription addNewPrescription(User_IF user) {
        Prescription prescription = new Prescription();
        prescription.setDoctor(user);
        prescription.setDoctorID(user.getUserID());
        prescription.setCreationDate(Date.valueOf(LocalDate.now()));
        return prescription;
    }

    @Override
    public void savePrescription(Prescription prescription) {
        this.prescriptionDAO.updatePrescription(prescription);
    }
        
    @Override
    public List<Prescription> getPrescriptionsByDoctor(User_IF user) {
        List<Prescription> prescriptions = this.prescriptionDAO.getPrescriptionsByDoctor(user);
        prescriptions.forEach(this.builder::buildPrescription);
        return prescriptions;
    }
    
}
