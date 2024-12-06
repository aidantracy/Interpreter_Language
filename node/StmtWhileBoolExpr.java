package node;

import eval.Environment;
import eval.EvalException;

/**
 * StmtWhileBoolExpr node class represents the while loop in a program
 */
public class StmtWhileBoolExpr extends Stmt{
    protected Stmt stmt;
    protected BoolExpr boolExpr;

    public StmtWhileBoolExpr(Stmt stmt, BoolExpr boolExpr){
        this.stmt = stmt;
        this.boolExpr = boolExpr;
    }

    /**
     * eval() assigns a value to a specific ID from a parsed expr node
     * @param env - map of environment
     * @return the double value of the assigned val
     * @throws EvalException returns an error if lexeme does not exist.
     */
    public double eval(Environment env) throws EvalException {
        double val = 0;
        while(boolExpr.eval(env) == 1.0){
            val = stmt.eval(env);
        }
        return val;
    }
}
