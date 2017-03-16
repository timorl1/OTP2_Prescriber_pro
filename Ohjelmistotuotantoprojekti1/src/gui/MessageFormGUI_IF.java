/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Button;
import model.Message;

/**
 *Sets up Message form to GUI
 * @author Timo
 */
public interface MessageFormGUI_IF {

    /**
     *New message that is created if passed to controller
     * @return created message
     */
    public abstract Message getMessage();

    /**
     *Closes message form GUI
     * @return button for eventhandler
     */
    public abstract Button getCancelButton();

    /**
     *Button for sending created message
     * @return button for eventhandler
     */
    public abstract Button getSendButton();
}
