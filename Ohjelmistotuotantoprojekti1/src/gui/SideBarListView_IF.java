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
    public abstract TitledPane getTitledPane();
    public abstract ListView getListView();
    public abstract void setList(List<E> list);
    public abstract E getSelection();
    public abstract boolean isExpanded();
}
