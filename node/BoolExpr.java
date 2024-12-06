package node;

import eval.Environment;
import eval.EvalException;

/**
 * BoolExpr node class that contains two expr nodes and a relational expression between the two.
 */
public class BoolExpr extends Node {
    protected Expr expr1;
    protected Relop relop;
    protected Expr expr2;


    public BoolExpr(Expr expr1, Relop relop, Expr expr2) {
        this.expr1 = expr1;
        this.relop = relop;
        this.expr2 = expr2;
    }

    /**
     * eval() assigns a value to a specific ID from a parsed expr node
     * @param env - map of environment
     * @return the double value of the assigned val
     * @throws EvalException returns an error if lexeme does not exist.
     */
    public double eval(Environment env) throws EvalException {
        double val1 = expr1.eval(env);
        double val2 = expr2.eval(env);
        return relop.compute(val1, val2);
    }
}
