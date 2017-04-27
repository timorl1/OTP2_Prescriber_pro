package resources.message;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import javax.persistence.*;
import resources.user.User;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity(name="message")
@Table(name="message")
public class Message extends Observable implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int messageID;
    private String message;
    private Date date;
    private String title;
    private boolean opened;
    
    private User sender;
    private User receiver;
    
    
    public Message(){}
    
    public Message(int id, String message, Date date){
        this.messageID = id;
        this.message = message;
        this.date = date;
        this.sender = new User();
        this.receiver = new User();
        
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
    
    @Column(name="opened")
    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
        this.setChanged();
        this.notifyObservers();
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
