package gui;

import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * This is an interface for building a custom JavaFX layout element, consisting of an Accordion wrapped in VBox.
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface SideBarGUI_IF {

    /**
     * Method to add a new titled pane element inside the accordion.
     * @param pane the titled pane element to be added to the accordion
     */
    public abstract void addView(TitledPane pane);

    /**
     * Method to get the root of this custom JavaFX element.
     * @return VBox, the root element of this layout
     */
    public abstract VBox getVbox();

    /**
     * Method to get the text field of this custom JavaFX element.
     * @return the text field element on the top of this layout
     */
    public abstract TextField getSearchField();
    
    /**
     * Method to get the VBox element reserved for menu buttons of this custom JavaFX element.
     * @return the VBox element reserved for menu buttons
     */
    public abstract VBox getButtonBox();
}
