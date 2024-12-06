package node;

import eval.Environment;
import eval.EvalException;

/**
 * StmtIfBoolStmt node class represents the "if - then" relational operation of a program
 */
public class StmtIfBoolStmt extends Stmt{
    protected BoolExpr boolExpr;
    protected Stmt stmt;
    protected Stmt elseStmt;

    public StmtIfBoolStmt(BoolExpr boolExpr, Stmt stmt, Stmt elseStmt) {
        this.boolExpr = boolExpr;
        this.stmt = stmt;
        this.elseStmt = elseStmt;
    }


    public StmtIfBoolStmt(BoolExpr boolExpr, Stmt stmt) {
        this.boolExpr = boolExpr;
        this.stmt = stmt;
        this.elseStmt = null;
    }

    /**
     * eval() assigns a value to a specific ID from a parsed expr node
     * @param env - map of environment
     * @return the double value of the assigned val
     * @throws EvalException returns an error if lexeme does not exist.
     */
    public double eval(Environment env) throws EvalException {
        if (boolExpr.eval(env) == 1.0){ // 1.0 is true
            return stmt.eval(env);
        } else if (elseStmt != null) {
            return elseStmt.eval(env);
        } else {
            return 0.0;
        }
    }
}
