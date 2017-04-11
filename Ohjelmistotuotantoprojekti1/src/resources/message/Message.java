package resources.message;

import resources.user.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import resources.user.User;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity(name="message")
@Table(name="message")
public class Message {
    
    private int messageID;
    private String message;
    private Date date;
    private String title;

    
    private User sender;
    private User receiver;
    
    
    public Message(){}
    
    public Message(int id, String message, Date d){
        this.messageID = id;
        this.message = message;
        this.date = d;
        sender = new User();
        receiver = new User();
        
    }
    
    @ManyToOne
    @JoinColumn(name="sender")
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne
    @JoinColumn(name="receiver")
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Id
    @Column(name="MessageID")
    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    @Column(name="message")
    public String getMessage() {
        return message;
    }
    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Lähettäjä: "+sender.getFirstName()+" "+sender.getLastName()+", Vastaanottaja: "+receiver.getFirstName()+
                " "+receiver.getLastName()+", Aihe: "+title+", pvm: "+date;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
