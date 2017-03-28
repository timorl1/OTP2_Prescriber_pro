package model;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 * Class gets the current application user
 */
public interface AppUser_IF {

    /**
     *Sets username to object
     * @param username username of current user
     * @return true if username if found in database, false if not
     */
    public abstract boolean setUser(String username);

    /**
     *Checks that user enters the correct password and sets the boolean value for isAuthenticated method
     * @param password password of current user
     */
    public abstract void authenticate(String password);
    
    /**
     * Sets authenticated to false when logging out
     * @param authenticated status of the current user
     */
    public abstract void setAuthenticate(boolean authenticated);
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
