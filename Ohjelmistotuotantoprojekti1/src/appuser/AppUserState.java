/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appuser;

/**
 *
 * @author joosiika
 */
public abstract class AppUserState {
    
    public void changeState(AppUser appUser, AppUserState state) {
        appUser.changeState(state);
    }
    
    public abstract void getMenuComponents();
}
