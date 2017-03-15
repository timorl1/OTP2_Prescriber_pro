/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

/**
 *
 * @author joosiika
 * @param <E>
 */
public interface ListTabGUI_IF<E> {

    /**
     *
     * @return
     */
    public abstract Tab getTab();

    /**
     *
     * @return
     */
    public abstract ListView getListView();

    /**
     *
     * @param list
     */
    public abstract void setList(List<E> list);

    /**
     *
     * @return
     */
    public abstract E getSelection();
}
