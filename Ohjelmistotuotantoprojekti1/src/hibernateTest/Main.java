/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateTest;

/**
 *
 * @author Timo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBTestDAO_IF d = new DBTestDAO();
        
        for(DBTest p :d.readAll()){
            System.out.println(p.getId());
        }
        System.exit(0);
    }
    
}
