/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Button;
import model.Message;

/**
 *
 * @author Timo
 */
public interface MessageFormGUI_IF {

    /**
     *
     * @return
     */
    public abstract Message getMessage();

    /**
     *
     * @return
     */
    public abstract Button getCancelButton();

    /**
     *
     * @return
     */
    public abstract Button getSendButton();
}
