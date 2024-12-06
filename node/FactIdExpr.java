package node;

import eval.Environment;
import syntax.Token;

/**
 * todo
 */
public class FactIdExpr extends Fact{
    protected Token id;
    protected Expr expr;

    public FactIdExpr(Token id, Expr expr) {
        this.id = id;
        this.expr = expr;
    }

    /**
     * todo
     * @param env - map of environment
     * @return
     */
    public double eval(Environment env){
        return 0;
    }
}
