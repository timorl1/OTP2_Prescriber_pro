/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.SideBarListViewController;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import controller.SideBarListViewController_IF;

/**
 * FXML Controller class
 *
 * @author joosiika
 * @param <E>
 */
public class SideBarListViewGUI<E> extends TitledPane implements SideBarListView_IF<E> {

    @FXML
    private ListView<E> listView;
    
    @FXML
    private TitledPane pane;
    
    private E e;
    private final String type;
    private final String title;
    private final MainGUI_IF root;
    private SideBarListViewController_IF sideBarListViewController;
    private FXMLLoader loader;
    
    private ObservableList<E> list;
    
    public SideBarListViewGUI(String type, String title, MainGUI_IF root) {
        this.root = root;
        this.type = type;
        this.title = title;
        this.sideBarListViewController = new SideBarListViewController(this);
        try {
            loader = new FXMLLoader(getClass().getResource("SideBarListView.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            this.pane.setText(this.title);
        } catch (IOException ex) {
            // handle exception
        }
    }
    
    @FXML
    public void showList() {
        if (this.pane.isExpanded()) {
            this.sideBarListViewController.loadList(this.type);
        }
    }
    
    @FXML
    public void showDetails() {
        this.root.loadTabPane(this.listView.getSelectionModel().getSelectedItem());
    }
    
    public void setList(List<E> list) {
        this.list = FXCollections.observableArrayList(list);
        this.listView.setItems(this.list);
    }

    @Override
    public E getElement() {
        return e;
    }

    @Override
    public void setElement(E e) {
        this.e = e;
    }
    
}
