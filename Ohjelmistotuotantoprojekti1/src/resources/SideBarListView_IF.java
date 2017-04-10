package resources;

import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

/**
 * This is a interface for building custom JavaFX List View wrapped in Titled Pane.
 * Can be used inside an accordion to create an expandable list view.
 * 
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 * @param <E> Any object
 */
public interface SideBarListView_IF<E> {

    /**
     * Method to get the Titled Pane element.
     * @return the root element of this custom JavaFX element
     */
    public abstract TitledPane getTitledPane();

    /**
     * Method to get the List View element.
     * @return the listview of this custom JavaFX element
     */
    public abstract ListView getListView();

    /**
     * Method to set a list collection inside the list view.
     * @param list the list to be displayed in the list view
     */
    public abstract void setList(List<E> list);

    /**
     * Method to get the currently selected item from the list.
     * @return the currently selected item in the list view
     */
    public abstract E getSelection();

    /**
     * Method to filter the list inside the list view.
     * @param filter the string to be used for filtering the list view
     */
    public abstract void filter(String filter);

    /**
     * Method to see if the titled pane is expanded
     * @return true if the titled pane is currently expanded, else returns false
     */
    public abstract boolean isExpanded();
}
