package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * todo
 */
public class AssnIdExpr extends Assn{
    protected Token id;
    protected Expr expr;

    public AssnIdExpr(Token id, Expr expr) {
        this.id = id;
        this.expr = expr;
    }


    /**
     * eval() assigns a value to a specific ID from a parsed expr node
     * @param env - map of environment
     * @return the double value of the assigned val
     * @throws EvalException returns an error if lexeme does not exist.
     */
    public double eval(Environment env) throws EvalException {
        // need to find a way to check if lexeme has already been declared or not, if not it needs throw an error.
        env.get(id.getPosition(), id.getLexeme());
        double val = expr.eval(env);
        return env.update(id.getLexeme(), val);
    }
}
