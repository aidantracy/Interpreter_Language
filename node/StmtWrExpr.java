package node;

import eval.Environment;
import eval.EvalException;

/**
 * This class is a non-terminal that writes a value of a parsed expr node.
 */
public class StmtWrExpr extends Stmt {
    protected Wr wr;

    public StmtWrExpr(Expr expr) {
        this.wr = new Wr(expr);
    }

    /**
     * eval() writes a value of a parsed expr node to console.
     * @param env - map of environment
     * @return a double value printed to console
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException {
        return wr.eval(env);
    }

}
