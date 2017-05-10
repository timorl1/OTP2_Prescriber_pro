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
public interface CalculatorAlgorithm {
    public abstract double calculate(double[] values, Operator[] operators);
}
