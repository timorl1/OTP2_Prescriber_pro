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

    /**
     *
     * @return
     */
    public abstract TitledPane getTitledPane();

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

    /**
     *
     * @param filter
     */
    public abstract void filter(String filter);

    /**
     *
     * @return
     */
    public abstract boolean isExpanded();
}
