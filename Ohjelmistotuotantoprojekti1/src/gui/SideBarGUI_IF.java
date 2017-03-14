package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author joosiika
 */
public interface SideBarGUI_IF {
    public abstract void addView(TitledPane pane);
    public abstract VBox getVbox();
    public abstract TextField getSearchField();
    public abstract Button getMessageButton();
}
