/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.javafx.application.LauncherImpl;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class AppLauncher2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(ApplicationMain.class, AppPreloader.class, args);
    }
    
}
