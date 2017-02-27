package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface Authenticator_IF {
    public abstract User authenticateUser(String username, String password);
}
