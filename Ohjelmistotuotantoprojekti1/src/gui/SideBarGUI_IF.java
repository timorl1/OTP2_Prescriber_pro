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

    /**
     *
     * @param pane
     */
    public abstract void addView(TitledPane pane);

    /**
     *
     * @return
     */
    public abstract VBox getVbox();

    /**
     *
     * @return
     */
    public abstract TextField getSearchField();

    /**
     *
     * @return
     */
    public abstract Button getMessageButton();
}
