/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.loadui.testfx.GuiTest;

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

    /**
     * Test of getUsername method, of class LoginGUI.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        LoginGUI instance = new LoginGUI();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class LoginGUI.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        LoginGUI instance = new LoginGUI();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
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

    @Override
    protected Parent getRootNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
