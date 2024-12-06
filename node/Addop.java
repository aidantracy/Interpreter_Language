package node;

import eval.Environment;
import eval.EvalException;
import syntax.*;

/**
 * Addop class that defines a token of + or -
 */
public class Addop extends Node {

    protected Token addop;

    public Addop(int position, Token addop) {

        this.position = position;
        this.addop = addop;
    }

    /**
     * Computes the values of two double values by subtracting or adding them together.
     * @param op1 - value from a parsed Term Node
     * @param op2 - value from a parsed EXPR Node
     * @return Double Value from adding or subtracting op1 and op2
     */
    public double compute(double op1, double op2) {
        if (addop.equalType("-")){
            return op2 - op1;
        } else {
            return op1 + op2;
        }
    }
}
