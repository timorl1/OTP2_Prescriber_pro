package model;

/**
 *
 * @author joosiika
 */
public interface AppUser_IF {
    public abstract void setUser(String username);
    public abstract void authenticate(String password);
    public abstract boolean isAuthenticated();
    public abstract int getUserPriviledges();
}
