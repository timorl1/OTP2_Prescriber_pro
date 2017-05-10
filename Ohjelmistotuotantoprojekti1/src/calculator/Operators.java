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
public enum Operators implements Operator{
    ADD {
        @Override
        public double apply(double x1, double x2) {
            return x1 + x2;
        }
        
        @Override
        public String toString() {
            return "+";
        }
    },
    SUBTRACT {
        @Override
        public double apply(double x1, double x2) {
            return x1 - x2;
        }
        
        @Override
        public String toString() {
            return "-";
        }
    },
    MULTIPLY {
        @Override
        public double apply(double x1, double x2) {
            return x1 * x2;
        }
        
        @Override
        public String toString() {
            return "*";
        }
    },
    DIVIDE {
        @Override
        public double apply(double x1, double x2) {
            return x1 / x2;
        }
        
        @Override
        public String toString() {
            return "÷";
        }
    }
}
