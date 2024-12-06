package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * StmtAssn - Assigns a double value to a Lexeme string parsed from an expr node.
 */
public class StmtAssn extends Stmt{
    protected Assn assn;

    public  StmtAssn(Assn assn){
        this.assn = assn;
    }

    /**
     * eval() assigns a value to a specific ID from a parsed expr node
     * @param env - map of environment
     * @return the double value of the assigned val
     * @throws EvalException returns an error if lexeme does not exist.
     */
    public double eval(Environment env) throws EvalException {
        return this.assn.eval(env);
    }
}
