/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import model.User_IF;
import model.Employee;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class UserFormGUI extends Tab implements UserFormGUI_IF  {
    
    @FXML
    private GridPane gridPane;
    @FXML
    private Label employeeLabel;
    @FXML
    private Text employeeField;
    @FXML
    private Label userIDLabel;
    @FXML
    private Text userIDField;
    @FXML
    private Label emailLabel ;
    @FXML
    private Text emailField;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextArea usernameField;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextArea passwordField;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    

    private final SideBarListView_IF<Employee> employeeSelector;
    private User_IF user;
    private FXMLLoader loader;
    
    private int id;
    private final String title;
    private Employee employee;
    private String username;
    private String password;
    private String email;
       
    public UserFormGUI(SideBarListView_IF<Employee> employeeSelector, User_IF user, String title) {
        this.title = title;
        this.employeeSelector = employeeSelector;
        this.user = user;
        this.id = this.user.getUserID();        
        this.employee = employee;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();        
        try {
            loader = new FXMLLoader(getClass().getResource("UserForm.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
          //this.initializeFields(); Mahdolliset update operaatiot!!
            this.initializeBasicListeners();
            if (this.employee == null) {
                this.initializeNewUserListeners(); 
            } 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void initializeFields() {
        this.setText(title);
        
        if (this.id != 0) {
            this.userIDLabel.setText("#" + Integer.toString(this.id));
        }                
        if (this.employee != null) {
            this.employeeField.setText(this.employee.toString());
        }
              
        this.usernameField.setText(this.username);
        this.passwordField.setText(this.password);
        this.emailField.setText(this.email);
      }
    
    private void initializeBasicListeners() {
        this.setText(title);
        this.usernameField.setOnKeyReleased(e -> {
            this.username= this.usernameField.getText();
            this.user.setUsername(this.username);
        });
        
        this.passwordField.setOnKeyReleased(e -> {
            this.password= this.passwordField.getText();
            this.user.setPassword(this.password);
        });
    }
     
    private void initializeNewUserListeners() {
        
        this.employeeSelector.getListView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
            @Override
            public void changed(ObservableValue<? extends Employee> ov, Employee oldValue, Employee newValue) {
                if (newValue != null) {
                    employee = newValue;
                    user.setFirstName(employee.getFirstName());
                    user.setLastName(employee.getLastName());
                    user.setEmail(employee.getEmail());
                    user.setUserID(employee.getUserID());
                    if (employee.getTitle().equals("Hoitaja")){
                        user.setUsertype(1);
                    }else if (employee.getTitle().equals("Lääkäri")){
                        user.setUsertype(2);
                    }else if (employee.getTitle().equals("Admin")){
                        user.setUsertype(3);
                    } else {
                        user.setUsertype(0);}
                    
                    employeeField.setText(employee.getFirstName() + " " + employee.getLastName());
                    userIDField.setText(Integer.toString(employee.getUserID()));
                    emailField.setText(employee.getEmail());                    
                }
              
            }
        }
        ); }
   
    
    @Override
    public Employee getEmployee() {
        return this.employee;
    }
    
    @Override
    public Text getEmployeeField() {
        return this.employeeField;    
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }

    @Override
    public Button getSaveButton() {
        return saveButton;
    }
    
    @Override
    public User_IF getUser() {
        return this.user;
    }
    
    


}
