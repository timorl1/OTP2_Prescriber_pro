package clientresources;

import resources.diagnose.Diagnose;
import resources.employee.Employee;
import resources.prescription.Prescription;
import resources.user.User_IF;
import resources.user.User;
import resources.patient.Patient;
import resources.employee.EmployeeDAO;
import resources.employee.EmployeeDAO_IF;
import resources.patient.PatientDAO;
import resources.patient.PatientDAO_IF;
import resources.prescription.PrescriptionDAO;
import resources.prescription.PrescriptionDAO_IF;
import resources.user.UserDAO;
import resources.user.UserDAO_IF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.converter.DoubleStringConverter;
import tools.DependencyBuilder;
import tools.DependencyBuilder_IF;
import calculator.DoseCalculator;
import calculator.DoseCalculator_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 * This class collects all the data for application
 */
public class ClientResources implements ClientResources_IF {
    private PatientDAO_IF patientDAO;
    private EmployeeDAO_IF employeeDAO;
    private UserDAO_IF userDAO;
    private PrescriptionDAO_IF prescriptionDAO;
    private DependencyBuilder_IF builder;
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
        this.doseCalculator = new DoseCalculator();
        this.dsc = new DoubleStringConverter();
        this.patients = new HashMap();
        this.users = new HashMap();
        this.employees = new HashMap();
        this.patientDAO.readPatients().forEach(p -> {
            this.patients.put(p.getSSN(), p);
        });
        this.userDAO.getUsers().forEach(u -> {
            this.users.put(u.getUserID(), u);
        });
        this.employeeDAO.readEmployees().forEach(e -> {
            this.employees.put(e.getUserID(), e);
        });
        this.builder = new DependencyBuilder(this.patients, this.users);
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> patientList = new ArrayList();
        patientList.addAll(this.patients.values());
        patientList.forEach(p -> this.getPatientDiagnoses(p));
        return patientList;
    }
    
    @Override
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        List<Diagnose> diagnoses = this.builder.buildPatient(patient).getDiagnoses();
        diagnoses.forEach(this.builder::buildDiagnose);
        return diagnoses;
    }

    @Override
    public List<Prescription> getPatientPrescriptions(Patient patient) {
        List<Prescription> prescriptions = this.prescriptionDAO.getPrescriptionsByPatient(patient);
        prescriptions.forEach(this.builder::buildPrescription);
        return prescriptions;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList();
        employeeList.addAll(this.employees.values());
        return employeeList;
    }

    @Override
    public Employee getEmployeeDetails(Employee employee) {
        return employee;
    }
    
    @Override
    public List<User_IF> getUsers() {
        List<User_IF> userList = new ArrayList();
        userList.addAll(this.users.values());
        return userList;
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
    public User_IF createNewUser() {
        return new User();
    }

    @Override
    public boolean saveUser(User_IF user) {
        if(user.getPassword() == null ||
                user.getPassword().isEmpty() ||
                user.getUsername() == null ||
                user.getUsername().isEmpty()||
                user.getEmail() == null ||
                user.getEmail().isEmpty()||
                user.getFirstName() == null ||
                user.getFirstName().isEmpty()||
                user.getLastName() == null ||
                user.getLastName().isEmpty()){            
            return false;
        }else{
            return this.userDAO.createUser(user);
        }
    }
        
    @Override
    public List<Prescription> getPrescriptionsByDoctor(User_IF user) {
        List<Prescription> prescriptions = this.prescriptionDAO.getPrescriptionsByDoctor(user);
        prescriptions.forEach(this.builder::buildPrescription);
        return prescriptions;
    }
    
}
