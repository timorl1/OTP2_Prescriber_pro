package controller;

import gui.LoginGUI_IF;
import gui.MainGUI_IF;
import model.Authenticator;
import model.Authenticator_IF;
import model.User;

/**
 *
 * @author joosiika
 */
public class LoginController implements LoginController_IF{
    
    private LoginGUI_IF loginGUI;
    private MainGUI_IF mainGUI;
    private Authenticator_IF authenticator;
    
    //Constructor takes the LoginGUI and the MainGUI as parameters
    public LoginController(MainGUI_IF mainGUI, LoginGUI_IF LoginGUI) {
        this.mainGUI = mainGUI;
        this.loginGUI = LoginGUI;
        this.authenticator = new Authenticator();
    }

    //Calls the MainGUI's sidebar loader method and passes the returned user authenticated by the Authenticator as a parameter
    //Retrieves the username and password from the LoginGUI to be authenticated by the Authenticator
    @Override
    public void login() {
        this.mainGUI.loadSideBar(authenticator.authenticateUser(this.loginGUI.getUsername(), this.loginGUI.getUsername()));
    }
    
}
