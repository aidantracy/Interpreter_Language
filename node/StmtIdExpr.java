package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * todo
 */
public class StmtIdExpr extends Stmt{
    protected Expr expr;
    protected Token id;

    public StmtIdExpr(Token id1, Expr e) {
        id = id1;
        expr = e;
    }


    /**
     * todo
     * @param env - map of environment
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return 0; // todo
    }
}
