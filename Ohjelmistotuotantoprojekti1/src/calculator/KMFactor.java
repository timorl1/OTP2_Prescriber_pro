/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author joosiika
 */
public class KMFactor {
    private static KMFactor INSTANCE = null;
    private final int child = 25;
    private final int adult = 37;

    public static KMFactor getInstance() {
        if (INSTANCE == null) {
            return new KMFactor();
        }
        return INSTANCE;
    }

    public int getFactor(int age) {
        if (age < 16) {
            return child;
        }
        return adult;
    }
}
