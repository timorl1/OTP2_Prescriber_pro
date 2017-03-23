package gui;

import javafx.scene.control.Button;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface LoginGUI_IF {

    /**
     * Method to get the username from the login view.
     * @return username as a string
     */
    public abstract String getUsername();

    /**
     * Method to get the password from the login view.
     * @return password as a string
     */
    public abstract String getPassword();

    /**
     * Method to get the login button from the login view.
     * @return the button element from the login view
     */
    public abstract Button getButton();

    /**
     * Method to add login failure messages to the login view.
     * @param message the message to be set to the message field
     */
    public abstract void addMessage(String message);
}
