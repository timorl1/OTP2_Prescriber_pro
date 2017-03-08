package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author joosiika
 */
@Entity(name="message")
@Table(name="message")
public class Message {
    
    @Id
    @Column(name="MessageID")
    private int messageID;
    @Column(name="message")
    private String message;
    @Column(name="date")
    private Date date;
    
    @ManyToMany (mappedBy="sentMessages")
    private List<User> sentM = new ArrayList();
    @ManyToMany (mappedBy="receivedMessages")
    private List<User> receivedM = new ArrayList();

    
    public Message(){}
    
    public Message(int id, String message, Date d){
        this.messageID = id;
        this.message = message;
        this.date = d;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message: " + "messageID= " + messageID + ", message= " + message + ", date= " + date ;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public List<User> getReceivedMessages(){
        return receivedM;
    }
    
    public List<User> getSentMessages(){
        return sentM;
    }
    
    public void setSentMessages(List<User> sentM){
        this.sentM = sentM;
    }
    public void setReceivedMessages(List<User> receivedM){
        this.receivedM = receivedM;
    }
    
}
