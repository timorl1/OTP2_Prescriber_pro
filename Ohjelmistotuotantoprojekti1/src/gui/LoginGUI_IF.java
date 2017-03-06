package gui;

import javafx.scene.control.Button;

/**
 *
 * @author joosiika
 */
public interface LoginGUI_IF {
    public abstract String getUsername();
    public abstract String getPassword();
    public abstract Button getButton();
    public abstract void addMessage(String message);
}
