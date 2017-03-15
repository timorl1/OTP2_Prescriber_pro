package model;

/**
 *
 * @author joosiika
 */
public interface AppUser_IF {

    /**
     *
     * @param username
     */
    public abstract void setUser(String username);

    /**
     *
     * @param password
     */
    public abstract void authenticate(String password);

    /**
     *
     * @return
     */
    public abstract boolean isAuthenticated();

    /**
     *
     * @return
     */
    public abstract int getUserPrivileges();

    /**
     *
     * @return
     */
    public abstract User_IF getUser();
}
