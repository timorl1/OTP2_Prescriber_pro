/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.time.LocalDate;

/**
 *
 * @author joosiika
 */
public class AgeCalculator {
    
    private static AgeCalculator INSTANCE = null;
    private int currentYear = LocalDate.now().getYear();
    
    public static AgeCalculator getInstance() {
        if (INSTANCE == null) {
            return new AgeCalculator();
        }
        return INSTANCE;
    }
    public int calculateAge(String SSN) {
        if (SSN.substring(6, 7).equalsIgnoreCase("a")) {
            return this.currentYear-(2000+Integer.parseInt(SSN.substring(4, 6)));
        }
        return this.currentYear-(1900+Integer.parseInt(SSN.substring(4, 6)));
    }
}
