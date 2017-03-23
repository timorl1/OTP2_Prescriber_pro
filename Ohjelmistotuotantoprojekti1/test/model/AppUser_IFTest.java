/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class AppUser_IFTest {
    
    public AppUser_IFTest() {
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
     * Test of setUser method, of class AppUser_IF.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String username = "admin";
        AppUser_IF instance = new AppUser();
        instance.setUser(username);
        assertEquals(3, instance.getUserPrivileges());
    }

    /**
     * Test of authenticate method, of class AppUser_IF.
     */
    @Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        String username = "admin";
        String password = "admin";
        AppUser_IF instance = new AppUser();
        instance.setUser(username);
        instance.authenticate(password);
        assertEquals(true, instance.isAuthenticated());
    }

    /**
     * Test of isAuthenticated method, of class AppUser_IF.
     */
    @Test
    public void testIsAuthenticated() {
        System.out.println("isAuthenticated");
        String username = "admin";
        String password = "admin";
        AppUser_IF instance = new AppUser();
        instance.setUser(username);
        instance.authenticate(password);
        boolean expResult = true;
        boolean result = instance.isAuthenticated();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserPriviledges method, of class AppUser_IF.
     */
    @Test
    public void testGetUserPrivileges() {
        System.out.println("getUserPrivileges");
        String username = "admin";
        String password = "admin";
        AppUser_IF instance = new AppUser();
        instance.setUser(username);
        instance.authenticate(password);
        int expResult = 3;
        int result = instance.getUserPrivileges();
        assertEquals(expResult, result);
    }
    
}
