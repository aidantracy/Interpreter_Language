package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * Mulop class that defines a token of * or /
 */
public class Mulop extends Node {

    protected Token mulop;

    public Mulop(int position, Token mulop) {
        this.mulop = mulop;
        this.position = position;
    }

    /**
     * Computes the values of two double values by subtracting or adding them together.
     * @param op1 - value from a parsed Fact Node
     * @param op2 - value from a parsed Term Node
     * @return Double Value from multiplying or dividing op1 and op2
     */
    public double compute(double op1, double op2) throws EvalException {
        if (this.mulop.equalType("*")){
            return op1 * op2;
        } else {
            return op1 / op2;
        }
    }
}
