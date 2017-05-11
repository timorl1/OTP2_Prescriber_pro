/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.prescription;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionListCell extends ListCell<Prescription> {
    
    private ResourceBundle text;
    private SimpleDateFormat dateFormatter;

    public PrescriptionListCell(ResourceBundle text) {
        this.text = text;
        this.dateFormatter = new SimpleDateFormat(this.text.getString("basicDateTimeFormat"), this.text.getLocale());
    }

    @Override
    protected void updateItem(Prescription prescription, boolean empty) {
        super.updateItem(prescription, empty);

        if (empty || prescription == null) {
            setText(null);
            setGraphic(null);
        } else {
            GridPane gridPane = new GridPane();
            Label idLabel = new Label();
            Label patientLabel = new Label();
            Label creationDateLabel = new Label();
            Text idValue = new Text();
            Text patientValue = new Text();
            Text creationDateValue = new Text();

            idLabel.setText(text.getString("ID") + ": ");
            idValue.setText(String.valueOf(prescription.getId()));

            patientLabel.setText(text.getString("patient") + ": ");
            patientValue.setText(prescription.getPatient().getFirstName() + " " + prescription.getPatient().getLastName() + " : " + prescription.getPatient().getSSN());

            creationDateLabel.setText(text.getString("creationDate") + ": ");
            creationDateValue.setText(dateFormatter.format(prescription.getCreationDate()));

            gridPane.add(idLabel, 0, 0);
            gridPane.add(idValue, 1, 0);
            gridPane.add(patientLabel, 0, 1);
            gridPane.add(patientValue, 1, 1);
            gridPane.add(creationDateLabel, 0, 2);
            gridPane.add(creationDateValue, 1, 2);
            
            this.setGraphic(gridPane);

        }

    }
}
