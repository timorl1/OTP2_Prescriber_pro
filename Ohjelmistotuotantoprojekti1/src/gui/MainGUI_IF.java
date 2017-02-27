package gui;

import model.User;

/**
 *
 * @author joosiika
 */
public interface MainGUI_IF {
    public abstract void loadLogin();
    public abstract void loadSideBar(User user);
    public abstract <T> void loadTabPane(T selection);
}
