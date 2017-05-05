/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.patient;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PatientListCell extends ListCell<Patient> {
    
    private ResourceBundle text;

    public PatientListCell(ResourceBundle text) {
        this.text = text;
    }

    @Override
    protected void updateItem(Patient patient, boolean empty) {
        super.updateItem(patient, empty);

        if (empty || patient == null) {
            setText(null);
            setGraphic(null);
        } else {
            GridPane gridPane = new GridPane();
            Label ssnLabel = new Label();
            Label firstNameLabel = new Label();
            Label lastNameLabel = new Label();
            Text ssnValue = new Text();
            Text firstNameValue = new Text();
            Text lastNameValue = new Text();

            ssnLabel.setText(text.getString("SSN") + ": ");
            ssnValue.setText(patient.getSSN());

            firstNameLabel.setText(text.getString("firstname") + ": ");
            firstNameValue.setText(patient.getFirstName());

            lastNameLabel.setText(text.getString("lastname") + ": ");
            lastNameValue.setText(patient.getLastName());

            gridPane.add(ssnLabel, 0, 0);
            gridPane.add(ssnValue, 1, 0);
            gridPane.add(firstNameLabel, 0, 1);
            gridPane.add(firstNameValue, 1, 1);
            gridPane.add(lastNameLabel, 0, 2);
            gridPane.add(lastNameValue, 1, 2);
            
            this.setGraphic(gridPane);

        }

    }
}
