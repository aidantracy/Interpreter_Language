package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * This is a non-terminal subclass of Fact that stores the grammar:  "("  EXPR  ")"
 */
public class FactOpExprOp extends Fact{
    protected Expr expr;


    public FactOpExprOp(Expr expr) {
        this.expr = expr;
    }

    /**
     * evaluates an expr node
     * @param env - map of environment
     * @return the double value returned from the expr node
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException {
        return this.expr.eval(env);
    }
}
