/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.drug;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author joosiika
 */
public class DrugListCell extends ListCell<Drug> {
    
    private ResourceBundle text;

    public DrugListCell(ResourceBundle text) {
        this.text = text;
    }

    @Override
    protected void updateItem(Drug drug, boolean empty) {
        super.updateItem(drug, empty);

        if (empty || drug == null) {
            setText(null);
            setGraphic(null);
        } else {
            GridPane gridPane = new GridPane();
            Label snLabel = new Label();
            Label nameLabel = new Label();
            Label mainAgentLabel = new Label();
            Text snValue = new Text();
            Text nameValue = new Text();
            Text mainAgentValue = new Text();

            snLabel.setText(text.getString("productNumber") + ": ");
            snValue.setText(String.valueOf(drug.getSN()));

            nameLabel.setText(text.getString("name") + ": ");
            nameValue.setText(drug.getName());

            mainAgentLabel.setText(text.getString("activeAgent") + ": ");
            mainAgentValue.setText(drug.getDrugActiveAgents().get(0).getActiveAgent().getName() + " " + drug.getDrugActiveAgents().get(0).getConcentration() + "mg");

            gridPane.add(snLabel, 0, 0);
            gridPane.add(snValue, 1, 0);
            gridPane.add(nameLabel, 0, 1);
            gridPane.add(nameValue, 1, 1);
            gridPane.add(mainAgentLabel, 0, 2);
            gridPane.add(mainAgentValue, 1, 2);
            
            this.setGraphic(gridPane);

        }

    }
}
