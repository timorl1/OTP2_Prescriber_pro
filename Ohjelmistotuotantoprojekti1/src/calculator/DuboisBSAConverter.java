/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;


public class DuboisBSAConverter implements CalculatorStrategy {

    @Override
    public double convert(double weight, double height, int factor) {
        return 0.007184*Math.pow(height, 0.725)*Math.pow(weight, 0.425)*factor;
    }    
}
