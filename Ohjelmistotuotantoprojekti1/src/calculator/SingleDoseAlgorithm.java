/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;


public class SingleDoseAlgorithm implements CalculatorAlgorithm {
    private String resultString;

    @Override
    public double calculate(double[] values, Operator[] operators) {
        double res = values[0];
        String str = String.valueOf(values[0]);
        for (int i = 0; i < values.length-1; i++) {
            res = operators[i].apply(res, values[i+1]);
            str += operators[i].toString() + values[i+1];
        }
        this.resultString = str + "=" + res;
        return res;
    }
    
    @Override
    public String toString() {
        return this.resultString;
    }
}
