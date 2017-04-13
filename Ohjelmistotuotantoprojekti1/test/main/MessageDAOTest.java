/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import resources.message.MessageDAO;
import resources.user.UserDAO;
import java.util.List;
import resources.message.Message;
import resources.user.User;
import resources.user.User_IF;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class MessageDAOTest {
    private final MessageDAO instance = new MessageDAO();
     public MessageDAOTest(){
    }
    
    @BeforeClass
    public static void setUpClass(){}
    
    @AfterClass
    public static void tearDownClass(){}
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of readMessage method, of class MessageDAO.
     */
    @Test
    public void testReadMessage(){
        System.out.println("readMessage");
        int id = 1;
        
        Message result = instance.readMessage(id);
        assertEquals(id, result.getMessageID());
        assertEquals("Joko toimii", result.getMessage());
        assertEquals("doctor1", result.getSender().getUsername());
        assertEquals("nurse1", result.getReceiver().getUsername());
    }
    
    /**
     * Test of readSentMessages method, of class MessageDAO.
     */
    @Test
    public void testReadSetMessages(){
        System.out.println("readSENTMessages");
        User user = new User();
        user.setUsername("doctor1");
        List<Message> result = instance.readSentMessages(user);
        assertEquals(1, result.get(0).getMessageID());
        assertEquals("Joko toimii", result.get(0).getMessage());
        assertEquals("doctor1", result.get(0).getSender().getUsername());
        assertEquals("nurse1", result.get(0).getReceiver().getUsername());       
    }
    
    /**
     * Test of readReceiverMessages method, of class MessageDAO.
     */
    @Test
    public void testReadReceivedMessages(){
        System.out.println("readReceivedMessages");
        User user = new User();
        user.setUsername("nurse1");
        List<Message> result = instance.readReceivedMessages(user);
        assertEquals(1, result.get(0).getMessageID());
        assertEquals("Joko toimii", result.get(0).getMessage());
        assertEquals("doctor1", result.get(0).getSender().getUsername());
        assertEquals("nurse1", result.get(0).getReceiver().getUsername());   
    }
    
    /**
     * Test of createMessages method, of class MessageDAO.
     */
    @Test
    public void testCreateMessage(){
        System.out.println("createMessage");
        User userS = new User();
        userS.setUsername("nurse1");
        User userR = new User();
        userR.setUsername("doctor1");
        Message message = new Message();
        message.setMessageID(4);
        message.setMessage("moi");
        message.setTitle("testi");
        message.setSender(userS);
        message.setReceiver(userR);
        boolean success = instance.createMessage(message);
        assertTrue(success);
        
    }
    
}
