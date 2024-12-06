package node;

import eval.Environment;
import eval.EvalException;

/**
 * Expr - Node class that contains pointers for other nodes for the parsing tree.
 */
public class Expr extends Node {

    protected Term term;
    protected Addop addop;
    protected Expr expr;

    public Expr(Term term, Addop addop, Expr expr) {
        this.term = term;
        this.addop = addop;
        this.expr = expr;

    }

    public Expr(Term term) {
        this.term = term;
        this.addop = null;
        this.expr = null;
    }


    public void append(Expr expr) {
        if (this.expr==null)
        {
            this.addop=expr.addop;
            this.expr=expr;
            expr.addop=null;
        }
        else
        {
            this.expr.append(expr);
        }
    }

    /**
     * returns the double value returned from either a term node if addop is null, or from a computed value.
     * @param env - map of environment
     * @return
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException {
        if (this.addop == null){
            return this.term.eval(env);
        } else {
            return this.addop.compute(term.eval(env), expr.eval(env));
        }
    }
}
