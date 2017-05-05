/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;


public class MonstellerBSAConverter implements CalculatorStrategy {

    @Override
    public double convert(double weight, double height, int factor) {
        return Math.sqrt(height*weight/3600)*factor;
    }
}
