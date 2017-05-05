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
public interface CalculatorStrategy {
    public abstract double convert(double weight, double height, int factor);
}
