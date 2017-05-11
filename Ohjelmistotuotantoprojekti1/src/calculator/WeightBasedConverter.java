/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;


public class WeightBasedConverter implements CalculatorStrategy {

    @Override
    public double convert(double weight, double height, int factor) {
        return weight;
    }
}
