package clientresources;

import resources.client.Diagnose;
import resources.client.Employee;
import resources.app.Message;
import resources.app.Prescription;
import resources.app.User_IF;
import resources.app.User;
import resources.client.Patient;
import resources.client.EmployeeDAO;
import resources.client.EmployeeDAO_IF;
import resources.app.MessageDAO;
import resources.app.MessageDAO_IF;
import resources.client.PatientDAO;
import resources.client.PatientDAO_IF;
import resources.app.PrescriptionDAO;
import resources.app.PrescriptionDAO_IF;
import resources.app.UserDAO;
import resources.app.UserDAO_IF;
import java.sql.Date;
import java.time.LocalDate;
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
    private MessageDAO_IF messageDAO;
    
    private Map<String, Patient> patients;
    private Map<Integer, Employee> employees;
    private Map<Integer, User_IF> users;
    
    public ClientResources() {
        this.patientDAO = new PatientDAO();
        this.employeeDAO = new EmployeeDAO();
        this.userDAO = new UserDAO();
        this.prescriptionDAO = new PrescriptionDAO();
        this.messageDAO = new MessageDAO();
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
    public Prescription addNewPrescription(User_IF user) {
        Prescription prescription = new Prescription();
        prescription.setDoctor(user);
        prescription.setDoctorID(user.getUserID());
        prescription.setTimesADay(1);
        prescription.setCreationDate(Date.valueOf(LocalDate.now()));
        return prescription;
    }

    @Override
    public boolean savePrescription(Prescription prescription) {
        return this.prescriptionDAO.createPrescription(prescription);
    }
    
     @Override
    public User_IF addNewUser(User_IF user) {
        user = new User();       
        return user;
    }

    @Override
    public boolean saveUser(User_IF user) {
        return this.userDAO.createUser(user);
    } 
    
    @Override
    public boolean saveMessage(Message message){
        return this.messageDAO.createMessage(message);
    }
        
    @Override
    public List<Prescription> getPrescriptionsByDoctor(User_IF user) {
        List<Prescription> prescriptions = this.prescriptionDAO.getPrescriptionsByDoctor(user);
        prescriptions.forEach(this.builder::buildPrescription);
        return prescriptions;
    }

    @Override
    public Message addNewMessage(User_IF user) {
        Message message = new Message();
        message.setSender((User) user);
        message.setDate(Date.valueOf(LocalDate.now()));
        return message;
    }
    
}
