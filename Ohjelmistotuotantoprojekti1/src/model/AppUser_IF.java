package model;

/**
 *
 * @author joosiika, Timo
 * Class gets the current application user
 */
public interface AppUser_IF {

    /**
     *Sets username to object
     * @param username
     */
    public abstract void setUser(String username);

    /**
     *Checks that user enters the correct password and sets the boolean value for isAuthenticated method
     * @param password
     */
    public abstract void authenticate(String password);

    /**
     *Boolean check if user is authenticated
     * @return true if user is authenticated, false if not
     */
    public abstract boolean isAuthenticated();

    /**
     *Gets users privilege level to application
     * @return 0 if user has no privileges, 1 if user is a nurse, 2 if user is doctor, 3 if user is doctor
     */
    public abstract int getUserPrivileges();

    /**
     *Gets applications current user object
     * @return Current application user
     */
    public abstract User_IF getUser();
}
