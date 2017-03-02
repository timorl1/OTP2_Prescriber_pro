package gui;

import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

/**
 *
 * @author joosiika
 * @param <E>
 */
public interface SideBarListView_IF<E> {
    public TitledPane getTitledPane();
    public ListView getListView();
    public void setList(List<E> list);
    public E getSelection();
}
