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
    public abstract Tab getTab();
    public abstract ListView getListView();
    public abstract void setList(List<E> list);
}
