package gui;

import javafx.scene.control.Button;

/**
 *
 * @author joosiika
 */
public interface LoginGUI_IF {

    /**
     *
     * @return
     */
    public abstract String getUsername();

    /**
     *
     * @return
     */
    public abstract String getPassword();

    /**
     *
     * @return
     */
    public abstract Button getButton();

    /**
     *
     * @param message
     */
    public abstract void addMessage(String message);
}
