/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

/**
 * Generic interface to build a custom JavaFX list view to the tab pane.
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 * @param <E> Any object
 */
public interface ListTabGUI_IF<E> {

    /**
     * Method to get the tab element from this custom element.
     * @return tab, the root element of this custom element
     */
    public abstract Tab getTab();

    /**
     * Method to get the list view element from this custom element.
     * @return listview element inside the tab
     */
    public abstract ListView getListView();

    /**
     * Method to set items to the list view of this custom element.
     * @param list to be shown in the list view
     */
    public abstract void setList(List<E> list);

    /**
     * Method to get the selected item from the list view.
     * @return selected item from the list view
     */
    public abstract E getSelection();
}
