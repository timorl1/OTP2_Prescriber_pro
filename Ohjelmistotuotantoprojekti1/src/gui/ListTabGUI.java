/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author joosiika
 * @param <E> Any object
 */
public class ListTabGUI<E> extends Tab implements ListTabGUI_IF<E> {

    @FXML
    private ListView<E> listView;
    
    @FXML
    private Tab tab;
    
    private final String title;
    private FXMLLoader loader;
    
    private ObservableList<E> list;
    
    public ListTabGUI(String title) {
        this.title = title;
        try {
            loader = new FXMLLoader(getClass().getResource("ListTab.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            this.tab.setText(this.title);
        } catch (IOException ex) {
            // handle exception
        }
    }
    
    @Override
    public Tab getTab() {
        return this.tab;
    }

    @Override
    public ListView getListView() {
        return this.listView;
    }
    
    @Override
    public void setList(List<E> list) {
        this.list = FXCollections.observableArrayList(list);
        this.listView.setItems(this.list);
    }

    @Override
    public E getSelection() {
        return this.listView.getSelectionModel().getSelectedItem();
    }
}
