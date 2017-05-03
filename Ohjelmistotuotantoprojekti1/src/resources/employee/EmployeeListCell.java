/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.employee;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class EmployeeListCell extends ListCell<Employee> {
    
    private ResourceBundle text;

    public EmployeeListCell(ResourceBundle text) {
        this.text = text;
    }

    @Override
    protected void updateItem(Employee employee, boolean empty) {
        super.updateItem(employee, empty);

        if (empty || employee == null) {
            setText(null);
            setGraphic(null);
        } else {
            GridPane gridPane = new GridPane();
            Label idLabel = new Label();
            Label firstNameLabel = new Label();
            Label lastNameLabel = new Label();
            Label titleLabel = new Label();
            Text idValue = new Text();
            Text firstNameValue = new Text();
            Text lastNameValue = new Text();
            Text titleValue = new Text();

            idLabel.setText(text.getString("employeeNumber") + ": ");
            idValue.setText(String.valueOf(employee.getUserID()));

            firstNameLabel.setText(text.getString("firstname") + ": ");
            firstNameValue.setText(employee.getFirstName());

            lastNameLabel.setText(text.getString("lastname") + ": ");
            lastNameValue.setText(employee.getLastName());
            
            titleLabel.setText(text.getString("employeeTitle") + ": ");
            titleValue.setText(text.getString(employee.getTitle().toLowerCase()));

            gridPane.add(idLabel, 0, 0);
            gridPane.add(idValue, 1, 0);
            gridPane.add(titleLabel, 0, 1);
            gridPane.add(titleValue, 1, 1);
            gridPane.add(firstNameLabel, 0, 2);
            gridPane.add(firstNameValue, 1, 2);
            gridPane.add(lastNameLabel, 0, 3);
            gridPane.add(lastNameValue, 1, 3);
            
            this.setGraphic(gridPane);

        }

    }
}
