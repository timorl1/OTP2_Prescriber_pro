/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.loadui.testfx.Assertions.verifyThat;
import org.loadui.testfx.GuiTest;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 *
 * @author Timo
 */
public class LoginGUITest extends GuiTest{
    
    public LoginGUITest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        return new LoginGUI();
    }

    /**
     * Test of getUsername method, of class LoginGUI.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        TextField username = find("#usernameField");
        click(username).type("tt");
        verifyThat("#usernameField", hasText("tt"));
    }

    /**
     * Test of getPassword method, of class LoginGUI.
     */
    @Test
    public void testGetPassword() {
        /*System.out.println("getPassword");
        TextField password = find("#passwordField");
        click(password).type("test");
        verifyThat("#passwordField", hasText("test"));*/
        fail("The test case is a prototype.");
    }

    /**
     * Test of getButton method, of class LoginGUI.
     */
    @Test
    public void testGetButton() {
        System.out.println("getButton");
        LoginGUI instance = new LoginGUI();
        Button expResult = null;
        Button result = instance.getButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMessage method, of class LoginGUI.
     */
    @Test
    public void testAddMessage() {
        System.out.println("addMessage");
        String message = "";
        LoginGUI instance = new LoginGUI();
        instance.addMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearPasswordField method, of class LoginGUI.
     */
    @Test
    public void testClearPasswordField() {
        System.out.println("clearPasswordField");
        LoginGUI instance = new LoginGUI();
        instance.clearPasswordField();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
